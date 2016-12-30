package com.example.bilal.donorandreceiver.LoginPage;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.bilal.donorandreceiver.Model.DataLogin;
import com.example.bilal.donorandreceiver.R;

import java.util.List;

/**
 * Created by Bilal on 12/16/2016.
 */

public class DatumAdapter extends ArrayAdapter{

    public DatumAdapter(Context context, int resource, List<DataLogin> objects) {
        super(context, resource, objects);
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v=convertView;
        if (v==null){
            LayoutInflater vi;
            vi=LayoutInflater.from(getContext());
            v=vi.inflate(R.layout.adapter,null);
        }
        DataLogin datum= (DataLogin) getItem(position);

        TextView tname=(TextView)v.findViewById(R.id.adptname);
        TextView temail=(TextView)v.findViewById(R.id.adptemail);
        TextView tpassword=(TextView)v.findViewById(R.id.adptpassword);

        if (tname!=null)
        {
            tname.setText(datum.getName());
        }
        if (temail!=null)
        {
            temail.setText(datum.getEmail());
        }
        if (tpassword!=null)
        {
            tpassword.setText(datum.getPassword());
        }

        return v;
    }
}
