package com.zee.myapplication;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * A simple {@link Fragment} subclass.
 * Use the  factory method to
 * create an instance of this fragment.
 */
public class HistoryFragment extends Fragment {
    int best1, best2, best3;
    SharedPreferences sh;
    TextView txtHistoryUpdate;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_history, container, false);
        sh=SharedPreferences.getInstance(getContext(),"GAMESCORES");
        getSH();
        txtHistoryUpdate= view.findViewById(R.id.txtHistoryUpdate);
        txtHistoryUpdate.setText( "TOP SCORES"+"\n"+"BEST 1: " + best1 + "\n" +
                "BEST 2: " + best2 + "\n" +
                "BEST 3: " + best3);
        return view;
    }
    private void getSH() {

        best1 = sh.getInteger("best1", 0);
        best2 = sh.getInteger("best2", 0);
        best3 = sh.getInteger("best3", 0);

    }
}