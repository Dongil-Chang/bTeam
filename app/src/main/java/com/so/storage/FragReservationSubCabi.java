package com.so.storage;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;


public class FragReservationSubCabi extends Fragment {
    ViewGroup rootView;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView =  (ViewGroup) inflater.inflate(R.layout.frag_reservation_sub_cabi, container, false);

        return rootView;
    } // onCreateView()


} // End of class
