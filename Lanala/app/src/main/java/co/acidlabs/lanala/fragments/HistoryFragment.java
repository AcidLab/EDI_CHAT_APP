package co.acidlabs.lanala.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import co.acidlabs.lanala.LoginActivity;
import co.acidlabs.lanala.R;
import co.acidlabs.lanala.adapters.HistoryAdapter;
import co.acidlabs.lanala.helpers.Utils;
import co.acidlabs.lanala.models.QHistory;
import co.acidlabs.lanala.models.QImpayed;
import co.acidlabs.lanala.models.User;
import co.acidlabs.lanala.networking.NetworkingAsyncResponse;
import co.acidlabs.lanala.networking.NetworkingHelper;

public class HistoryFragment extends Fragment implements NetworkingAsyncResponse {


    private RecyclerView mRecyclerView;
    private RelativeLayout loaderView;
    private TextView total;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_history, container, false);

        loaderView = rootView.findViewById(R.id.loaderView);

        mRecyclerView = rootView.findViewById(R.id.list);
        mRecyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity().getApplicationContext());
        mRecyclerView.setLayoutManager(mLayoutManager);
        total = rootView.findViewById(R.id.total);



        return rootView;
    }

    @Override
    public void onStart() {
        super.onStart();
        NetworkingHelper api = new NetworkingHelper(HistoryFragment.this);
        api.GetHistoriqueVersement(Utils.selectedContract.getNumeroPolice());
        loaderView.setVisibility(View.VISIBLE);
    }


    @Override
    public void onHistorySuccess(ArrayList<QHistory> qHistories) {

        loaderView.setVisibility(View.GONE);
        RecyclerView.Adapter mAdapter = new HistoryAdapter(qHistories);
        mRecyclerView.setAdapter(mAdapter);
        double som = 0;
        for (int i = 0;i<qHistories.size();i++) {

            som+= Double.parseDouble(qHistories.get(i).getMontant_payee().replace(" ",""));

        }

        total.setText("Total des versements : "+(String.format("%,14.2f", som)).replace(",00",""));

    }

    @Override
    public void onHistoryError(String message){

        loaderView.setVisibility(View.GONE);

    }

    @Override
    public void onTokenError () {
        User.logoutUser(getActivity());
        Intent i = new Intent(getActivity(),LoginActivity.class);
        startActivity(i);
        getActivity().finishAffinity();
    }
}
