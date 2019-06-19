package co.acidlabs.lanala.adapters;

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
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import co.acidlabs.lanala.HomeActivity;
import co.acidlabs.lanala.R;
import co.acidlabs.lanala.helpers.Utils;
import co.acidlabs.lanala.models.Contract;

public class ContractAdapter extends RecyclerView.Adapter<ContractAdapter.MyViewHolder>  {

    private List<Contract> list = new ArrayList<>();

    public ContractAdapter(List<Contract> list) {
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

        TextView contract,product,date,status;
        Contract object;



        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            contract = itemView.findViewById(R.id.contract);
            product = itemView.findViewById(R.id.product);
            date = itemView.findViewById(R.id.date);
            status = itemView.findViewById(R.id.status);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    Intent i = new Intent(itemView.getContext(),HomeActivity.class);
                    Utils.selectedContract = object;
                    itemView.getContext().startActivity(i);
                }
            });


        }

        @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
        @SuppressLint({"SetTextI18n", "ResourceAsColor"})
        public void refreshUI() {

            contract.setText(object.getNumeroPolice());
            product.setText(object.getNomProduit());
            date.setText(object.getDate_du().split(" ")[0]+"\n au \n"+object.getDate_au().split(" ")[0]);

            if (object.getEtatPolice().equals("En cours")) {
                Drawable green = itemView.getResources().getDrawable(R.drawable.greencircle);
                status.setBackground(green);
            }

            else {
                status.setBackground(itemView.getResources().getDrawable(R.drawable.redcircle));
            }


        }
    }
}
