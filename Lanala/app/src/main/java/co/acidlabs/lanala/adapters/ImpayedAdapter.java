package co.acidlabs.lanala.adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import co.acidlabs.lanala.R;
import co.acidlabs.lanala.helpers.DelegateObserver;
import co.acidlabs.lanala.helpers.Utils;
import co.acidlabs.lanala.models.Contract;
import co.acidlabs.lanala.models.QImpayed;
import co.acidlabs.lanala.networking.NetworkingAsyncResponse;

public class ImpayedAdapter extends RecyclerView.Adapter<ImpayedAdapter.MyViewHolder>  {

    private List<QImpayed> list = new ArrayList<>();
    Fragment fragment;

    public ImpayedAdapter(List<QImpayed> list, Fragment fragment) {
        this.list = list;
        this.fragment = fragment;
    }

    @NonNull
    @Override
    public ImpayedAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        View view = inflater.inflate(R.layout.cell_impayed, viewGroup, false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        final QImpayed obj = list.get(position);
        holder.object = obj;
        holder.refreshUI();


    }

    @Override
    public int getItemCount() {
        return list.size();
    }


    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView code,money,date;
        CheckBox checkbox;
        QImpayed object;


        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            code = itemView.findViewById(R.id.code);
            money = itemView.findViewById(R.id.money);
            date = itemView.findViewById(R.id.date);
            checkbox = itemView.findViewById(R.id.checkBox);

            checkbox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton compoundButton, boolean b) {

                    if (b) {

                        Utils.selectedBox.add(object);

                    }

                    else {

                        Utils.selectedBox.remove(object);

                    }
                    ((DelegateObserver) fragment).onCheckChange();
                }
            });


        }

        @SuppressLint("SetTextI18n")
        public void refreshUI() {

            code.setText(object.getNumero_quittance());
            money.setText(object.getMontant_quittance());
            date.setText(object.getDate_au().split(" ")[0]);




        }
    }
}
