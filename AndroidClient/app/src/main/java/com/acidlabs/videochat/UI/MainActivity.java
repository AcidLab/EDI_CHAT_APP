package com.acidlabs.videochat.UI;

import android.Manifest;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.opengl.GLSurfaceView;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.acidlabs.videochat.Movie;
import com.acidlabs.videochat.MoviesAdapter;
import com.acidlabs.videochat.OpenTokConfig;
import com.acidlabs.videochat.RecyclerItemClickListener;
import com.acidlabs.videochat.Service.OTService;
import com.acidlabs.videochat.Service.OTServiceListener;
import com.opentok.android.BaseVideoRenderer;
import com.opentok.android.OpentokError;
import com.opentok.android.Publisher;
import com.opentok.android.PublisherKit;
import com.opentok.android.Session;
import com.opentok.android.Stream;
import com.opentok.android.Subscriber;
import com.opentok.android.SubscriberKit;
import com.tokbox.android.tutorials.basicvideochat.R;

import java.util.ArrayList;
import java.util.List;

import pub.devrel.easypermissions.AfterPermissionGranted;
import pub.devrel.easypermissions.AppSettingsDialog;
import pub.devrel.easypermissions.EasyPermissions;


public class MainActivity extends AppCompatActivity
        implements EasyPermissions.PermissionCallbacks, OTServiceListener,View.OnClickListener {

    private static final String LOG_TAG = MainActivity.class.getSimpleName();
    private static final int RC_SETTINGS_SCREEN_PERM = 123;
    private static final int RC_VIDEO_APP_PERM = 124;
    private List<Movie> movieList = new ArrayList<>();
    private RecyclerView recyclerView;
    private RelativeLayout call;
    private MoviesAdapter mAdapter;

    private OTService otService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        Log.d(LOG_TAG, "onCreate");

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        call = (RelativeLayout) findViewById(R.id.callView);
        mAdapter = new MoviesAdapter(movieList,this::onClick);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mAdapter);




        prepareMovieData();

        requestPermissions();

        otService = new OTService(this);

        Button button_start = findViewById(R.id.button_start);
        button_start.setOnClickListener(e -> {

        });

    }



    private void prepareMovieData() {
        Movie movie = new Movie("CONTRAT VOITURE 208", "Du 01/01/2019 au 31/12/2019", "");
        movieList.add(movie);

        movie = new Movie("CONTRAT VOITURE Audi A3", "Du 01/02/2019 au 31/01/2019", "");
        movieList.add(movie);


        mAdapter.notifyDataSetChanged();
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, this);
    }

    @Override
    public void onPermissionsGranted(int requestCode, List<String> perms) {
    }

    @Override
    public void onPermissionsDenied(int requestCode, List<String> perms) {
        if (EasyPermissions.somePermissionPermanentlyDenied(this, perms)) {
            new AppSettingsDialog.Builder(this)
                    .setTitle(getString(R.string.title_settings_dialog))
                    .setRationale(getString(R.string.rationale_ask_again))
                    .setPositiveButton(getString(R.string.setting))
                    .setNegativeButton(getString(R.string.cancel))
                    .setRequestCode(RC_SETTINGS_SCREEN_PERM)
                    .build()
                    .show();
        }
    }

    @AfterPermissionGranted(RC_VIDEO_APP_PERM)
    private void requestPermissions() {

        String[] perms = {Manifest.permission.INTERNET, Manifest.permission.CAMERA, Manifest.permission.RECORD_AUDIO};
        if (EasyPermissions.hasPermissions(this, perms)) {
        } else {
            EasyPermissions.requestPermissions(this, getString(R.string.rationale_video_app), RC_VIDEO_APP_PERM, perms);
        }
    }

    public void onSessionIdReceived(String sessionId) {
        System.out.println("sessionId = [" + sessionId + "]");
        Intent intent = new Intent(this, OTActivity.class);
        intent.putExtra("sessionId", sessionId);
        startActivity(intent);
    }

    public void onSessionIdError () {

        //call.setVisibility(View.GONE);
        //getSupportActionBar().show();

    }

    public void ItemClick (View v) {



    }


    @Override
    public void onClick(View view) {
        Toast.makeText(getApplicationContext(),"call",Toast.LENGTH_SHORT).show();
        otService.requestSessionId();
        //call.setVisibility(View.VISIBLE);
        //getSupportActionBar().hide();

    }
}
