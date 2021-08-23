package com.so.storage;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class FragReservationType extends Fragment {

    MainActivity mActivity;
    ViewGroup rootView;
    FragReservationBox fragReservationBox;
    FragReservationCabi fragReservationCabi;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = (ViewGroup) inflater.inflate(R.layout.frag_reservation_type, container, false);
        mActivity = (MainActivity) getActivity();
        fragReservationBox = new FragReservationBox();
        fragReservationCabi = new FragReservationCabi();



        rootView.findViewById(R.id.reser_type_s).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               // mActivity.onFragmentChange(fragReservationBox);
            }
        });

        rootView.findViewById(R.id.reser_type_m).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               // mActivity.onFragmentChange(fragReservationCabi);

            }
        });
        rootView.findViewById(R.id.btn_reser_type_back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mActivity.onBackPressed();
            }
        });
        return rootView;
    }
}