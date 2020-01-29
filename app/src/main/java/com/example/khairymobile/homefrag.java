package com.example.khairymobile;


import android.os.Bundle;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;


/**
 * A simple {@link Fragment} subclass.
 */
public class homefrag extends Fragment {


    public homefrag() {
        // Required empty public constructor
    }

    private CardView godonate,goevents;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_homefrag, container, false);
        // Inflate the layout for this fragment
        godonate=(CardView)view.findViewById(R.id.godonate);
        goevents = (CardView)view.findViewById(R.id.goevents);



        godonate.setOnClickListener(new View.OnClickListener() {
            @Override

            public void onClick(View v) {
                Toast.makeText(getActivity(), "جاري التبرع", Toast.LENGTH_SHORT).show();
            }
        });

        goevents.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(), "جاري المشاركة", Toast.LENGTH_SHORT).show();
            }
        });

        return view;


    }
}
