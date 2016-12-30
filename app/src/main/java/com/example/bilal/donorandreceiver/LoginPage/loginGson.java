package com.example.bilal.donorandreceiver.LoginPage;

import android.content.Intent;
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
import com.example.bilal.donorandreceiver.Model.DataLogin;
import com.example.bilal.donorandreceiver.Model.GSonData;
import com.example.bilal.donorandreceiver.R;
import com.example.bilal.donorandreceiver.Url;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class loginGson extends AppCompatActivity {
    EditText email,password;
    Button login,register,Admin;
    GSonData gSonData=new GSonData();
    String u,p,stremail,strpassword;
    int strid;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_gson);

        email=(EditText)findViewById(R.id.EmailText);
        password=(EditText)findViewById(R.id.password);
        login=(Button)findViewById(R.id.login);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                u=email.getText().toString();
                p=password.getText().toString();
//                if (u.equals("")&&p.equals(""))
//                {
//                    Toast.makeText(getApplicationContext(),"Username and password is Empty",Toast.LENGTH_LONG).show();
//                }
//                else if(u.equals(""))
//                {
//                    Toast.makeText(getApplicationContext(),"Email is Empty",Toast.LENGTH_LONG).show();
//                }
//                else if(p.equals(""))
//                {
//                    Toast.makeText(getApplicationContext(),"Password is Empty",Toast.LENGTH_LONG).show();
//                }
//                else{
                StringRequest JSONRequest=new StringRequest(Request.Method.GET, Url.urluser, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        try {

                            JSONObject responseObject = new JSONObject(response);
                            JSONArray resultsArray = responseObject.getJSONArray("data");
                            DataLogin datum[]=new DataLogin[resultsArray.length()];

                            List<DataLogin> list=new ArrayList<>();
                            for (int i=0;i<resultsArray.length();i++)
                            {
                                strid=resultsArray.getJSONObject(i).getInt("id");
                                stremail=resultsArray.getJSONObject(i).getString("email");
                                strpassword=resultsArray.getJSONObject(i).getString("password");
                                datum[i]=new DataLogin();
                                datum[i].setEmail(stremail);
                                datum[i].setId(strid);
                                datum[i].setPassword(strpassword);
                                list.add(datum[i]);
                            }
                            gSonData.setData(list);
                            for (int j=0;j<resultsArray.length();j++)
                            {
                                if (u.equals(datum[j].getEmail())&&p.equals(datum[j].getPassword())){
                                    Toast.makeText(getApplication(),"Sign in successfull!!",Toast.LENGTH_LONG).show();
                                    Intent intent=new Intent(loginGson.this, Home.class);
                                    intent.putExtra("idno",datum[j].getId());
                                    startActivity(intent);

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
                //if (flag==false)
                // {
                //   Toast.makeText(getApplicationContext(),"Please Enter Valid Email and Password !",Toast.LENGTH_LONG).show();

                //}
                RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
                requestQueue.add(JSONRequest);



            }
            //}
        });

        register =(Button)findViewById(R.id.register);
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(loginGson.this,Register.class);
                startActivity(intent);
            }
        });

        Admin = (Button) findViewById(R.id.Adminbtn);
        Admin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(loginGson.this, com.example.bilal.donorandreceiver.LoginPage.Admin.class));
            }
        });
    }
}

