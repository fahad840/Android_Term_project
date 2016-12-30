package com.example.bilal.donorandreceiver.LoginPage;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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

public class Register extends AppCompatActivity {
EditText email,name,Password,phone;
    Button Signup;
    String n,e,p,num;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        name=(EditText)findViewById(R.id.NameText);
        email=(EditText)findViewById(R.id.EmailText);
         Password=(EditText)findViewById(R.id.PasswordText);
        phone= (EditText) findViewById(R.id.phoneedit);

        Signup=(Button)findViewById(R.id.signup);

        Signup.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                n=name.getText().toString();
                e=email.getText().toString();
                p=Password.getText().toString();
                num=phone.getText().toString();

                StringRequest stringRequest=new StringRequest(Request.Method.POST, Url.urluser, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {


                        Toast.makeText(Register.this,response,Toast.LENGTH_LONG).show();

                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(Register.this,error.toString(),Toast.LENGTH_LONG).show();
                    }
                }){
                    protected Map<String,String> getParams()
                    {
                        Map<String,String> params =new HashMap<String, String>();

                        params.put("name",n);
                        params.put("email",e);
                        params.put("password",p);
                        return params;

                    }

                };
                StringRequest stringRequest1=new StringRequest(Request.Method.POST, Url.urlphone, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {


                        Toast.makeText(Register.this,response,Toast.LENGTH_LONG).show();

                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(Register.this,error.toString(),Toast.LENGTH_LONG).show();
                    }
                }){
                    protected Map<String,String> getParams()
                    {
                        Map<String,String> params =new HashMap<String, String>();

                        params.put("phoneno",num);
                        return params;

                    }

                };

                RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
                requestQueue.add(stringRequest);
                requestQueue.add(stringRequest1);
            }

        });


    }
}
