package co.acidlabs.proassur;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import co.acidlabs.proassur.adapters.ContractAdapter;
import co.acidlabs.proassur.helpers.Utils;
import co.acidlabs.proassur.models.Contract;
import co.acidlabs.proassur.models.User;
import co.acidlabs.proassur.networking.NetworkingAsyncResponse;
import co.acidlabs.proassur.networking.NetworkingHelper;
import co.acidlabs.proassur.networking.OTService;
import co.acidlabs.proassur.networking.OTServiceListener;
import pub.devrel.easypermissions.AfterPermissionGranted;
import pub.devrel.easypermissions.AppSettingsDialog;
import pub.devrel.easypermissions.EasyPermissions;


public class ContractActivity extends AppCompatActivity implements NetworkingAsyncResponse, EasyPermissions.PermissionCallbacks, OTServiceListener {

    private TextView name,code;
    private RelativeLayout loaderView;
    private RecyclerView mRecyclerView;
    private SwipeRefreshLayout pullToRefresh;
    private static final int RC_SETTINGS_SCREEN_PERM = 123;
    private static final int RC_VIDEO_APP_PERM = 124;
    private RelativeLayout call;
    private OTService otService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contract);
        name = findViewById(R.id.name);
        code = findViewById(R.id.code);
        loaderView = findViewById(R.id.loaderView);
        mRecyclerView = findViewById(R.id.list);
        call = (RelativeLayout) findViewById(R.id.callView);
        mRecyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        mRecyclerView.setLayoutManager(mLayoutManager);

        requestPermissions();

        otService = new OTService(this);

        name.setText(User.getCurrentUser(getApplicationContext()).getEmail());
        code.setText(User.getCurrentUser(getApplicationContext()).getCode_client());

        pullToRefresh = findViewById(R.id.swiperefresh);
        pullToRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {

                Utils.UserId = User.getCurrentUser(getApplicationContext()).getCode_client();
                Utils.sessionCode = User.getCurrentUser(getApplicationContext()).getId();
                NetworkingHelper api = new NetworkingHelper(ContractActivity.this);
                api.GetListPoliceVie();

            }
        });







    }

    @Override
    protected void onStart () {
        super.onStart();

        Utils.UserId = User.getCurrentUser(getApplicationContext()).getCode_client();
        Utils.sessionCode = User.getCurrentUser(getApplicationContext()).getId();
        NetworkingHelper api = new NetworkingHelper(ContractActivity.this);
        api.GetListPoliceVie();
        //Toast.makeText(getApplicationContext(),"Start",Toast.LENGTH_LONG).show();




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



    @Override
    public void onContractListSuccess(ArrayList<Contract> contracts){

        pullToRefresh.setRefreshing(false);
        loaderView.setVisibility(View.GONE);

        RecyclerView.Adapter mAdapter = new ContractAdapter(contracts,otService);
        mRecyclerView.setAdapter(mAdapter);
        //Toast.makeText(getApplicationContext(),""+contracts.size(),Toast.LENGTH_SHORT).show();

    }
    @Override
    public void onContractListError(String message){
        pullToRefresh.setRefreshing(false);

        loaderView.setVisibility(View.GONE);
        //Toast.makeText(getApplicationContext(),message,Toast.LENGTH_LONG).show();

    }

    @Override
    public void onTokenError () {
        pullToRefresh.setRefreshing(false);
        User.logoutUser(getApplicationContext());
        Intent i = new Intent(getApplicationContext(),LoginActivity.class);
        startActivity(i);
        finishAffinity();
    }

    public void logoutAlert(View view) {

        AlertDialog.Builder builder1 = new AlertDialog.Builder(ContractActivity.this);
        builder1.setTitle("Déconnexion");
        builder1.setMessage("Voulez-vous vraiment se déconnecter ?");
        builder1.setCancelable(true);

        builder1.setPositiveButton(
                "OUI",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                        Intent i = new Intent(getApplicationContext(),LoginActivity.class);
                        User.logoutUser(getApplicationContext());
                        startActivity(i);
                        finishAffinity();


                    }
                });

        builder1.setNegativeButton(
                "NON",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();


                        //mViewPager.setCurrentItem(0);
                    }
                });

        AlertDialog alert11 = builder1.create();
        alert11.show();
    }

    public void showMenu(View view) {
        openOptionsMenu();
    }




}
