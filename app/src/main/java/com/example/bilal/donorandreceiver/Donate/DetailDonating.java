package com.example.bilal.donorandreceiver.Donate;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.bilal.donorandreceiver.LoginPage.UpdatePage;
import com.example.bilal.donorandreceiver.R;

import java.lang.reflect.Array;

public class DetailDonating extends AppCompatActivity {
TextView txtblood,txtdes,txtloc;
    int id;
    String blood,loc,des,s;
    Button update;
    UpdatePage updatePage;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_donating);
        txtblood= (TextView) findViewById(R.id.FragmentBlood);
        txtdes= (TextView) findViewById(R.id.FragmentDes);
        txtloc= (TextView) findViewById(R.id.FragmentLocation);
        update = (Button) findViewById(R.id.updatebtn);

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

    }
}
