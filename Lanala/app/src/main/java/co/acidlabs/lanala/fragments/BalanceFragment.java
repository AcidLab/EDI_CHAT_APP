package co.acidlabs.lanala.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.text.NumberFormat;
import java.util.Locale;

import co.acidlabs.lanala.ContractActivity;
import co.acidlabs.lanala.LoginActivity;
import co.acidlabs.lanala.PayActivity;
import co.acidlabs.lanala.R;
import co.acidlabs.lanala.helpers.Utils;
import co.acidlabs.lanala.models.Balance;
import co.acidlabs.lanala.models.User;
import co.acidlabs.lanala.networking.NetworkingAsyncResponse;
import co.acidlabs.lanala.networking.NetworkingHelper;

public class BalanceFragment extends Fragment implements NetworkingAsyncResponse {

    private ConstraintLayout payView;
    private Button payButton,onPayButton;
    private ImageView closeButton,add,remove;
    private TextView cumul,name_ben,contract,epargne,periode,risque,epargne_c;
    private RelativeLayout loaderView;
    private EditText moneyEdit;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_balance_new, container, false);
        payView = rootView.findViewById(R.id.pay);
        onPayButton = rootView.findViewById(R.id.onPay);

        payButton = rootView.findViewById(R.id.payBtn);
        closeButton = rootView.findViewById(R.id.closeBtn);
        moneyEdit = rootView.findViewById(R.id.moneyEdit);
        add = rootView.findViewById(R.id.add);
        remove = rootView.findViewById(R.id.remove);

        epargne = rootView.findViewById(R.id.epargne);
        risque = rootView.findViewById(R.id.risque);
        periode = rootView.findViewById(R.id.periode);

        loaderView = rootView.findViewById(R.id.loaderView);

        cumul = rootView.findViewById(R.id.cumul);
        name_ben = rootView.findViewById(R.id.ben_name);
        contract = rootView.findViewById(R.id.contrat);
        epargne_c = rootView.findViewById(R.id.epargne_c);

        contract.setText(Utils.selectedContract.getNumeroPolice());
        name_ben.setText(Utils.selectedContract.getBeneficiairesCasVie());

        payButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                payView.setVisibility(View.VISIBLE);

            }
        });

        onPayButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Utils.fromQ = false;
                Intent i = new Intent(getActivity(),PayActivity.class);
                i.putExtra("montant",Double.parseDouble(moneyEdit.getText().toString().replace(" ","")));
                getActivity().startActivity(i);
            }
        });

        closeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                payView.setVisibility(View.GONE);
            }
        });

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addMoney();
            }
        });

        remove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                removeMoney();
            }
        });

        return rootView;
    }

    @Override
    public void onStart() {
        super.onStart();
        NetworkingHelper api = new NetworkingHelper(BalanceFragment.this);
        api.GetDataContratEpargne(Utils.selectedContract.getNumeroPolice());
        loaderView.setVisibility(View.VISIBLE);
    }

    @Override
    public void onBalanceSuccess(Balance balance){

        loaderView.setVisibility(View.GONE);
        epargne.setText(balance.getCumuleVersementEpargne());
        risque.setText(balance.getMontantDeRisque());
        periode.setText(balance.getPeriodicite());
        cumul.setText(balance.getCumul_Versement());
        epargne_c.setText(balance.getCapitalConstitue());
        //Toast.makeText(getContext(),balance.getCapitalConstitue(),Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onBalanceError(String message){

        loaderView.setVisibility(View.GONE);
        //Toast.makeText(getContext(),message,Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onTokenError () {
        User.logoutUser(getActivity());
        Intent i = new Intent(getActivity(),LoginActivity.class);
        startActivity(i);
        getActivity().finishAffinity();
    }

    public void addMoney () {

        int value = Integer.parseInt(moneyEdit.getText().toString().trim());
        value+=10000;
        moneyEdit.setText(value+"");



    }

    public void removeMoney () {

        int value = Integer.parseInt(moneyEdit.getText().toString().trim());
        if (value > 100000) {

            value-=10000;
            moneyEdit.setText(value+"");


        }




    }

    public void onPay (View view) {

        Utils.fromQ = false;
        Intent i = new Intent(getActivity(),PayActivity.class);
        i.putExtra("montant",Double.parseDouble(moneyEdit.getText().toString().replace(" ","")));
        getActivity().startActivity(i);

    }


}
