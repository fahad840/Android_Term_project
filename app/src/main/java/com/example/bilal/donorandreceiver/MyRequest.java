package com.example.bilal.donorandreceiver;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.bilal.donorandreceiver.Model.DataLogin;
import com.example.bilal.donorandreceiver.Model.GSonData;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


public class MyRequest {
    public List<DataLogin> list;
    String strname,stremail,strpassword;
    GSonData gSonData=new GSonData();
    public void Load(Context context){
        list = new ArrayList<>();
        StringRequest stringRequest = new StringRequest(Request.Method.GET, Url.urluser, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject responseObject = new JSONObject(response);
                    JSONArray resultsArray = responseObject.getJSONArray("data");
                    DataLogin datum[] = new DataLogin[resultsArray.length()];
                    for (int i = 0; i < resultsArray.length(); i++) {
                        strname = resultsArray.getJSONObject(i).getString("name");
                        stremail = resultsArray.getJSONObject(i).getString("email");
                        strpassword = resultsArray.getJSONObject(i).getString("password");
                        datum[i] = new DataLogin();
                        datum[i].setName(strname);
                        datum[i].setEmail(stremail);
                        datum[i].setPassword(strpassword);
                        list.add(datum[i]);
                    }
                    gSonData.setData(list);

                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

        RequestQueue requestQueue = Volley.newRequestQueue(context);
        requestQueue.add(stringRequest);


    }
}
