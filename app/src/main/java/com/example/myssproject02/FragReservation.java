package com.example.myssproject02;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class FragReservation extends Fragment {
    MainActivity mActivity;
    ViewGroup rootView;
    FragReservationSubBox fragReservationSubBox;
    FragReservationSubCabi fragReservationSubCabi;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = (ViewGroup) inflater.inflate(R.layout.frag_reservation, container, false);
        mActivity = (MainActivity) getActivity();
        fragReservationSubBox = new FragReservationSubBox();
        fragReservationSubCabi = new FragReservationSubCabi();

        Spinner location = (Spinner) rootView.findViewById(R.id.location);
        ArrayAdapter locationAdapter = ArrayAdapter.createFromResource(getActivity(),
                R.array.location, android.R.layout.simple_spinner_item);
        locationAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        location.setAdapter(locationAdapter);

        rootView.findViewById(R.id.btn_box).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               /* Intent intent = new Intent(ReservationActivity.this, ReservationSubBox.class);
                startActivity(intent);*/
                mActivity.onFragmentChange(fragReservationSubBox);
            }
        });

        rootView.findViewById(R.id.btn_cabi).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               /* Intent intent = new Intent(ReservationActivity.this, ReservationSubCabi.class);
                startActivity(intent);*/
                mActivity.onFragmentChange(fragReservationSubCabi);
            }
        });

        return rootView;
    } // onCreateView()
} // class
