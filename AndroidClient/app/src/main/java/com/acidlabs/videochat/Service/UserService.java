package com.acidlabs.videochat.Service;

import android.content.Context;

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


public class UserService extends Service {
    public UserService(Context ctx) {
        super(ctx);
    }
    public UserService(Context ctx, Object rcv) {
        super(ctx, rcv);
    }

    public void register(String name, String email, String password){
        RequestQueue queue = Volley.newRequestQueue(context);
        String url = context.getResources().getString(R.string.server) + "/api/register";
        StringRequest postRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonResponse = new JSONObject(response);
                            if (jsonResponse.has("success")) {
                                JSONObject success = jsonResponse.getJSONObject("success");
                                String token = success.getString("token");
                                ((UserAsyncResponse) receiver).onAccessTokenReceived(token);
                            }
                            if (jsonResponse.has("error")) {
                                ((UserAsyncResponse) receiver).onRegisterError("Please verify your informations");
                            }
                        } catch (JSONException e) {
                            ((UserAsyncResponse) receiver).onAccessError("Unknown Error.");
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // error
                    }
                }
        ) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<>();
                params.put("name", name);
                params.put("email", email);
                params.put("password", password);
                params.put("c_password", password);
                return params;
            }
        };
        queue.add(postRequest);
    }

    public void getAccessToken(String email, String password) {
        RequestQueue queue = Volley.newRequestQueue(context);
        String url = context.getResources().getString(R.string.server) + "/api/login";
        StringRequest postRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonResponse = new JSONObject(response);
                            if (jsonResponse.has("success")) {
                                JSONObject success = jsonResponse.getJSONObject("success");
                                String token = success.getString("token");
                                ((UserAsyncResponse) receiver).onAccessTokenReceived(token);
                            }
                            if (jsonResponse.has("error")) {
                                ((UserAsyncResponse) receiver).onAccessError(jsonResponse.getString("error"));
                            }
                        } catch (JSONException e) {
                            ((UserAsyncResponse) receiver).onAccessError("Unknown Error.");
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // error
                    }
                }
        ) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<>();
                params.put("email", email);
                params.put("password", password);
                return params;
            }
        };
        queue.add(postRequest);
    }

    public void getUserDetails(String token){
        RequestQueue queue = Volley.newRequestQueue(context);
        String url = context.getResources().getString(R.string.server) + "/api/user/details";
        StringRequest postRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonResponse = new JSONObject(response);
                            if (jsonResponse.has("success")) {
                                JSONObject user = jsonResponse.getJSONObject("success");
                                ((UserAsyncResponse) receiver).onDetailsReceived(user);
                            }
                            if (jsonResponse.has("error")) {
                                ((UserAsyncResponse) receiver).onRegisterError("Please verify your informations");
                            }
                        } catch (JSONException e) {
                            ((UserAsyncResponse) receiver).onAccessError("getUserDetails: Parse Error.");
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // error
                    }
                }
        ) {
            @Override
            public Map<String, String> getHeaders() {
                Map<String, String> params = new HashMap<>();
                params.put("Accept", "application/json");
                params.put("Authorization", "Bearer "+token);
                return params;
            }
        };
        queue.add(postRequest);
    }

    public void followUser(String token, final int id){
        RequestQueue queue = Volley.newRequestQueue(context);
        String url = context.getResources().getString(R.string.server) + "/api/user/follow";
        StringRequest postRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonResponse = new JSONObject(response);
                            if (jsonResponse.has("success")) {
                                JSONObject success = jsonResponse.getJSONObject("success");
                                //todo parse response
                            }
                        } catch (JSONException e) {
                            ((UserAsyncResponse) receiver).onUserFollowed(id);
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // error
                    }
                }
        ) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<>();
                params.put("id", String.valueOf(id));
                return params;
            }
            @Override
            public Map<String, String> getHeaders() {
                Map<String, String> params = new HashMap<>();
                params.put("Accept", "application/json");
                params.put("Authorization", "Bearer "+token);
                return params;
            }
        };
        queue.add(postRequest);
    }
}
