package co.acidlabs.lanala.models;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class Contract {

    private String NumeroPolice;
    private String NomProduit;
    private String date_du;
    private String date_au;
    private String DateNaissanceAssure;
    private String EtatPolice;
    private String NomAssuree;
    private String NomConseille;
    private String NumTelephoneConseille;
    private String BeneficiairesCasVie;
    private String BeneficiairesCasDeces;
    private String CapitalDeces;

    public Contract(String DateNaissanceAssure,String numeroPolice, String nomProduit, String date_du, String date_au, String etatPolice, String nomAssuree, String nomConseille, String numTelephoneConseille, String beneficiairesCasVie, String beneficiairesCasDeces, String capitalDeces) {
        NumeroPolice = numeroPolice;
        this.DateNaissanceAssure = DateNaissanceAssure;
        NomProduit = nomProduit;
        this.date_du = date_du;
        this.date_au = date_au;
        EtatPolice = etatPolice;
        NomAssuree = nomAssuree;
        NomConseille = nomConseille;
        NumTelephoneConseille = numTelephoneConseille;
        BeneficiairesCasVie = beneficiairesCasVie;
        BeneficiairesCasDeces = beneficiairesCasDeces;
        CapitalDeces = capitalDeces;
    }

    public String getNumeroPolice() {
        return NumeroPolice;
    }

    public void setNumeroPolice(String numeroPolice) {
        NumeroPolice = numeroPolice;
    }

    public String getNomProduit() {
        return NomProduit;
    }

    public void setNomProduit(String nomProduit) {
        NomProduit = nomProduit;
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

    public String getEtatPolice() {
        return EtatPolice;
    }

    public void setEtatPolice(String etatPolice) {
        EtatPolice = etatPolice;
    }

    public String getNomAssuree() {
        return NomAssuree;
    }

    public void setNomAssuree(String nomAssuree) {
        NomAssuree = nomAssuree;
    }

    public String getNomConseille() {
        return NomConseille;
    }

    public void setNomConseille(String nomConseille) {
        NomConseille = nomConseille;
    }

    public String getNumTelephoneConseille() {
        return NumTelephoneConseille;
    }

    public void setNumTelephoneConseille(String numTelephoneConseille) {
        NumTelephoneConseille = numTelephoneConseille;
    }

    public String getBeneficiairesCasVie() {
        return BeneficiairesCasVie;
    }

    public void setBeneficiairesCasVie(String beneficiairesCasVie) {
        BeneficiairesCasVie = beneficiairesCasVie;
    }

    public String getBeneficiairesCasDeces() {
        return BeneficiairesCasDeces;
    }

    public void setBeneficiairesCasDeces(String beneficiairesCasDeces) {
        BeneficiairesCasDeces = beneficiairesCasDeces;
    }

    public String getCapitalDeces() {
        return CapitalDeces;
    }

    public void setCapitalDeces(String capitalDeces) {
        CapitalDeces = capitalDeces;
    }

    public String getDateNaissanceAssure() {
        return DateNaissanceAssure;
    }

    public void setDateNaissanceAssure(String dateNaissanceAssure) {
        DateNaissanceAssure = dateNaissanceAssure;
    }

    public static Contract parseObjectFromResponse (JSONObject object) {


        String NumeroPolice = object.optString("NumeroPolice");
        String NomProduit = object.optString("NomProduit");
        String DateNaissanceAssure = object.optString("DateNaissanceAssure");
        String date_du = object.optString("date_du");
        String date_au = object.optString("date_au");
        String EtatPolice = object.optString("EtatPolice");
        String NomAssuree = object.optString("NomAssuree");
        String NomConseille = object.optString("NomConseille");
        String NumTelephoneConseille = object.optString("NumTelephoneConseille");
        String BeneficiairesCasVie = "-";

        try {
            JSONArray list = object.getJSONArray("BeneficiairesCasVie");

            for (int i = 0;i<list.length();i++) {

                if (i == 0) {
                    BeneficiairesCasVie = list.getJSONObject(i).optString("NomPrenom");
                }

                else {
                    BeneficiairesCasVie += " - "+list.getJSONObject(i).optString("NomPrenom");
                }

            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        String BeneficiairesCasDeces = object.optString("BeneficiairesCasDeces");
        String CapitalDeces = object.optString("CapitalDeces");

        Contract contract = new Contract(DateNaissanceAssure,NumeroPolice,NomProduit,date_du,date_au,EtatPolice,NomAssuree,NomConseille,NumTelephoneConseille,BeneficiairesCasVie,BeneficiairesCasDeces,CapitalDeces);

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
