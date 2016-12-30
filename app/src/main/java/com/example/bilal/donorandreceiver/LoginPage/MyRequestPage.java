package com.example.bilal.donorandreceiver.LoginPage;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.bilal.donorandreceiver.Donate.PojoReceiver;
import com.example.bilal.donorandreceiver.Donate.ReceiverAdapter;
import com.example.bilal.donorandreceiver.R;
import com.example.bilal.donorandreceiver.Url;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MyRequestPage extends AppCompatActivity {
String strid,strbloodtype,strlocation,strdes,strsender,strid1,strblood1,strloc;
    int id;
    ArrayList<PojoReceiver> list;
    ArrayList<PojoReceiver> list2;
    ReceiverAdapter receiverAdapter;
    ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_request_page);
        Intent intent=getIntent();
        id=intent.getIntExtra("idno2",0);
        strid=String.valueOf(id);
        list=new ArrayList<>();
        list2=new ArrayList<>();
        listView= (ListView) findViewById(R.id.Myreqlist);
        StringRequest stringRequest = new StringRequest(Request.Method.GET, Url.urlreceiver, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject responseObject = new JSONObject(response);
                    JSONArray resultsArray = responseObject.getJSONArray("data");
                    PojoReceiver pojoReceivers[] = new PojoReceiver[resultsArray.length()];
                    PojoReceiver pojo[]=new PojoReceiver[resultsArray.length()];
                    for (int i = 0; i < resultsArray.length(); i++) {

//                        strbloodtype = resultsArray.getJSONObject(i).getString("bloodtype");
//                        strlocation = resultsArray.getJSONObject(i).getString("location");
//                        strdes = resultsArray.getJSONObject(i).getString("description");
                        strsender=resultsArray.getJSONObject(i).getString("sender");
                        pojoReceivers[i] = new PojoReceiver();
//                        pojoReceivers[i].setBloodtype(strbloodtype);
//                        pojoReceivers[i].setLocation(strlocation);
//                        pojoReceivers[i].setDescription(strdes);
                        pojoReceivers[i].setSender(strsender);

                        list.add(pojoReceivers[i]);
                    }
                    for (int j=0;j<resultsArray.length();j++)
                    {
                        if (pojoReceivers[j].getSender().equals(strid))
                        {
                            strbloodtype = resultsArray.getJSONObject(j).getString("bloodtype");
                        strlocation = resultsArray.getJSONObject(j).getString("location");
                        strdes = resultsArray.getJSONObject(j).getString("description");
                            pojo[j]=new PojoReceiver();
                            pojo[j].setBloodtype(strbloodtype);
                            pojo[j].setDescription(strdes);
                            pojo[j].setLocation(strlocation);
                            list2.add(pojo[j]);

                        }
                    }
                    receiverAdapter=new ReceiverAdapter(getApplicationContext(),R.layout.adapter_receive,list2);
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


