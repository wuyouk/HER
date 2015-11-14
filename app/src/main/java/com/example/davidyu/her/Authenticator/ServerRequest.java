package com.example.davidyu.her.Authenticator;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.davidyu.her.Activities.MainActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * Class to connect to server
 */
public class ServerRequest {

    ProgressDialog progressDialog;
    public static final int CONNECTION_TIMEOUT = 1000 * 15;
    public static final String SERVER_ADDRESS = "http://i.cs.hku.hk/~sclee/android/";
    private Context context;
    private String username, password;
    private UserLocalStore userLocalStore;

    public ServerRequest(Context context){
        this.context = context;
        userLocalStore = new UserLocalStore(context);
    }

    //function to register user in database
    public void getUserFromDatabase(final String username, final String password){

        this.username = username;
        this.password = password;


        progressDialog = new ProgressDialog(context);
        progressDialog.setCancelable(false);
        progressDialog.setTitle("Processing");
        progressDialog.setMessage("Please wait...");
        progressDialog.show();

        RequestQueue queue = Volley.newRequestQueue(context);

        //parameters to be passed into volley POST request
        Map<String,String> params = new HashMap<>();
        params.put("username", username);
        params.put("password", password);

        CustomRequest jsonObjectRequest = new CustomRequest(Request.Method.POST,
                SERVER_ADDRESS + "getUserData.php",
                params,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {

                        JSONArray jsonArray;
                        JSONObject jsonObject;
                        String token = "", name = "";

                        try {
                            jsonArray = response.getJSONArray("user");
                            jsonObject = jsonArray.getJSONObject(0);
                            token = jsonObject.getString("token");
                            name = jsonObject.getString("name");

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                        Log.e("volley", "success");
                        Log.e("response token", token);
                        progressDialog.dismiss();

                        //save user credentials
                        User user = new User(username, password, token);
                        userLocalStore.storeUserData(user);
                        userLocalStore.setUserLoggedIn(true);

                        //remove from backstack
                        Intent i = new Intent(context, MainActivity.class);
                        i.setFlags(i.getFlags() | Intent.FLAG_ACTIVITY_NO_HISTORY);
                        context.startActivity(i);

                    }
                },

                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e("volley", "failure");
                        progressDialog.dismiss();
                    }
                });

        queue.add(jsonObjectRequest);

    }

    //function to register user
    public void registerUser(User user){
        progressDialog = new ProgressDialog(context);
        progressDialog.setCancelable(false);
        progressDialog.setTitle("Processing");
        progressDialog.setMessage("Please wait...");
        progressDialog.show();

        RequestQueue queue = Volley.newRequestQueue(context);

        //parameters to be passed into volley POST request
        Map<String,String> params = new HashMap<>();
        params.put("name", user.name);
        params.put("username", user.username);
        params.put("password", user.password);

        CustomRequest jsonObjectRequest = new CustomRequest(Request.Method.POST,
                SERVER_ADDRESS + "register.php",
                params,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {

                        progressDialog.dismiss();

                        Intent i = new Intent(context, LoginActivity.class);
                        i.setFlags(i.getFlags() | Intent.FLAG_ACTIVITY_NO_HISTORY);
                        context.startActivity(i);
                    }
                },

                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e("volley", "failure");
                        progressDialog.dismiss();
                    }
                });

        queue.add(jsonObjectRequest);
    }


    public void logoutUser(){

        RequestQueue queue = Volley.newRequestQueue(context);

        StringRequest strReq = new StringRequest(Request.Method.GET,
                SERVER_ADDRESS + "clearSession.php", new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                userLocalStore.clearUserData();
                userLocalStore.setUserLoggedIn(false);
            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

        queue.add(strReq);
    }


}
