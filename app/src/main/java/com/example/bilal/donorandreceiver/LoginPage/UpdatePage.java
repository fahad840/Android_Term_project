package com.example.bilal.donorandreceiver.LoginPage;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.bilal.donorandreceiver.Donate.PojoReceiver;
import com.example.bilal.donorandreceiver.Donate.ReceiverAdapter;
import com.example.bilal.donorandreceiver.Donate.ReceiverData;
import com.example.bilal.donorandreceiver.R;
import com.example.bilal.donorandreceiver.Url;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class UpdatePage extends AppCompatActivity {
    ArrayList<PojoReceiver> list;
    EditText bloodtypeedit,locedit,desedit;
    String strbloodtype, strlocation, strdes,strsender,s;
    String b,l,d;
    Button Updatenow;
    int strid,id;
    ReceiverData gSonData=new ReceiverData();
    RequestQueue requestQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_page);
        bloodtypeedit= (EditText) findViewById(R.id.UpdateType);
        locedit= (EditText) findViewById(R.id.Updatelocation);
        desedit= (EditText) findViewById(R.id.UpdateDes);
        Updatenow= (Button) findViewById(R.id.UpdateNowbtn);
        Intent intent=getIntent();
        id=intent.getIntExtra("idno",0);
        s=String.valueOf(id);
        StringRequest stringRequest = new StringRequest(Request.Method.GET, Url.urlreceiver, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject responseObject = new JSONObject(response);
                    JSONArray resultsArray = responseObject.getJSONArray("data");
                    list=new ArrayList<>();
                    PojoReceiver pojoReceivers[] = new PojoReceiver[resultsArray.length()];
                    for (int i = 0; i < resultsArray.length(); i++) {
                        strbloodtype = resultsArray.getJSONObject(i).getString("bloodtype");
                        strlocation = resultsArray.getJSONObject(i).getString("location");
                        strdes = resultsArray.getJSONObject(i).getString("description");
                        strsender=resultsArray.getJSONObject(i).getString("sender");
                        strid=resultsArray.getJSONObject(i).getInt("id");
                        pojoReceivers[i] = new PojoReceiver();
                        pojoReceivers[i].setBloodtype(strbloodtype);
                        pojoReceivers[i].setLocation(strlocation);
                        pojoReceivers[i].setDescription(strdes);
                        pojoReceivers[i].setSender(strsender);
                        pojoReceivers[i].setId(strid);
                        list.add(pojoReceivers[i]);
                    }
                    gSonData.setData(list);
                   for (int j=0;j<resultsArray.length();j++)
                   {
                       if (id==pojoReceivers[j].getId())
                       {
                           bloodtypeedit.setText(pojoReceivers[j].getBloodtype());
                           locedit.setText(pojoReceivers[j].getLocation());
                           desedit.setText(pojoReceivers[j].getDescription());
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
        requestQueue = Volley.newRequestQueue(getApplicationContext());
        requestQueue.add(stringRequest);

        //Update Button
        Updatenow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                b=bloodtypeedit.getText().toString();
                l=locedit.getText().toString();
                d=desedit.getText().toString();
                StringRequest request=new StringRequest(Request.Method.PUT, Url.urlreceiver+id, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {


                        Toast.makeText(UpdatePage.this,response,Toast.LENGTH_LONG).show();

                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(UpdatePage.this,error.toString(),Toast.LENGTH_LONG).show();
                    }
                }){
                    protected Map<String,String> getParams()
                    {
                        Map<String,String> params =new HashMap<String, String>();

                        params.put("bloodtype",b);
                        params.put("location",l);
                        params.put("description",d);
                        params.put("sender",s);
                        return params;

                    }

                };
                requestQueue.add(request);
            }


        });



    }
}
