package co.acidlabs.proassur.models;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class Contract {

    private String num_police;
    private String client;
    private String type_contrat;
    private String date_effet;
    private String date_expiration;
    private String libelle_sous_branche;
    private String libelle_branche;
    private String date_souscription;

    public Contract(String num_police, String client, String type_contrat, String date_effet, String date_expiration, String libelle_sous_branche, String libelle_branche, String date_souscription) {
        this.num_police = num_police;
        this.client = client;
        this.type_contrat = type_contrat;
        this.date_effet = date_effet;
        this.date_expiration = date_expiration;
        this.libelle_sous_branche = libelle_sous_branche;
        this.libelle_branche = libelle_branche;
        this.date_souscription = date_souscription;
    }

    public String getNum_police() {
        return num_police;
    }

    public void setNum_police(String num_police) {
        this.num_police = num_police;
    }

    public String getClient() {
        return client;
    }

    public void setClient(String client) {
        this.client = client;
    }

    public String getType_contrat() {
        return type_contrat;
    }

    public void setType_contrat(String type_contrat) {
        this.type_contrat = type_contrat;
    }

    public String getDate_effet() {
        return date_effet;
    }

    public void setDate_effet(String date_effet) {
        this.date_effet = date_effet;
    }

    public String getDate_expiration() {
        return date_expiration;
    }

    public void setDate_expiration(String date_expiration) {
        this.date_expiration = date_expiration;
    }

    public String getLibelle_sous_branche() {
        return libelle_sous_branche;
    }

    public void setLibelle_sous_branche(String libelle_sous_branche) {
        this.libelle_sous_branche = libelle_sous_branche;
    }

    public String getLibelle_branche() {
        return libelle_branche;
    }

    public void setLibelle_branche(String libelle_branche) {
        this.libelle_branche = libelle_branche;
    }

    public String getDate_souscription() {
        return date_souscription;
    }

    public void setDate_souscription(String date_souscription) {
        this.date_souscription = date_souscription;
    }

    public static Contract parseObjectFromResponse (JSONObject object) {


        String num_police = object.optString("num_police");
        String client = object.optString("client");
        String type_contrat = object.optString("type_contrat");
        String date_effet = object.optString("date_effet");
        String date_expiration = object.optString("date_expiration");
        String libelle_sous_branche = object.optString("libelle_sous_branche");
        String libelle_branche = object.optString("libelle_branche");
        String date_souscription = object.optString("date_souscription");



        Contract contract = new Contract(num_police,client,type_contrat,date_effet,date_expiration,libelle_sous_branche,libelle_branche,date_souscription);

        return contract;

    }



    public static ArrayList<Contract> parseArrayFromResponse (JSONArray array) {

        ArrayList <Contract> list = new ArrayList<Contract>();

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
