package com.example.bilal.donorandreceiver.LoginPage;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
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

public class DetailMyRequest extends AppCompatActivity {
    TextView txtblood, txtdes, txtloc;
    int id;
    String blood, loc, des, s;
    Button update, del;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_my_request);
        txtblood = (TextView) findViewById(R.id.bloodtxt);
        txtdes= (TextView) findViewById(R.id.myeqdestxt);
        txtloc= (TextView) findViewById(R.id.myreloctxt);
        update = (Button) findViewById(R.id.EditButton);
        del= (Button) findViewById(R.id.deletebutton);

       Intent intent=getIntent();
        blood=intent.getStringExtra("bloodpojo12");
        des=intent.getStringExtra("despojo12");
        loc=intent.getStringExtra("locationpojo12");
        id=intent.getIntExtra("pojoid",0);
        s=String.valueOf(id);
        txtblood.setText(blood);
        txtdes.setText(des);
        txtloc.setText(loc);
        Toast.makeText(getApplicationContext(),s,Toast.LENGTH_LONG).show();
        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1=new Intent(DetailMyRequest.this,UpdatePage.class);
                intent1.putExtra("idnumber",id);
                startActivity(intent1);
            }
        });
        del.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new AlertDialog.Builder(DetailMyRequest.this)
                        .setTitle("Delete entry")
                        .setMessage("Are you sure you want to delete this entry?")
                        .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                StringRequest stringRequest=new StringRequest(Request.Method.DELETE, Url.urlreceiver + id, new Response.Listener<String>() {
                                    @Override
                                    public void onResponse(String response) {
                                        Toast.makeText(getApplication(),"Delete Successfully !",Toast.LENGTH_LONG).show();
                                    }
                                }, new Response.ErrorListener() {
                                    @Override
                                    public void onErrorResponse(VolleyError error) {
                                        Toast.makeText(getApplicationContext(),error.toString(),Toast.LENGTH_LONG).show();
                                    }
                                });
                                RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
                                requestQueue.add(stringRequest);

                            }

                        })
                        .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                // do nothing
                            }
                        })
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .show();
            }
        });

    }
}
