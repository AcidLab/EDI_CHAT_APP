<?php
/**
 * Created by PhpStorm.
 * User: selim
 * Date: 2/11/2019
 * Time: 3:58 PM
 */

namespace App\Repositories;


use App\Models\Session;

class SessionRepository extends ResourceRepository
{
    public function __construct(Session $session)
    {
        $this->model = $session;
    }

    //@Override
    public function getById($id)
    {
        return $this->model->where('session_id', $id)->first();
    }

    public function getPending()
    {
        return $this->model->where('accepted', 0)->oldest()->get();
    }

}