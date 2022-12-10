package com.fro.home_autodoorcase;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;

import java.util.Objects;

public class Fragment_1 extends Fragment {


    /**
     * A simple {@link Fragment} subclass.
     */
    public Fragment_1() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        /* Inflate the layout for this fragment */



        return inflater.inflate(R.layout.fragment, container, false);
    }

    LinearLayout check;
    LinearLayout practiice;
    LinearLayout discuss;
    LinearLayout statistics;

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        check = (LinearLayout) getActivity().findViewById(R.id.check);
        practiice = (LinearLayout) getActivity().findViewById(R.id.practice);
        discuss = (LinearLayout) getActivity().findViewById(R.id.discuss);

        check.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), delete.class);
                startActivity(intent);
            }
        });

        practiice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), searcher.class);
                startActivity(intent);
            }
        });

        discuss.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), changepassword.class);
                startActivity(intent);
            }
        });

    }
}
