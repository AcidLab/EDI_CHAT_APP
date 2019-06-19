package co.acidlabs.proassur.adapters;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import co.acidlabs.proassur.R;
import co.acidlabs.proassur.helpers.Utils;
import co.acidlabs.proassur.models.Contract;
import co.acidlabs.proassur.networking.OTService;


public class ContractAdapter extends RecyclerView.Adapter<ContractAdapter.MyViewHolder>  {

    private List<Contract> list = new ArrayList<>();
    private OTService otService;

    public ContractAdapter(List<Contract> list,OTService otService) {

        this.otService = otService;
        this.list = list;
    }

    @NonNull
    @Override
    public ContractAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        View view = inflater.inflate(R.layout.cell_contract, viewGroup, false);

        return new ContractAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ContractAdapter.MyViewHolder holder, int position) {

        final Contract obj = list.get(position);
        holder.object = obj;
        holder.refreshUI();
    }

    @Override
    public int getItemCount() {
        return list.size();
    }


    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView contract,product,date;
        Contract object;



        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            contract = itemView.findViewById(R.id.contract);
            product = itemView.findViewById(R.id.product);
            date = itemView.findViewById(R.id.date);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    otService.requestSessionId();
                }
            });


        }

        @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
        @SuppressLint({"SetTextI18n", "ResourceAsColor"})
        public void refreshUI() {

            contract.setText(object.getNum_police());
            product.setText(object.getLibelle_branche());
            date.setText(object.getDate_effet().split(" ")[0]+"\n au \n"+object.getDate_expiration().split(" ")[0]);




        }
    }
}
