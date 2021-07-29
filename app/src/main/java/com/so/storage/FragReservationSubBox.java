package com.so.storage;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;




public class FragReservationSubBox extends Fragment {
    ViewGroup rootView;
    MainActivity mActivity;
    FragReservationCheck fragReservationCheck;



    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView =  (ViewGroup) inflater.inflate(R.layout.frag_reservation_sub_box, container, false);
        mActivity = (MainActivity) getActivity();
        fragReservationCheck = new FragReservationCheck();

        rootView.findViewById(R.id.btn_reser_box).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               /* Intent intent = new Intent(ReservationActivity.this, ReservationSubBox.class);
                startActivity(intent);*/
                mActivity.onFragmentChange(fragReservationCheck);
            }
        });





        return rootView;
    } // onCreateView()

} // End of class