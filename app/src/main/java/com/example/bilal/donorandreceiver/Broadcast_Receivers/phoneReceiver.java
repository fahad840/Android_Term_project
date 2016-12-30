package com.example.bilal.donorandreceiver.Broadcast_Receivers;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.bilal.donorandreceiver.Url;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class phoneReceiver extends BroadcastReceiver {
    String num[];
    public phoneReceiver() {
    }

    @Override
    public void onReceive(final Context context, Intent intent) {
        TelephonyManager telephony = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
        telephony.listen(new PhoneStateListener() {
            @Override
            public void onCallStateChanged(int state, final String incomingNumber) {
                super.onCallStateChanged(state, incomingNumber);
                System.out.println("incomingNumber : " + incomingNumber);
//               Toast.makeText(context, "Incoming call" + incomingNumber, Toast.LENGTH_LONG).show();
                StringRequest stringRequest=new StringRequest(Request.Method.GET, Url.urlphone, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        JSONObject responseObject = null;
                        try {
                            responseObject = new JSONObject(response);
                            JSONArray resultsArray = responseObject.getJSONArray("data");
                            num=new String[resultsArray.length()];
                            for (int i=0;i<resultsArray.length();i++)
                            {
                                num[i]=resultsArray.getJSONObject(i).getString("phoneno");
                            }
                            for (int j=0;j<resultsArray.length();j++) {
                                if (incomingNumber.equals(num[j]))
                                {
                                    Toast.makeText(context,"This a Donar",Toast.LENGTH_LONG).show();
                                }
                            }

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
        }, PhoneStateListener.LISTEN_CALL_STATE);

    }
}
