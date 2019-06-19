package co.acidlabs.lanala;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
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

import co.acidlabs.lanala.adapters.ContractAdapter;
import co.acidlabs.lanala.adapters.HistoryAdapter;
import co.acidlabs.lanala.helpers.Utils;
import co.acidlabs.lanala.models.Contract;
import co.acidlabs.lanala.models.User;
import co.acidlabs.lanala.networking.NetworkingAsyncResponse;
import co.acidlabs.lanala.networking.NetworkingHelper;


public class ContractActivity extends AppCompatActivity implements NetworkingAsyncResponse {

    private TextView name,code;
    private RelativeLayout loaderView;
    private RecyclerView mRecyclerView;
    private SwipeRefreshLayout pullToRefresh;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contract);
        name = findViewById(R.id.name);
        code = findViewById(R.id.code);
        loaderView = findViewById(R.id.loaderView);
        mRecyclerView = findViewById(R.id.list);
        mRecyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        mRecyclerView.setLayoutManager(mLayoutManager);

        name.setText(User.getCurrentUser(getApplicationContext()).getNom());
        code.setText(User.getCurrentUser(getApplicationContext()).getCode_client()+"             " +
                "        "+User.getCurrentUser(getApplicationContext()).getDate_naissance());

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
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.action_settings:
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onContractListSuccess(ArrayList<Contract> contracts){

        loaderView.setVisibility(View.GONE);
        pullToRefresh.setRefreshing(false);
        RecyclerView.Adapter mAdapter = new ContractAdapter(contracts);
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
