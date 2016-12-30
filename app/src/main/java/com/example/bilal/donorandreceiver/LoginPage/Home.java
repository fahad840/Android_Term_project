package com.example.bilal.donorandreceiver.LoginPage;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.bilal.donorandreceiver.Donate.Donating;
import com.example.bilal.donorandreceiver.R;
import com.example.bilal.donorandreceiver.Receive.Receiving;

public class Home extends AppCompatActivity {
Button donate,Receive,Myreq;
    TextView textView;
    int i;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home2);
        donate= (Button) findViewById(R.id.Donatebtn);
        Receive= (Button) findViewById(R.id.Receivebtn);
        Myreq= (Button) findViewById(R.id.Myreqbtn);
        textView= (TextView) findViewById(R.id.welcome);
        final Intent intent=getIntent();
         i=intent.getIntExtra("idno",0);
        textView.setText("Welcome "+ String.valueOf(i).toString());
        donate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Home.this, Donating.class));
            }
        });
        Receive.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1=new Intent(Home.this, Receiving.class);
                intent1.putExtra("idno1",i);
                startActivity(intent1);
            }
        });
        Myreq.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent2=new Intent(Home.this,MyRequestPage.class);
                intent2.putExtra("idno2",i);
                startActivity(intent2);
            }
        });

    }
}
