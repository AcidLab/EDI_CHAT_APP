package co.acidlabs.lanala.adapters;

import android.annotation.SuppressLint;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import co.acidlabs.lanala.R;
import co.acidlabs.lanala.models.QHistory;
import co.acidlabs.lanala.models.QImpayed;

public class HistoryAdapter extends RecyclerView.Adapter<HistoryAdapter.MyViewHolder>  {

    private List<QHistory> list = new ArrayList<>();

    public HistoryAdapter(List<QHistory> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public HistoryAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        View view = inflater.inflate(R.layout.cell_history, viewGroup, false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        final QHistory obj = list.get(position);
        holder.object = obj;
        holder.refreshUI();

    }

    @Override
    public int getItemCount() {
        return list.size();
    }


    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView code,money,date;
        QHistory object;


        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            code = itemView.findViewById(R.id.code);
            money = itemView.findViewById(R.id.money);
            date = itemView.findViewById(R.id.date);


        }

        @SuppressLint("SetTextI18n")
        public void refreshUI() {

            String code = "Indisponible";
            String money = code;
            String date = code;

            if (object.getNumero_quittance().length() > 1) {

                code = object.getNumero_quittance();

            }

            if (object.getMontant_payee().length() > 1) {

                money = object.getMontant_payee();

            }

            if (object.getDate_Encaissement().length() > 1) {

                date = object.getDate_Encaissement().split(" ")[0];

            }

            this.code.setText(code);
            this.money.setText(money);
            this.date.setText(date);


        }
    }
}
