package co.acidlabs.proassur;

import android.opengl.GLSurfaceView;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.opentok.android.BaseVideoRenderer;
import com.opentok.android.OpentokError;
import com.opentok.android.Publisher;
import com.opentok.android.PublisherKit;
import com.opentok.android.Session;
import com.opentok.android.Stream;
import com.opentok.android.Subscriber;
import com.opentok.android.SubscriberKit;

import java.util.ArrayList;
import java.util.HashMap;

import co.acidlabs.proassur.helpers.OpenTokConfig;
import co.acidlabs.proassur.networking.OTService;
import co.acidlabs.proassur.networking.OTServiceListener;

public class OTActivity extends AppCompatActivity
        implements Session.SessionListener,
        PublisherKit.PublisherListener,
        SubscriberKit.SubscriberListener,
        OTServiceListener
{
    private static final String LOG_TAG = OTActivity.class.getSimpleName();
    private static final int MAX_SUB = 2;

    private Session mSession;
    private Publisher mPublisher;

    private ArrayList<Subscriber> mSubscribers = new ArrayList<>();
    private HashMap<Stream, Subscriber> mSubscriberStreams = new HashMap<>();

    private RelativeLayout mPublisherViewContainer;

    private OTService otService;
    private String sessionId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ot_m);

        mPublisherViewContainer = findViewById(R.id.publisherview);

        initUI();
        hideChatUI();

        ImageView end = findViewById(R.id.end);
        end.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                mSession.disconnect();
                finish();

            }
        });


        otService = new OTService(this);

        sessionId = getIntent().getStringExtra("sessionId");
        if(sessionId==null || sessionId.isEmpty())
            finish();
        otService.requestToken(sessionId);
    }

    private void initUI() {
        final Button swapCamera = findViewById(R.id.swapCamera);
        swapCamera.setOnClickListener(v -> {
            if (mPublisher == null) {
                return;
            }
            mPublisher.cycleCamera();
        });

        final ToggleButton toggleAudio = findViewById(R.id.toggleAudio);
        toggleAudio.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (mPublisher == null) {
                    return;
                }
                if (isChecked) {
                    mPublisher.setPublishAudio(true);
                } else {
                    mPublisher.setPublishAudio(false);
                }
            }
        });

        final ToggleButton toggleVideo = findViewById(R.id.toggleVideo);
        toggleVideo.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (mPublisher == null) {
                    return;
                }
                if (isChecked) {
                    mPublisher.setPublishVideo(true);
                } else {
                    mPublisher.setPublishVideo(false);
                }
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_end) {
            mSession.disconnect();
            finish();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onPause() {
        super.onPause();

        if (mSession != null) {
            mSession.onPause();
        }

    }

    @Override
    protected void onResume() {
        super.onResume();

        if (mSession != null) {
            mSession.onResume();
        }
    }

    private void initializeSession(String token) {
        mSession = new Session.Builder(this, OpenTokConfig.API_KEY, sessionId).build();
        mSession.setSessionListener(this);
        mSession.connect(token);
    }

    /* Session Listener methods */

    @Override
    public void onConnected(Session session) {
        Log.d(LOG_TAG, "onConnected: Connected to session: " + session.getSessionId());

        // initialize Publisher and set this object to listen to Publisher events
        mPublisher = new Publisher.Builder(this).build();
        mPublisher.setPublisherListener(this);

        // set publisher video style to fill view
        mPublisher.getRenderer().setStyle(BaseVideoRenderer.STYLE_VIDEO_SCALE,
                BaseVideoRenderer.STYLE_VIDEO_FILL);
        mPublisherViewContainer.addView(mPublisher.getView());
        if (mPublisher.getView() instanceof GLSurfaceView) {
            ((GLSurfaceView) mPublisher.getView()).setZOrderOnTop(true);
        }

        mSession.publish(mPublisher);
    }

    @Override
    public void onDisconnected(Session session) {
        Log.d(LOG_TAG, "onDisconnected: Disconnected from session: " + session.getSessionId());
    }

    @Override
    public void onStreamReceived(Session session, Stream stream) {
        Log.d(LOG_TAG, "onStreamReceived: New Stream Received " + stream.getStreamId() + " in session: " + session.getSessionId());



        if(mSubscribers.size()==MAX_SUB)
            return;

        final Subscriber subscriber = new Subscriber.Builder(OTActivity.this, stream).build();
        mSession.subscribe(subscriber);
        mSubscribers.add(subscriber);
        mSubscriberStreams.put(stream, subscriber);

        int position = mSubscribers.size() - 1;
        int id = getResources().getIdentifier("subscriberview" + (Integer.valueOf(position)).toString(), "id", OTActivity.this.getPackageName());
        RelativeLayout subscriberViewContainer =  findViewById(id);

        subscriber.setStyle(BaseVideoRenderer.STYLE_VIDEO_SCALE, BaseVideoRenderer.STYLE_VIDEO_FILL);
        subscriberViewContainer.addView(subscriber.getView());

        id = getResources().getIdentifier("toggleAudioSubscriber" + (Integer.valueOf(position)).toString(), "id", OTActivity.this.getPackageName());
        final ToggleButton toggleAudio = (ToggleButton) findViewById(id);
        toggleAudio.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    subscriber.setSubscribeToAudio(true);
                } else {
                    subscriber.setSubscribeToAudio(false);
                }
            }
        });
        toggleAudio.setVisibility(View.GONE);

        if(mSubscribers.size() >= 1){
            showChatUI();
        }


    }

    @Override
    public void onStreamDropped(Session session, Stream stream) {
        Log.d(LOG_TAG, "onStreamDropped: Stream Dropped: " + stream.getStreamId() + " in session: " + session.getSessionId());

        if(mSubscribers.size() == 1){
            hideChatUI();
            mSession.disconnect();
            finish();
        }

        Subscriber subscriber = mSubscriberStreams.get(stream);
        if (subscriber == null) {
            return;
        }

        int position = mSubscribers.indexOf(subscriber);
        int id = getResources().getIdentifier("subscriberview" + (Integer.valueOf(position)).toString(), "id", OTActivity.this.getPackageName());

        mSubscribers.remove(subscriber);
        mSubscriberStreams.remove(stream);

        RelativeLayout subscriberViewContainer = findViewById(id);
        subscriberViewContainer.removeView(subscriber.getView());

        id = getResources().getIdentifier("toggleAudioSubscriber" + (Integer.valueOf(position)).toString(), "id", OTActivity.this.getPackageName());
        final ToggleButton toggleAudio = findViewById(id);
        toggleAudio.setOnCheckedChangeListener(null);
        toggleAudio.setVisibility(View.INVISIBLE);
    }

    @Override
    public void onError(Session session, OpentokError opentokError) {
        Log.e(LOG_TAG, "onError: " + opentokError.getErrorDomain() + " : " +
                opentokError.getErrorCode() + " - " + opentokError.getMessage() + " in session: " + session.getSessionId());

        showOpenTokError(opentokError);
    }

    /* Publisher Listener methods */

    @Override
    public void onStreamCreated(PublisherKit publisherKit, Stream stream) {
        Log.d(LOG_TAG, "onStreamCreated: Publisher Stream Created. Own stream " + stream.getStreamId());
    }

    @Override
    public void onStreamDestroyed(PublisherKit publisherKit, Stream stream) {
        Log.d(LOG_TAG, "onStreamDestroyed: Publisher Stream Destroyed. Own stream " + stream.getStreamId());
    }

    @Override
    public void onError(PublisherKit publisherKit, OpentokError opentokError) {
        Log.e(LOG_TAG, "onError: " + opentokError.getErrorDomain() + " : " +
                opentokError.getErrorCode() + " - " + opentokError.getMessage());

        showOpenTokError(opentokError);
    }

    /* Subscriber Listener methods */

    @Override
    public void onConnected(SubscriberKit subscriberKit) {
        Log.d(LOG_TAG, "onConnected: Subscriber connected. Stream: " + subscriberKit.getStream().getStreamId());
    }

    @Override
    public void onDisconnected(SubscriberKit subscriberKit) {
        Log.d(LOG_TAG, "onDisconnected: Subscriber disconnected. Stream: " + subscriberKit.getStream().getStreamId());
    }

    @Override
    public void onError(SubscriberKit subscriberKit, OpentokError opentokError) {
        Log.e(LOG_TAG, "onError: " + opentokError.getErrorDomain() + " : " +
                opentokError.getErrorCode() + " - " + opentokError.getMessage());

        showOpenTokError(opentokError);
    }

    private void showOpenTokError(OpentokError opentokError) {
        Toast.makeText(this, opentokError.getErrorDomain().name() + ": " + opentokError.getMessage() + " Please, see the logcat.", Toast.LENGTH_LONG).show();
        finish();
    }

    // OT Service

    @Override
    public void onTokenReceived(String token) {
        initializeSession(token);
    }


    private void hideChatUI(){
        if (mPublisher != null) {
            mPublisher.setPublishVideo(false);
            mPublisher.setPublishAudio(false);
        }

        LinearLayout linearLayout = findViewById(R.id.linearLayout);
        LinearLayout controls = findViewById(R.id.controls);
        linearLayout.setVisibility(View.GONE);
        controls.setVisibility(View.GONE);
        mPublisherViewContainer.setVisibility(View.GONE);

        RelativeLayout image_calling = findViewById(R.id.callView);
        image_calling.setVisibility(View.VISIBLE);
    }
    private void showChatUI(){
        if (mPublisher != null) {
            mPublisher.setPublishVideo(true);
            mPublisher.setPublishAudio(true);
        }

        LinearLayout linearLayout = findViewById(R.id.linearLayout);
        LinearLayout controls = findViewById(R.id.controls);
        linearLayout.setVisibility(View.VISIBLE);
        controls.setVisibility(View.VISIBLE);
        mPublisherViewContainer.setVisibility(View.VISIBLE);

        RelativeLayout image_calling = findViewById(R.id.callView);
        image_calling.setVisibility(View.GONE);
    }

    @Override
    public void onBackPressed() {

    }
}
