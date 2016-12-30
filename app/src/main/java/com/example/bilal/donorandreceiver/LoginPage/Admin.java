package com.example.bilal.donorandreceiver.LoginPage;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.example.bilal.donorandreceiver.MyRequest;
import com.example.bilal.donorandreceiver.R;

public class Admin extends AppCompatActivity {
    Button search;
    EditText editText;
    DatumAdapter datumAdapter;
    MyRequest myRequest=new MyRequest();
    ListView listview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);
        search = (Button) findViewById(R.id.searchbtn);
        editText = (EditText) findViewById(R.id.Entry);
        listview = (ListView) findViewById(R.id.Data);

        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MyRequest request=new MyRequest();
                request.Load(getApplicationContext());
                datumAdapter = new DatumAdapter(getApplication(), R.layout.adapter, myRequest.list);
                listview.setAdapter(datumAdapter);
            }



        });

    }
}
