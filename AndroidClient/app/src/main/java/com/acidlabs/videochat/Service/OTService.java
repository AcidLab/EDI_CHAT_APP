package com.acidlabs.videochat.Service;

import android.content.Context;
import android.os.Debug;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.tokbox.android.tutorials.basicvideochat.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class OTService {
    Context context;
    OTServiceListener delegate;

    public OTService(Context context){
        this.context = context;
        delegate = (OTServiceListener) context;
    }
    public OTService(Context context, OTServiceListener delegate){
        this.context = context;
        this.delegate = delegate;
    }

    public void requestSessionId(){
        RequestQueue queue = Volley.newRequestQueue(context);
        String url = context.getResources().getString(R.string.server) + "/ot/session";
        StringRequest postRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonResponse = new JSONObject(response);
                            delegate.onSessionIdReceived(jsonResponse.getString("sessionId"));
                        } catch (JSONException e) {
                            //todo: handle errors
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        delegate.onSessionIdError();
                        Log.d("SERVICE ERROR", "on requestSessionId: "+error.toString());
                    }
                }
        ) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<>();
                return params;
            }
        };
        queue.add(postRequest);
    }

    public void requestToken(final String sessionId){
        RequestQueue queue = Volley.newRequestQueue(context);
        String url = context.getResources().getString(R.string.server) + "/ot/token";
        StringRequest postRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonResponse = new JSONObject(response);
                            delegate.onTokenReceived(jsonResponse.getString("token"));
                        } catch (JSONException e) {
                            //todo: handle errors
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.d("SERVICE ERROR", "on requestToken: "+error.toString());
                    }
                }
        ) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<>();
                params.put("sessionId", sessionId);
                return params;
            }
        };
        queue.add(postRequest);
    }


}
