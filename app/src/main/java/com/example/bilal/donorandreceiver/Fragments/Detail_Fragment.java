package com.example.bilal.donorandreceiver.Fragments;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.bilal.donorandreceiver.Donate.DetailDonating;
import com.example.bilal.donorandreceiver.Donate.PojoReceiver;
import com.example.bilal.donorandreceiver.R;

import java.io.Serializable;

/**
 * A simple {@link Fragment} subclass.
 */
public class Detail_Fragment extends android.app.Fragment  {
View view;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        view= inflater.inflate(R.layout.fragment_detail_2, container, false);


        return  view;
    }

}
