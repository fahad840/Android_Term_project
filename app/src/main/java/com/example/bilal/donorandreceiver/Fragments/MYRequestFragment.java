package com.example.bilal.donorandreceiver.Fragments;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.bilal.donorandreceiver.Donate.DetailDonating;
import com.example.bilal.donorandreceiver.Donate.PojoReceiver;
import com.example.bilal.donorandreceiver.Donate.ReceiverAdapter;
import com.example.bilal.donorandreceiver.LoginPage.DetailMyRequest;
import com.example.bilal.donorandreceiver.R;
import com.example.bilal.donorandreceiver.Url;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class MYRequestFragment extends android.app.Fragment {

View view;
    int id;
    String strid,strbloodtype,strlocation,strdes,strsender;
    ArrayList<PojoReceiver> list;
    ArrayList<PojoReceiver> list2;
    ReceiverAdapter receiverAdapter;
    PojoReceiver pojoReceivers[];
    ListView listView;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view= inflater.inflate(R.layout.fragment_myrequest, container, false);

        Intent intent=getActivity().getIntent();
        id=intent.getIntExtra("idno2",0);
        strid=String.valueOf(id);
        list=new ArrayList<>();
        list2=new ArrayList<>();
        listView= (ListView)view.findViewById(R.id.MyReqList);
        StringRequest stringRequest = new StringRequest(Request.Method.GET, Url.urlreceiver, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject responseObject = new JSONObject(response);
                    JSONArray resultsArray = responseObject.getJSONArray("data");
                    pojoReceivers = new PojoReceiver[resultsArray.length()];
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
                            pojo[j].setId(resultsArray.getJSONObject(j).getInt("id"));
                            list2.add(pojo[j]);

                        }
                    }
                    receiverAdapter=new ReceiverAdapter(getActivity(),R.layout.adapter_receive,list2);
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


        RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
        requestQueue.add(stringRequest);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                pojoReceivers[0] = (PojoReceiver) listView.getItemAtPosition(position);
                Toast.makeText(getActivity(),"You selected : " + pojoReceivers[0].getBloodtype(),Toast.LENGTH_SHORT).show();

                Intent intent=new Intent(getActivity(),DetailMyRequest.class);
                intent.putExtra("bloodpojo12",pojoReceivers[0].getBloodtype());
                intent.putExtra("locationpojo12",pojoReceivers[0].getLocation());
                intent.putExtra("despojo12",pojoReceivers[0].getDescription());
                intent.putExtra("pojoid",pojoReceivers[0].getId());
                startActivity(intent);
            }
        });

        return view;
    }

}
