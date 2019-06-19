package co.acidlabs.lanala.models;

import org.json.JSONObject;

public class Balance {

    private String ModeVersement;
    private String Cumul_Versement;
    private String capitalConstitue;
    private String CumuleVersementEpargne;
    private String MontantDeRisque;
    private String Periodicite;

    public Balance(String modeVersement, String cumul_Versement, String capitalConstitue, String cumuleVersementEpargne, String montantDeRisque, String periodicite) {
        ModeVersement = modeVersement;
        Cumul_Versement = cumul_Versement;
        this.capitalConstitue = capitalConstitue;
        CumuleVersementEpargne = cumuleVersementEpargne;
        MontantDeRisque = montantDeRisque;
        Periodicite = periodicite;
    }

    public String getModeVersement() {
        return ModeVersement;
    }

    public void setModeVersement(String modeVersement) {
        ModeVersement = modeVersement;
    }

    public String getCumul_Versement() {
        return Cumul_Versement;
    }

    public void setCumul_Versement(String cumul_Versement) {
        Cumul_Versement = cumul_Versement;
    }

    public String getCapitalConstitue() {
        return capitalConstitue;
    }

    public void setCapitalConstitue(String capitalConstitue) {
        this.capitalConstitue = capitalConstitue;
    }

    public String getCumuleVersementEpargne() {
        return CumuleVersementEpargne;
    }

    public void setCumuleVersementEpargne(String cumuleVersementEpargne) {
        CumuleVersementEpargne = cumuleVersementEpargne;
    }

    public String getMontantDeRisque() {
        return MontantDeRisque;
    }

    public void setMontantDeRisque(String montantDeRisque) {
        MontantDeRisque = montantDeRisque;
    }

    public String getPeriodicite() {
        return Periodicite;
    }

    public void setPeriodicite(String periodicite) {
        Periodicite = periodicite;
    }

    public static Balance parseObjectFromResponse (JSONObject object) {

        String ModeVersement = object.optString("ModeVersement");
        String Cumul_Versement = object.optString("Cumul_Versement");
        String capitalConstitue = object.optString("capitalConstitue");
        String cumuleVersementEpargne = object.optString("CumuleVersementEpargne");
        String montantDeRisque = object.optString("MontantDeRisque");
        String periodicite = object.optString("Periodicite");

        Balance balance = new Balance(ModeVersement,Cumul_Versement,capitalConstitue, cumuleVersementEpargne,  montantDeRisque,  periodicite);

        return balance;

    }
}
