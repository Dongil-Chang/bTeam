package com.so.storage;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.util.Calendar;

import static com.so.storage.MainActivity.reservationDTO;

public class FragReservationDate extends Fragment  {
    MainActivity mActivity;
    ViewGroup rootView;
    FragReservationID fragReservationID;
    Calendar myCalender_start = Calendar.getInstance();
    Calendar myCalender_end = Calendar.getInstance();

    private static final String TAG = "reservation:";

    //시작날짜
    DatePickerDialog.OnDateSetListener myDatePicker_start = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
            myCalender_start.set(Calendar.YEAR, year);
            myCalender_start.set(Calendar.MONTH, month);
            myCalender_start.set(Calendar.DAY_OF_MONTH, dayOfMonth);

            String date_start = year + "년 " + (month+1) + "월 " + dayOfMonth+"일";
            updateLabelStart(date_start);
        }
    };
    //종료날짜
    DatePickerDialog.OnDateSetListener myDatePicker_end = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
            myCalender_end.set(Calendar.YEAR, year);
            myCalender_end.set(Calendar.MONTH, month);
            myCalender_end.set(Calendar.DAY_OF_MONTH, dayOfMonth);

            String date_end = year + "년 " + (month+1)+ "월 " + dayOfMonth+"일";
            updateLabelEnd(date_end);
        }
    };
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = (ViewGroup) inflater.inflate(R.layout.frag_reservation_date, container, false);
        mActivity = (MainActivity) getActivity();
        fragReservationID = new FragReservationID();
        //시작날짜 텍스트
        EditText et_start = (EditText) rootView.findViewById(R.id.reser_booking_start);
        et_start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DatePickerDialog(getContext(),myDatePicker_start,myCalender_start.get(Calendar.YEAR),
                        myCalender_start.get(Calendar.MONTH), myCalender_start.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

        //종료날짜 텍스트
        EditText et_end = (EditText) rootView.findViewById(R.id.reser_booking_end);
        et_end.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DatePickerDialog(getContext(),myDatePicker_end,myCalender_end.get(Calendar.YEAR),
                        myCalender_end.get(Calendar.MONTH), myCalender_end.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

        //다음단계 버튼
        rootView.findViewById(R.id.btn_reser_main).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               /* Intent intent = new Intent(ReservationActivity.this, ReservationSubBox.class);
                startActivity(intent);*/
                mActivity.onFragmentChange(fragReservationID);
               //et_end.setText(null);
                //et_start.setText(null);
            }
        });
        return rootView;
    } // onCreateView()

    //시작날짜값 띄우고 값저장
    private void updateLabelStart(String date_start){
        EditText et_start = (EditText) rootView.findViewById(R.id.reser_booking_start);
        et_start.setText(date_start);
        reservationDTO.setBooking_start(et_start.getText().toString());

    }

    //종료날짜값 띄우고 값저장
    private void updateLabelEnd(String date_end){
        EditText et_end = (EditText) rootView.findViewById(R.id.reser_booking_end);
        et_end.setText(date_end);
        reservationDTO.setBooking_end(et_end.getText().toString());
    }
} // class
