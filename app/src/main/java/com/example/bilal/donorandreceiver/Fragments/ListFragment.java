package com.example.bilal.donorandreceiver.Fragments;


import android.app.FragmentManager;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.FrameLayout;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.bilal.donorandreceiver.Donate.DetailDonating;
import com.example.bilal.donorandreceiver.Donate.Donating;
import com.example.bilal.donorandreceiver.Donate.PojoReceiver;
import com.example.bilal.donorandreceiver.Donate.ReceiverAdapter;
import com.example.bilal.donorandreceiver.Donate.ReceiverData;
import com.example.bilal.donorandreceiver.R;
import com.example.bilal.donorandreceiver.Url;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class ListFragment extends android.app.Fragment {
    ListView listView;
    ArrayList<PojoReceiver> list;
    String strbloodtype, strlocation, strdes, strsender;
    ReceiverAdapter receiverAdapter;
    PojoReceiver pojoReceivers[];
    ReceiverData gSonData = new ReceiverData();
    private boolean isRotate = false;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_list, container, false);
        listView = (ListView) view.findViewById(R.id.FragmentListview);
        list = new ArrayList<>();
        StringRequest stringRequest = new StringRequest(Request.Method.GET, Url.urlreceiver, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject responseObject = new JSONObject(response);
                    JSONArray resultsArray = responseObject.getJSONArray("data");
                    pojoReceivers = new PojoReceiver[resultsArray.length()];
                    for (int i = 0; i < resultsArray.length(); i++) {
                        strbloodtype = resultsArray.getJSONObject(i).getString("bloodtype");
                        strlocation = resultsArray.getJSONObject(i).getString("location");
                        strdes = resultsArray.getJSONObject(i).getString("description");
                        strsender = resultsArray.getJSONObject(i).getString("sender");
                        pojoReceivers[i] = new PojoReceiver();
                        pojoReceivers[i].setBloodtype(strbloodtype);
                        pojoReceivers[i].setLocation(strlocation);
                        pojoReceivers[i].setDescription(strdes);
                        pojoReceivers[i].setSender(strsender);
                        pojoReceivers[i].setId(resultsArray.getJSONObject(i).getInt("id"));

                        list.add(pojoReceivers[i]);
                    }
                    gSonData.setData(list);
                    receiverAdapter = new ReceiverAdapter(getActivity(), R.layout.adapter_receive, list);
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
                Intent intent=new Intent(getActivity(),DetailDonating.class);
                intent.putExtra("bloodpojo",pojoReceivers[0].getBloodtype());
                intent.putExtra("locationpojo",pojoReceivers[0].getLocation());
                intent.putExtra("despojo",pojoReceivers[0].getDescription());
                intent.putExtra("idpojo",pojoReceivers[0].getId());
                startActivity(intent);
            }
        });


        return view;
    }
}

