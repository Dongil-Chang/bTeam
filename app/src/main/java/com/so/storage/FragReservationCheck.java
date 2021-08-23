package com.so.storage;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.material.snackbar.Snackbar;
import com.so.storage.ATask.JoinInsert;
import com.so.storage.ATask.ReserInsert;
import com.so.storage.DTO.MemberUserDTO;
import com.so.storage.DTO.ReservationDTO;

import static com.so.storage.MainActivity.loginDTO;
import static com.so.storage.MainActivity.reservationDTO;

public class FragReservationCheck extends Fragment {
    MainActivity mActivity;
    ViewGroup rootView;
    FragReservationFinal fragReservationFinal;
    TextView reser_check_id,reser_check_type,reser_check_start,reser_check_end;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = (ViewGroup) inflater.inflate(R.layout.frag_reservation_check, container, false);
        mActivity = (MainActivity) getActivity();



        fragReservationFinal = new FragReservationFinal();
        reser_check_id = rootView.findViewById(R.id.reser_check_id);
        reser_check_type = rootView.findViewById(R.id.reser_check_type);
        reser_check_start = rootView.findViewById(R.id.reser_check_start);
        reser_check_end = rootView.findViewById(R.id.reser_check_end);

        //예약내용 띄우기
        reser_check_id.setText(reservationDTO.getProduct_id());
        reser_check_type.setText(reservationDTO.getProduct_type());
        reser_check_start.setText(reservationDTO.getBooking_start());
        reser_check_end.setText(reservationDTO.getBooking_end());



        //reserInsert = new ReserInsert(reservationDTO);

        rootView.findViewById(R.id.btn_reser_check_next).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               /* Intent intent = new Intent(ReservationActivity.this, ReservationSubBox.class);
                startActivity(intent);*/
                // String use = "Y";
                ReservationDTO dto = new ReservationDTO(

                        reser_check_id.getText().toString(),
                        reser_check_type.getText().toString(),
                        loginDTO.getId(),
                        reser_check_start.getText().toString(),
                        reser_check_end.getText().toString()


                );
                ReserInsert reserInsert = new ReserInsert(dto);
                try {
                    reserInsert.execute().get();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                Snackbar.make(v, "예약이 완료되었습니다.", Snackbar.LENGTH_LONG).show();


                mActivity.onFragmentChange(fragReservationFinal);
            }
        });


        rootView.findViewById(R.id.btn_reser_check_back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mActivity.onBackPressed();
            }
        });
        return rootView;
    } // onCreateView()
}