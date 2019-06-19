package co.acidlabs.lanala.helpers;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import co.acidlabs.lanala.models.Contract;
import co.acidlabs.lanala.models.QImpayed;
import co.acidlabs.lanala.models.User;

public class Utils {

    public static String login = "";
    public static String UserId = "";
    public static String sessionCode = "";
    public static Contract selectedContract;
    public static ArrayList<QImpayed> selectedBox = new ArrayList<QImpayed>();
    public static User user;
    public static boolean fromQ = true;

    public static JSONObject QtoPay () {

        JSONObject object = new JSONObject();
        JSONArray array = new JSONArray();



        try {

            for (int i = 0;i<selectedBox.size();i++) {

                JSONObject Q = new JSONObject();
                Q.put("numero_quittance",selectedBox.get(i).getNumero_quittance());
                Q.put("montant",Double.parseDouble(selectedBox.get(i).getSolde_quittance().replaceAll(" ","")));
                array.put(Q);


            }

            object.put("code_client",UserId);
            object.put("codeSession",sessionCode);
            object.put("ListQuittance",array);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return  object;
    }

    public static JSONObject LtoPay (String numero,double montant) {

        JSONObject object = new JSONObject();



        try {



            object.put("code_client",UserId);
            object.put("codeSession",sessionCode);
            object.put("IsCotisationLibre",true);
            object.put("numeroPolice",numero);
            object.put("montantCotisation",montant);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return  object;
    }
}
