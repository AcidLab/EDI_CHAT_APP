package co.acidlabs.lanala.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;
import java.util.ArrayList;

import co.acidlabs.lanala.LoginActivity;
import co.acidlabs.lanala.PayActivity;
import co.acidlabs.lanala.R;
import co.acidlabs.lanala.adapters.HistoryAdapter;
import co.acidlabs.lanala.adapters.ImpayedAdapter;
import co.acidlabs.lanala.helpers.DelegateObserver;
import co.acidlabs.lanala.helpers.Utils;
import co.acidlabs.lanala.models.QImpayed;
import co.acidlabs.lanala.models.User;
import co.acidlabs.lanala.networking.NetworkingAsyncResponse;
import co.acidlabs.lanala.networking.NetworkingHelper;

public class ImpayedFragment extends Fragment implements NetworkingAsyncResponse,DelegateObserver {

    private RecyclerView mRecyclerView;
    private RelativeLayout loaderView;
    private Button payButton;
    private TextView total;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_impayed, container, false);

        mRecyclerView = rootView.findViewById(R.id.list);
        payButton = rootView.findViewById(R.id.pay);
        mRecyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity().getApplicationContext());
        mRecyclerView.setLayoutManager(mLayoutManager);
        loaderView = rootView.findViewById(R.id.loaderView);
        total = rootView.findViewById(R.id.total);

        return rootView;
    }

    @Override
    public void onStart() {
        super.onStart();
        NetworkingHelper api = new NetworkingHelper(ImpayedFragment.this);
        api.GetListeQuittancesImpayees(Utils.selectedContract.getNumeroPolice());
        loaderView.setVisibility(View.VISIBLE);

        payButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Utils.fromQ = true;
                Intent i = new Intent(getActivity(),PayActivity.class);
                getActivity().startActivity(i);
            }
        });
    }


    @Override
    public void onImpayedSuccess(ArrayList<QImpayed> qImpayeds){

        loaderView.setVisibility(View.GONE);
        RecyclerView.Adapter mAdapter = new ImpayedAdapter(qImpayeds,this);
        mRecyclerView.setAdapter(mAdapter);

        double som = 0;
        for (int i = 0;i<qImpayeds.size();i++) {

            som+= Double.parseDouble(qImpayeds.get(i).getMontant_quittance().replace(" ",""));

        }
        total.setText("Total imapyé : "+(String.format("%,14.2f", som)).replace(",00",""));


    }

    @Override
    public void onImpayedError(String message){

        loaderView.setVisibility(View.GONE);

    }

    @Override
    public void onTokenError () {
        User.logoutUser(getActivity());
        Intent i = new Intent(getActivity(),LoginActivity.class);
        startActivity(i);
        getActivity().finishAffinity();
    }

    @Override
    public void onResume () {

        super.onResume();

        if (Utils.selectedBox.size() == 0) {

            payButton.setVisibility(View.GONE);

        }

        else {

            payButton.setVisibility(View.VISIBLE);
            double som = 0;
            for (int i = 0;i<Utils.selectedBox.size();i++) {

                som+= Double.parseDouble(Utils.selectedBox.get(i).getMontant_quittance().replace(" ",""));

            }

            payButton.setText("Payer ("+(String.format("%,14.2f", som)).replace(",00","")+")");

        }
    }

    @Override
    public void onCheckChange () {

        //Toast.makeText(getContext(),"d",Toast.LENGTH_SHORT).show();

        if (Utils.selectedBox.size() == 0) {

            payButton.setVisibility(View.GONE);

        }

        else {

            payButton.setVisibility(View.VISIBLE);
            double som = 0;
            for (int i = 0;i<Utils.selectedBox.size();i++) {

                som+= Double.parseDouble(Utils.selectedBox.get(i).getMontant_quittance().replace(" ",""));

            }

            payButton.setText("Payer ("+(String.format("%,14.2f", som)).replace(",00","")+")");

        }
    }
}
