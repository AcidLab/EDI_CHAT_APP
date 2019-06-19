package acidlabs.com.otinvited.networking;

import android.content.Context;
import android.support.v4.app.Fragment;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.onesignal.OneSignal;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import acidlabs.com.otinvited.helpers.Utils;
import acidlabs.com.otinvited.models.User;


public class NetworkingHelper {


    private Context context;
    private Fragment fragment;
    private String url = "https://edi.proassur.tn/api.proassur_vie/api/";

    public NetworkingHelper(Context context) {
        this.context = context;
    }

    public NetworkingHelper(Fragment fragment) {
        this.fragment = fragment;
    }



    public void login (final String login, final String password) {
        RequestQueue queue = Volley.newRequestQueue(context);
        String url = this.url + "wsassur/UserLogin";

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
                params.put("IdAppareil", OneSignal.getPermissionSubscriptionState().getSubscriptionStatus().getUserId());
                params.put("TypeAppareil", "Android");

                return params;
            }
        };
        queue.add(postRequest);
    }









}
