package com.example.bilal.donorandreceiver.Donate;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.bilal.donorandreceiver.Model.DataLogin;
import com.example.bilal.donorandreceiver.R;

import java.util.List;

/**
 * Created by Bilal on 12/28/2016.
 */

public class ReceiverAdapter extends ArrayAdapter {
    public ReceiverAdapter(Context context, int resource, List<PojoReceiver> objects) {
        super(context, resource, objects);
    }
    public View getView(int position, View convertView, ViewGroup parent) {
        View v=convertView;
        if (v==null){
            LayoutInflater vi;
            vi=LayoutInflater.from(getContext());
            v=vi.inflate(R.layout.adapter_receive,null);
        }
        PojoReceiver pojoReceiver= (PojoReceiver) getItem(position);

        TextView tblood=(TextView)v.findViewById(R.id.tbloodtype);
        TextView tloc=(TextView)v.findViewById(R.id.tlocation);
      TextView tdes=(TextView)v.findViewById(R.id.tdescription);

        if (tblood!=null)
        {
            tblood.setText(pojoReceiver.getBloodtype());
        }
        if (tloc!=null)
        {
            tloc.setText(pojoReceiver.getLocation());
        }
        if (tdes!=null)
        {
            tdes.setText(pojoReceiver.getDescription());
        }

        return v;
    }
}
