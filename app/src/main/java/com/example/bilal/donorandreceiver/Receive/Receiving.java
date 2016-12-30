package com.example.bilal.donorandreceiver.Receive;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.bilal.donorandreceiver.R;
import com.example.bilal.donorandreceiver.Url;

import java.util.HashMap;
import java.util.Map;

public class Receiving extends AppCompatActivity {
EditText location,descriptiontxt;
    Spinner bloodtype;
    String bloodid,des,loc;
    Button req;
     int id;
    String s;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_receiving);
        descriptiontxt = (EditText) findViewById(R.id.descriptionedit);
        location = (EditText) findViewById(R.id.locationedit);
        bloodtype= (Spinner) findViewById(R.id.bloodtypespin);
        req= (Button) findViewById(R.id.Sendreqbtn);
        Intent intent=getIntent();
       id=intent.getIntExtra("idno1",0);
        s=String.valueOf(id);


        bloodtype.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selectedItem = parent.getItemAtPosition(position).toString();
                if (selectedItem.equals("A-")) {
                    bloodid = "A-";
                }
                if (selectedItem.equals("A+")) {
                   bloodid="A+";
                }

                if (selectedItem.equals("B-")) {
                    bloodid="B-";
                }

                if (selectedItem.equals("B+")) {
                    bloodid="B+";
                }

                if (selectedItem.equals("AB+")) {
                    bloodid="AB+";
                }

                if (selectedItem.equals("AB-")) {
                    bloodid="AB-";
                }

                if (selectedItem.equals("O+")) {
                    bloodid="O+";
                }

                if (selectedItem.equals("O-")) {
                    bloodid="O-";
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        req.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                loc=location.getText().toString();
                des=descriptiontxt.getText().toString();

                StringRequest stringRequest = new StringRequest(Request.Method.POST, Url.urlreceiver, new Response.Listener<String>() {


                    @Override
                    public void onResponse(String response) {
                        Toast.makeText(getApplicationContext(), response, Toast.LENGTH_LONG).show();

                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(Receiving.this, error.toString(), Toast.LENGTH_LONG).show();

                    }
                }) {
                    protected Map<String, String> getParams() {

                        Map<String, String> params = new HashMap<String, String>();

                        params.put("description", des);
                        params.put("location", loc);
                        params.put("bloodtype", bloodid);
                        params.put("sender",s);
                        return params;
                    }


                };


                RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
                requestQueue.add(stringRequest);


            }


        });
    }
}
