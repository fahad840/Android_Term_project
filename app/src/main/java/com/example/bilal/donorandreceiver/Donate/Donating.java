package com.example.bilal.donorandreceiver.Donate;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.bilal.donorandreceiver.R;
import com.example.bilal.donorandreceiver.Url;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class Donating extends AppCompatActivity {
ListView listView;
    ArrayList<PojoReceiver> list;
    String strbloodtype, strlocation, strdes,strsender;
    ReceiverAdapter receiverAdapter;
    ReceiverData gSonData=new ReceiverData();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_donating);
        listView= (ListView) findViewById(R.id.recevierdata);
        list = new ArrayList<>();
        StringRequest stringRequest = new StringRequest(Request.Method.GET, Url.urlreceiver, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject responseObject = new JSONObject(response);
                    JSONArray resultsArray = responseObject.getJSONArray("data");
                   PojoReceiver pojoReceivers[] = new PojoReceiver[resultsArray.length()];
                    for (int i = 0; i < resultsArray.length(); i++) {
                        strbloodtype = resultsArray.getJSONObject(i).getString("bloodtype");
                        strlocation = resultsArray.getJSONObject(i).getString("location");
                        strdes = resultsArray.getJSONObject(i).getString("description");
                        strsender=resultsArray.getJSONObject(i).getString("sender");
                        pojoReceivers[i] = new PojoReceiver();
                        pojoReceivers[i].setBloodtype(strbloodtype);
                        pojoReceivers[i].setLocation(strlocation);
                       pojoReceivers[i].setDescription(strdes);
                        pojoReceivers[i].setSender(strsender);

                        list.add(pojoReceivers[i]);
                    }
                    gSonData.setData(list);
                    receiverAdapter=new ReceiverAdapter(getApplicationContext(),R.layout.adapter_receive,list);
                    listView.setAdapter(receiverAdapter);

                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }

        });

        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        requestQueue.add(stringRequest);

    }
}
