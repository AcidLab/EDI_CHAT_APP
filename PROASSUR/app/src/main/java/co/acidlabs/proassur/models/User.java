package co.acidlabs.proassur.models;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

import org.json.JSONObject;

public class User extends Activity {

    private String id;
    private String code_client;
    private String email;
    private String Type_Personne;
    private String nombreContrat;
    private String nombreSinistre;
    private String nombrePreDeclaration;
    private String nombreContratEnCours;
    private String nombreQuittanceImpaye;
    private String nombreQuittance;
    private String nombrePropositions;
    private String code_agence;
    private String adresse;
    private String identifiant;
    private String ville;
    private String cp_ville;
    private String pays;
    private String premier_responsable;
    private String TEL;
    private String GSM;
    private String FAX;
    private String profession;
    private String secteur_activite;
    private String code_tva;
    private String date_naissance;

    public User(String id,String code_client, String email, String type_Personne, String nombreContrat, String nombreSinistre, String nombrePreDeclaration, String nombreContratEnCours, String nombreQuittanceImpaye, String nombreQuittance, String nombrePropositions, String code_agence, String adresse, String identifiant, String ville, String cp_ville, String pays, String premier_responsable, String TEL, String GSM, String FAX, String profession, String secteur_activite, String code_tva, String date_naissance) {
        this.id = id;
        this.code_client = code_client;
        this.email = email;
        Type_Personne = type_Personne;
        this.nombreContrat = nombreContrat;
        this.nombreSinistre = nombreSinistre;
        this.nombrePreDeclaration = nombrePreDeclaration;
        this.nombreContratEnCours = nombreContratEnCours;
        this.nombreQuittanceImpaye = nombreQuittanceImpaye;
        this.nombreQuittance = nombreQuittance;
        this.nombrePropositions = nombrePropositions;
        this.code_agence = code_agence;
        this.adresse = adresse;
        this.identifiant = identifiant;
        this.ville = ville;
        this.cp_ville = cp_ville;
        this.pays = pays;
        this.premier_responsable = premier_responsable;
        this.TEL = TEL;
        this.GSM = GSM;
        this.FAX = FAX;
        this.profession = profession;
        this.secteur_activite = secteur_activite;
        this.code_tva = code_tva;
        this.date_naissance = date_naissance;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getType_Personne() {
        return Type_Personne;
    }

    public void setType_Personne(String type_Personne) {
        Type_Personne = type_Personne;
    }

    public String getNombreContrat() {
        return nombreContrat;
    }

    public void setNombreContrat(String nombreContrat) {
        this.nombreContrat = nombreContrat;
    }

    public String getNombreSinistre() {
        return nombreSinistre;
    }

    public void setNombreSinistre(String nombreSinistre) {
        this.nombreSinistre = nombreSinistre;
    }

    public String getNombrePreDeclaration() {
        return nombrePreDeclaration;
    }

    public void setNombrePreDeclaration(String nombrePreDeclaration) {
        this.nombrePreDeclaration = nombrePreDeclaration;
    }

    public String getNombreContratEnCours() {
        return nombreContratEnCours;
    }

    public void setNombreContratEnCours(String nombreContratEnCours) {
        this.nombreContratEnCours = nombreContratEnCours;
    }

    public String getNombreQuittanceImpaye() {
        return nombreQuittanceImpaye;
    }

    public void setNombreQuittanceImpaye(String nombreQuittanceImpaye) {
        this.nombreQuittanceImpaye = nombreQuittanceImpaye;
    }

    public String getNombreQuittance() {
        return nombreQuittance;
    }

    public void setNombreQuittance(String nombreQuittance) {
        this.nombreQuittance = nombreQuittance;
    }

    public String getNombrePropositions() {
        return nombrePropositions;
    }

    public void setNombrePropositions(String nombrePropositions) {
        this.nombrePropositions = nombrePropositions;
    }

    public String getCode_agence() {
        return code_agence;
    }

    public void setCode_agence(String code_agence) {
        this.code_agence = code_agence;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getIdentifiant() {
        return identifiant;
    }

    public void setIdentifiant(String identifiant) {
        this.identifiant = identifiant;
    }

    public String getVille() {
        return ville;
    }

    public void setVille(String ville) {
        this.ville = ville;
    }

    public String getCp_ville() {
        return cp_ville;
    }

    public void setCp_ville(String cp_ville) {
        this.cp_ville = cp_ville;
    }

    public String getPays() {
        return pays;
    }

    public void setPays(String pays) {
        this.pays = pays;
    }

    public String getPremier_responsable() {
        return premier_responsable;
    }

    public void setPremier_responsable(String premier_responsable) {
        this.premier_responsable = premier_responsable;
    }

    public String getTEL() {
        return TEL;
    }

    public void setTEL(String TEL) {
        this.TEL = TEL;
    }

    public String getGSM() {
        return GSM;
    }

    public void setGSM(String GSM) {
        this.GSM = GSM;
    }

    public String getFAX() {
        return FAX;
    }

    public void setFAX(String FAX) {
        this.FAX = FAX;
    }

    public String getProfession() {
        return profession;
    }

    public void setProfession(String profession) {
        this.profession = profession;
    }

    public String getSecteur_activite() {
        return secteur_activite;
    }

    public void setSecteur_activite(String secteur_activite) {
        this.secteur_activite = secteur_activite;
    }

    public String getCode_tva() {
        return code_tva;
    }

    public void setCode_tva(String code_tva) {
        this.code_tva = code_tva;
    }

    public String getDate_naissance() {
        return date_naissance;
    }

    public void setDate_naissance(String date_naissance) {
        this.date_naissance = date_naissance;
    }

    public String getCode_client() {
        return code_client;
    }

    public void setCode_client(String code_client) {
        this.code_client = code_client;
    }

    public static User parseFromResponse(JSONObject object) {

        String id = object.optString("id");
        String code_client = object.optString("code_client");
        String email = object.optString("email");
        String Type_Personne = object.optString("Type_Personne");
        String nombreContrat = object.optString("nombreContrat");
        String nombreSinistre = object.optString("nombreSinistre");
        String nombrePreDeclaration = object.optString("nombrePreDeclaration");
        String nombreContratEnCours = object.optString("nombreContratEnCours");
        String nombreQuittanceImpaye = object.optString("nombreQuittanceImpaye");
        String nombreQuittance = object.optString("nombreQuittance");
        String nombrePropositions = object.optString("nombrePropositions");
        String code_agence = object.optString("code_agence");
        String adresse = object.optString("adresse");
        String identifiant = object.optString("identifiant");
        String ville = object.optString("ville");
        String cp_ville = object.optString("cp_ville");
        String pays = object.optString("pays");
        String premier_responsable = object.optString("premier_responsable");
        String TEL = object.optString("TEL");
        String GSM = object.optString("GSM");
        String FAX = object.optString("FAX");
        String profession = object.optString("profession");
        String secteur_activite = object.optString("secteur_activite");
        String code_tva = object.optString("code_tva");
        String date_naissance = object.optString("date_naissance");

        User user = new User
                (id,code_client,
                        email,
                        Type_Personne,
                        nombreContrat,
                        nombreSinistre,
                        nombrePreDeclaration,
                        nombreContratEnCours,
                        nombreQuittanceImpaye,
                        nombreQuittance,
                        nombrePropositions,
                        code_agence,
                        adresse,
                        identifiant,
                        ville,
                        cp_ville,
                        pays,
                        premier_responsable,
                        TEL,
                        GSM,
                        FAX,
                        profession,
                        secteur_activite,
                        code_tva,
                        date_naissance);

        return user;

    }

    public void saveUser(Context context) {

        SharedPreferences sharedPref = context.getSharedPreferences("login_data", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString("id", this.id);
        editor.putString("code_client",this.code_client);
        editor.putString("email", this.email);
        editor.putString("Type_Personne", this.Type_Personne);
        editor.putString("nombreContrat", this.nombreContrat);
        editor.putString("nombreSinistre", this.nombreSinistre);
        editor.putString("nombrePreDeclaration", this.nombrePreDeclaration);
        editor.putString("nombreContratEnCours", this.nombreContratEnCours);
        editor.putString("nombreQuittanceImpaye", this.nombreQuittanceImpaye);
        editor.putString("nombreQuittance", this.nombreQuittance);
        editor.putString("nombrePropositions", this.nombrePropositions);
        editor.putString("code_agence", this.code_agence);
        editor.putString("adresse", this.adresse);
        editor.putString("identifiant", this.identifiant);
        editor.putString("ville", this.ville);
        editor.putString("cp_ville", this.cp_ville);
        editor.putString("pays", this.pays);
        editor.putString("premier_responsable", this.premier_responsable);
        editor.putString("TEL", this.TEL);
        editor.putString("GSM", this.GSM);
        editor.putString("FAX", this.FAX);
        editor.putString("profession", this.profession);
        editor.putString("secteur_activite", this.secteur_activite);
        editor.putString("code_tva", this.code_tva);
        editor.putString("date_naissance", this.date_naissance);
        editor.apply();

    }

    public static User getCurrentUser(Context context) {

        SharedPreferences sharedPref = context.getSharedPreferences("login_data", Context.MODE_PRIVATE);

        String id = sharedPref.getString("id", null);
        String code_client = sharedPref.getString("code_client",null);
        String email = sharedPref.getString("email", null);
        String Type_Personne = sharedPref.getString("Type_Personne", null);
        String nombreContrat = sharedPref.getString("nombreContrat", null);
        String nombreSinistre = sharedPref.getString("nombreSinistre", null);
        String nombrePreDeclaration = sharedPref.getString("nombrePreDeclaration", null);
        String nombreContratEnCours = sharedPref.getString("nombreContratEnCours", null);
        String nombreQuittanceImpaye = sharedPref.getString("nombreQuittanceImpaye", null);
        String nombreQuittance = sharedPref.getString("nombreQuittance", null);
        String nombrePropositions = sharedPref.getString("nombrePropositions", null);
        String code_agence = sharedPref.getString("code_agence", null);
        String adresse = sharedPref.getString("adresse", null);
        String identifiant = sharedPref.getString("identifiant", null);
        String ville = sharedPref.getString("ville", null);
        String cp_ville = sharedPref.getString("cp_ville", null);
        String pays = sharedPref.getString("pays", null);
        String premier_responsable = sharedPref.getString("premier_responsable", null);
        String TEL = sharedPref.getString("TEL", null);
        String GSM = sharedPref.getString("GSM", null);
        String FAX = sharedPref.getString("FAX", null);
        String profession = sharedPref.getString("profession", null);
        String secteur_activite = sharedPref.getString("secteur_activite", null);
        String code_tva = sharedPref.getString("code_tva", null);
        String date_naissance = sharedPref.getString("date_naissance", null);

        User user = new User
                (id,code_client,
                        email,
                        Type_Personne,
                        nombreContrat,
                        nombreSinistre,
                        nombrePreDeclaration,
                        nombreContratEnCours,
                        nombreQuittanceImpaye,
                        nombreQuittance,
                        nombrePropositions,
                        code_agence,
                        adresse,
                        identifiant,
                        ville,
                        cp_ville,
                        pays,
                        premier_responsable,
                        TEL,
                        GSM,
                        FAX,
                        profession,
                        secteur_activite,
                        code_tva,
                        date_naissance);
        if (identifiant == null) {

            return null;

        } else {

            return user;

        }


    }

    public static void logoutUser(Context context) {

        SharedPreferences preferences = context.getSharedPreferences("login_data", 0);
        preferences.edit().remove("id").commit();
        preferences.edit().remove("code_client").commit();

        preferences.edit().remove("email").commit();
        preferences.edit().remove("Type_Personne").commit();
        preferences.edit().remove("nombreContrat").commit();
        preferences.edit().remove("nombreSinistre").commit();
        preferences.edit().remove("nombrePreDeclaration").commit();
        preferences.edit().remove("nombreContratEnCours").commit();
        preferences.edit().remove("nombreQuittanceImpaye").commit();
        preferences.edit().remove("nombreQuittance").commit();
        preferences.edit().remove("nombrePropositions").commit();
        preferences.edit().remove("code_agence").commit();
        preferences.edit().remove("adresse").commit();
        preferences.edit().remove("identifiant").commit();
        preferences.edit().remove("ville").commit();
        preferences.edit().remove("cp_ville").commit();
        preferences.edit().remove("pays").commit();
        preferences.edit().remove("premier_responsable").commit();
        preferences.edit().remove("TEL").commit();
        preferences.edit().remove("GSM").commit();
        preferences.edit().remove("FAX").commit();
        preferences.edit().remove("profession").commit();
        preferences.edit().remove("secteur_activite").commit();
        preferences.edit().remove("code_tva").commit();
        preferences.edit().remove("date_naissance").commit();

    }

}

