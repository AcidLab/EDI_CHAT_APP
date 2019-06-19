package co.acidlabs.lanala.models;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class QHistory {

    private String numero_quittance;
    private String date_du;
    private String date_au;
    private String Date_Encaissement;
    private String Montant_payee;

    public QHistory(String numero_quittance, String date_du, String date_au, String date_Encaissement, String montant_payee) {
        this.numero_quittance = numero_quittance;
        this.date_du = date_du;
        this.date_au = date_au;
        Date_Encaissement = date_Encaissement;
        Montant_payee = montant_payee;
    }

    public String getNumero_quittance() {
        return numero_quittance;
    }

    public void setNumero_quittance(String numero_quittance) {
        this.numero_quittance = numero_quittance;
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

    public String getDate_Encaissement() {
        return Date_Encaissement;
    }

    public void setDate_Encaissement(String date_Encaissement) {
        Date_Encaissement = date_Encaissement;
    }

    public String getMontant_payee() {
        return Montant_payee;
    }

    public void setMontant_payee(String montant_payee) {
        Montant_payee = montant_payee;
    }

    public static QHistory parseObjectFromResponse (JSONObject object) {


        String numero_quittance = object.optString("NumeroQuittance");
        String date_du = object.optString("Date_du");
        String date_au = object.optString("Date_au");
        String Date_Encaissement = object.optString("Date_Encaissement");
        String Montant_payee = object.optString("Montant_payee");


        QHistory qHistory = new QHistory(numero_quittance, date_du, date_au, Date_Encaissement,Montant_payee);

        return qHistory;

    }

    public static ArrayList<QHistory> parseArrayFromResponse (JSONArray array) {

        ArrayList <QHistory> list = new ArrayList<QHistory>();

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
