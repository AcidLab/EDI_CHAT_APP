package co.acidlabs.lanala.networking;

import android.content.Context;
import android.support.v4.app.Fragment;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import co.acidlabs.lanala.helpers.Utils;
import co.acidlabs.lanala.models.Balance;
import co.acidlabs.lanala.models.Contract;
import co.acidlabs.lanala.models.QHistory;
import co.acidlabs.lanala.models.QImpayed;
import co.acidlabs.lanala.models.User;

public class NetworkingHelper {


    private Context context;
    private Fragment fragment;
    //private String url = "https://proassur.lanala-assurances.com/api.proassur_vie/api/";
    private String url = "https://edi.proassur.tn/api.proassur_vie/api/";

    public NetworkingHelper(Context context) {
        this.context = context;
    }

    public NetworkingHelper(Fragment fragment) {
        this.fragment = fragment;
    }



    public void login (final String login, final String password) {
        RequestQueue queue = Volley.newRequestQueue(context);
        String url = this.url + "wsassur/getUserByEmailAndPassword";

        StringRequest postRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {



                            JSONObject jsonResponse = new JSONObject(response);
                            if (jsonResponse.getInt("code") == 200) {


                                User user = User.parseFromResponse(jsonResponse);

                                ((NetworkingAsyncResponse) context).onLoginSuccess(user);

                            } else {

                                ((NetworkingAsyncResponse) context).onLoginError(jsonResponse.getString("message")+"ERR");

                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        ((NetworkingAsyncResponse) context).onLoginError(error.getMessage());
                    }
                }
        ) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<>();

                params.put("email", login);
                params.put("password", password);

                return params;
            }
        };
        queue.add(postRequest);
    }


    public void GetListPoliceVie () {

        RequestQueue queue = Volley.newRequestQueue(context);
        String url = this.url + "vie/GetListPoliceVie";

        StringRequest postRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonResponse = new JSONObject(response);
                            int code = jsonResponse.getInt("code");
                            if (code == 200) {


                                ArrayList<Contract> contracts = Contract.parseArrayFromResponse(jsonResponse.getJSONArray("ListContratVie"));

                                ((NetworkingAsyncResponse) context).onContractListSuccess(contracts);

                            }

                            else if (code == 502) {

                                ((NetworkingAsyncResponse) context).onTokenError();

                            }

                            else {

                                ((NetworkingAsyncResponse) context).onContractListError(jsonResponse.getString("message")+"ERR");

                            }

                        } catch (JSONException e) {
                            ((NetworkingAsyncResponse) context).onContractListError(e.getMessage());
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        ((NetworkingAsyncResponse) context).onContractListError(error.getMessage());
                    }
                }
        ) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<>();

                params.put("UserId", Utils.UserId);
                params.put("codeSession", Utils.sessionCode);

                return params;
            }
        };
        queue.add(postRequest);
    }



    public void GetDataContratEpargne (String police) {

        RequestQueue queue = Volley.newRequestQueue(fragment.getContext());
        String url = this.url + "contrat/GetDataContratEpargne";

        StringRequest postRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonResponse = new JSONObject(response);
                            int code = jsonResponse.getInt("code");
                            if (code == 200) {


                                Balance balance = Balance.parseObjectFromResponse(jsonResponse);

                                ((NetworkingAsyncResponse) fragment).onBalanceSuccess(balance);

                            }

                            else if (code == 502) {

                                ((NetworkingAsyncResponse) fragment).onTokenError();

                            }

                            else {

                                ((NetworkingAsyncResponse) fragment).onBalanceError(jsonResponse.getString("message"));

                            }

                        } catch (JSONException e) {
                            ((NetworkingAsyncResponse) fragment).onBalanceError(e.getMessage());
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        ((NetworkingAsyncResponse) fragment).onBalanceError(error.getMessage());
                    }
                }
        ) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<>();

                params.put("numero_police", police);
                params.put("code_client", Utils.UserId);
                params.put("codeSession", Utils.sessionCode);

                return params;
            }
        };
        queue.add(postRequest);
    }



    public void GetListeQuittancesImpayees (String police) {

        RequestQueue queue = Volley.newRequestQueue(fragment.getContext());
        String url = this.url + "vie/GetListeQuittancesImpayees";

        StringRequest postRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonResponse = new JSONObject(response);
                            int code = jsonResponse.getInt("code");
                            if (code == 200) {


                                ArrayList<QImpayed> qHistories = QImpayed.parseArrayFromResponse(jsonResponse.getJSONArray("quittancesImapyees"));

                                ((NetworkingAsyncResponse) fragment).onImpayedSuccess(qHistories);

                            }

                            else if (code == 502) {

                                ((NetworkingAsyncResponse) fragment).onTokenError();

                            }

                            else {

                                ((NetworkingAsyncResponse) fragment).onImpayedError(jsonResponse.getString("message")+"ERR");

                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                            ((NetworkingAsyncResponse) fragment).onImpayedError(e.getMessage());
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        ((NetworkingAsyncResponse) fragment).onImpayedError(error.getMessage());
                    }
                }
        ) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<>();

                params.put("ContratId", police);
                params.put("UserId", Utils.UserId);
                params.put("codeSession", Utils.sessionCode);

                return params;
            }
        };
        queue.add(postRequest);
    }



    public void GetHistoriqueVersement (String police) {

        RequestQueue queue = Volley.newRequestQueue(fragment.getContext());
        String url = this.url + "Vie/GetHistoriqueVersement";

        StringRequest postRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonResponse = new JSONObject(response);
                            int code = jsonResponse.getInt("code");
                            if (code == 200) {


                                ArrayList<QHistory> qHistories = QHistory.parseArrayFromResponse(jsonResponse.getJSONArray("ListEncaissements"));

                                ((NetworkingAsyncResponse) fragment).onHistorySuccess(qHistories);

                            }

                            else if (code == 502) {

                                ((NetworkingAsyncResponse) fragment).onTokenError();

                            }

                            else {

                                ((NetworkingAsyncResponse) fragment).onHistoryError(jsonResponse.getString("message")+"ERR");

                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                            ((NetworkingAsyncResponse) fragment).onHistoryError(e.getMessage());
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        ((NetworkingAsyncResponse) fragment).onHistoryError(error.getMessage());
                    }
                }
        ) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<>();

                params.put("ContratId", police);
                params.put("UserId", Utils.UserId);
                params.put("codeSession", Utils.sessionCode);

                return params;
            }
        };
        queue.add(postRequest);
    }




}
