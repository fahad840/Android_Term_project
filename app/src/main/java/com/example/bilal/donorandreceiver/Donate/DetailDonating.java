package com.example.bilal.donorandreceiver.Donate;

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
import com.example.bilal.donorandreceiver.Fragments.ListFragment;
import com.example.bilal.donorandreceiver.LoginPage.UpdatePage;
import com.example.bilal.donorandreceiver.R;
import com.example.bilal.donorandreceiver.Url;

import java.lang.reflect.Array;

public class DetailDonating extends AppCompatActivity {
    TextView txtblood, txtdes, txtloc;
    int id;
    String blood, loc, des, s;
    Button update, del;
    UpdatePage updatePage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_donating);
        txtblood = (TextView) findViewById(R.id.FragmentBlood);
        txtdes= (TextView) findViewById(R.id.FragmentDes);
        txtloc= (TextView) findViewById(R.id.FragmentLocation);
        update = (Button) findViewById(R.id.updatebtn);
        del= (Button) findViewById(R.id.deletebtn);

        final Intent intent=getIntent();
        blood=intent.getStringExtra("bloodpojo");
        des=intent.getStringExtra("despojo");
        loc=intent.getStringExtra("locationpojo");
        id=intent.getIntExtra("idpojo",0);
        s=String.valueOf(id);
        txtblood.setText(blood);
        txtdes.setText(des);
        txtloc.setText(loc);
        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1=new Intent(DetailDonating.this,UpdatePage.class);
                intent1.putExtra("idno",id);
                startActivity(intent1);
            }
        });
        del.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new AlertDialog.Builder(DetailDonating.this)
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
//
    }

}
