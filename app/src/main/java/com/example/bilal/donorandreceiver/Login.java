package com.example.bilal.donorandreceiver;

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
import com.example.bilal.donorandreceiver.LoginPage.Home;
import com.example.bilal.donorandreceiver.LoginPage.Register;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Login extends AppCompatActivity {
EditText email,password;
Button login,register;
    Boolean flag=false;
    String u,p;
    JSONObject objname,objeemail,objpassword;
    String [] arrname,arremail,arrpassword;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
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
                    StringRequest stringRequest=new StringRequest(Request.Method.GET, "http://192.168.10.3/api/students", new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {

                            try {
                                JSONObject responseObject = new JSONObject(response);
                                JSONArray resultsArray = responseObject.getJSONArray("data");
                                arrname=new String[resultsArray.length()];
                                arremail=new String[resultsArray.length()];
                                arrpassword=new String[resultsArray.length()];

                                for (int i=0;i<resultsArray.length();i++)
                                {
                                    objname=resultsArray.getJSONObject(i);
                                    if (objname.has("name"))
                                    {
                                        arrname[i]=objname.getString("name");
                                    }
                                    objeemail=resultsArray.getJSONObject(i);
                                    if (objeemail.has("email"))
                                    {
                                        arremail[i]=objeemail.getString("email");
                                    }

                                    objpassword=resultsArray.getJSONObject(i);
                                    if (objpassword.has("password"))
                                    {
                                        arrpassword[i]=objpassword.getString("password");
                                    }

                                }
                              for (int j=0;j<arremail.length;j++)
                              {
                                  if (u.equals(arremail[j])&&p.equals(arrpassword[j]))
                                  {
                                      Toast.makeText(getApplicationContext(),"Sign in Successfull!!",Toast.LENGTH_LONG).show();
                                      Intent intent = new Intent(Login.this,Home.class);
                                      startActivity(intent);
                                      flag=true;
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
                requestQueue.add(stringRequest);



                }
            //}
        });

        register=(Button)findViewById(R.id.register);
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Login.this,Register.class);
                startActivity(intent);
            }
        });
    }
}
