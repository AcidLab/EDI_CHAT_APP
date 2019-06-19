<?php
/**
 * Created by PhpStorm.
 * User: selim
 * Date: 1/21/2019
 * Time: 2:42 AM
 */

namespace App\Http\Controllers;

use App\Models\Archive;
use App\Models\Session;
use App\Repositories\SessionRepository;
use Illuminate\Http\Request;
use OpenTok\ArchiveMode;
use OpenTok\Exception\ArchiveException;
use OpenTok\MediaMode;
use OpenTok\OpenTok;
use OpenTok\Role;
use Pusher\Pusher;
use Pusher\PusherException;

class OTController extends Controller
{
    protected $sessionRepository;

    protected $opentok;
    private $apiKey = "46254462";
    private $apiSecret = "bd3b761cc6975931ffec4f2e8b1e9282a5fc409c";
    private $sessionId;

    public function __construct(SessionRepository $sessionRepository)
    {
        $this->sessionRepository = $sessionRepository;
        $this->opentok = new OpenTok($this->apiKey, $this->apiSecret);
        //$this->sessionId = "2_MX40NjI1NDQ2Mn5-MTU0ODAzODQ5OTMzOH5NRi9wR0tZcFpHUkVKaDNDVnhKS2I4QTF-UH4";
    }
    public function index(){
        $sessions = $this->sessionRepository->getPending();
        return view('main')->withSessions($sessions);
    }

    public function index_old()
    {
        $session = Session::all()->last();

        if (1) {
            $sessionOptions = array(
                'mediaMode' => MediaMode::ROUTED,
                'archiveMode' => ArchiveMode::MANUAL
            );
            $session = $this->opentok->createSession($sessionOptions);
            $sessionId = $session->getSessionId();
        } else {
            $sessionId = $session->session_id;
        }

        // ---

        $archives = Archive::all();
        $archivesObj = array();
        foreach ($archives as $archive) {
            try {
                $archivesObj[] = $this->opentok->getArchive($archive->archive_id);
            } catch (\OpenTok\ArchiveException $e) {
            }
        }

        $archivesObj = $this->opentok->listArchives()->getItems();
        return view('welcome')->with('sessionId', $sessionId)->with('archives', $archivesObj);
    }

    public function requestSessionId()
    {
        $sessionOptions = array(
            'mediaMode' => MediaMode::ROUTED,
            'archiveMode' => ArchiveMode::ALWAYS
        );
        $session = $this->opentok->createSession($sessionOptions);

        $db_session = $this->sessionRepository->store(['session_id' => $session->getSessionId(), 'accepted' => 0]);

        $this->notify($db_session);

        return ["sessionId" => $session->getSessionId()];
    }

    public function generateToken(Request $request)
    {
        $sessionId = $request->get("sessionId");
        if($request->has("accepted")){
            $this->sessionRepository->update($sessionId, ["accepted" => 1]);
        }
        return ["sessionId" => $sessionId, "token" => $this->opentok->generateToken($sessionId)];
    }

    public function startArchive(Request $request)
    {
        $sessionId = $request->get("sessionId");
        $archive = $this->opentok->startArchive($sessionId);

        return $archive->jsonSerialize();
    }

    public function stopArchive(Request $request)
    {
        $archiveId = $request->get("archiveId");
        try {
            $archive = $this->opentok->getArchive($archiveId);

            $archive->stop();
            Archive::create(['archive_id' => $archive->id, 'session_id' => "0",  'url' => ""]);
            return $archive->jsonSerialize();
        } catch (ArchiveException $e) {
            return $e->getMessage();
        }
    }

    public function getArchive(Request $request)
    {
        $archiveId = $request->get("archiveId");
        try {
            $archive = $this->opentok->getArchive($archiveId);
            return $archive->jsonSerialize();
        } catch (ArchiveException $e) {
            return $e->getMessage();
        }
    }

    public function notify($session){
        $options = array(
            'cluster' => 'eu',
            'useTLS' => true
        );
        $pusher = new Pusher(
            'ab501e6a821b06d9c651',
            'adf043ddcb5ac1c340da',
            '711493',
            $options
        );

        $data['id'] = $session->id;
        $data['session_id'] = $session->session_id;

        try {
            $pusher->trigger('threads', 'new-call', $data);
        } catch (PusherException $e) {
        }
    }

    public function acceptConnection(Request $request){
        $sessionId = $request->get('sessionId');
        $session = $this->sessionRepository->getById($sessionId);
        $this->sessionRepository->update($session->id, ['accepted' => 1]);
    }
}