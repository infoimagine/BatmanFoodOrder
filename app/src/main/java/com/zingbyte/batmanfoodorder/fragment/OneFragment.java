package com.zingbyte.batmanfoodorder.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.zingbyte.batmanfoodorder.R;

public class OneFragment extends android.support.v4.app.Fragment {

    View view;

    public OneFragment(){

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.item_one, container, false);
        return view;
    }
}
