package com.so.storage;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import static com.so.storage.MainActivity.reservationDTO;


public class FragReservationID extends Fragment {

    MainActivity mActivity;
    ViewGroup rootView;
   // FragReservationBox fragReservationBox;
  //  FragReservationCabi fragReservationCabi;
    FragReservationType fragReservationType;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = (ViewGroup) inflater.inflate(R.layout.frag_reservation_id, container, false);
        mActivity = (MainActivity) getActivity();
     //   fragReservationBox = new FragReservationBox();
    //    fragReservationCabi = new FragReservationCabi();
        fragReservationType = new FragReservationType();

        rootView.findViewById(R.id.btn_box).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String box = "Box";
                reservationDTO.setProduct_id(box);
                //Toast.makeText(getActivity(), reservationDTO.getProduct_id(), Toast.LENGTH_SHORT).show();
                mActivity.onFragmentChange(fragReservationType);
            }
        });

        rootView.findViewById(R.id.btn_cabi).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String cabi = "Cabi";
                reservationDTO.setProduct_id(cabi);
                mActivity.onFragmentChange(fragReservationType);

            }
        });
        rootView.findViewById(R.id.btn_reser_sub_back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mActivity.onBackPressed();
            }
        });
        return rootView;
    } // onCreateView()
}