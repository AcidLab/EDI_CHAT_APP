package co.acidlabs.lanala.models;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class QImpayed {

    private String numero_quittance;
    private String date_effet_quittance;
    private String date_du;
    private String date_au;
    private String solde_quittance;
    private String montant_quittance;

    public QImpayed(String numero_quittance, String date_effet_quittance, String date_du, String date_au, String solde_quittance, String montant_quittance) {
        this.numero_quittance = numero_quittance;
        this.date_effet_quittance = date_effet_quittance;
        this.date_du = date_du;
        this.date_au = date_au;
        this.solde_quittance = solde_quittance;
        this.montant_quittance = montant_quittance;
    }

    public String getNumero_quittance() {
        return numero_quittance;
    }

    public void setNumero_quittance(String numero_quittance) {
        this.numero_quittance = numero_quittance;
    }

    public String getDate_effet_quittance() {
        return date_effet_quittance;
    }

    public void setDate_effet_quittance(String date_effet_quittance) {
        this.date_effet_quittance = date_effet_quittance;
    }

    public String getDate_du() {
        return date_du;
    }

    public void setDate_du(String date_du) {
        this.date_du = date_du;
    }

    public String getDate_au() {
        return date_au;
    }

    public void setDate_au(String date_au) {
        this.date_au = date_au;
    }

    public String getSolde_quittance() {
        return solde_quittance;
    }

    public void setSolde_quittance(String solde_quittance) {
        this.solde_quittance = solde_quittance;
    }

    public String getMontant_quittance() {
        return montant_quittance;
    }

    public void setMontant_quittance(String montant_quittance) {
        this.montant_quittance = montant_quittance;
    }


    public static QImpayed parseObjectFromResponse (JSONObject object) {


        String numero_quittance = object.optString("numero_quittance");
        String date_effet_quittance = object.optString("date_effet_quittance");
        String date_du = object.optString("date_du");
        String date_au = object.optString("date_au");
        String solde_quittance = object.optString("solde_quittance");
        String montant_quittance = object.optString("montant_quittance");


        QImpayed qImpayed = new QImpayed(numero_quittance, date_effet_quittance, date_du, date_au, solde_quittance,montant_quittance);

        return qImpayed;

    }

    public static ArrayList<QImpayed> parseArrayFromResponse (JSONArray array) {

        ArrayList <QImpayed> list = new ArrayList<QImpayed>();

        for (int i = 0;i < array.length();i++) {

            JSONObject object = null;
            try {
                object = array.getJSONObject(i);
                list.add(parseObjectFromResponse(object));

            } catch (JSONException e) {
                e.printStackTrace();
            }


        }

        return list;



    }
}
