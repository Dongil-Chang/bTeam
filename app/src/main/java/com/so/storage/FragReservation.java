package com.so.storage;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.so.storage.DTO.ReserDTO;

import java.text.SimpleDateFormat;
import java.time.Month;
import java.util.Calendar;
import java.util.Locale;


public class FragReservation extends Fragment  {
    MainActivity mActivity;
    ViewGroup rootView;
    FragReservationSub fragReservationSub;
    ReserDTO dto;
    Calendar myCalender_start = Calendar.getInstance();
    Calendar myCalender_end = Calendar.getInstance();


    //시작날짜
    DatePickerDialog.OnDateSetListener myDatePicker_start = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
            myCalender_start.set(Calendar.YEAR, year);
            myCalender_start.set(Calendar.MONTH, month);
            myCalender_start.set(Calendar.DAY_OF_MONTH, dayOfMonth);
            updateLabelStart();


            String date = year + "년 " + (month+1) + "월 " + dayOfMonth+"일";
            Toast.makeText(getActivity(), date, Toast.LENGTH_SHORT).show();
            //dto.setStart_date(date);

        }
    };
    //종료날짜
    DatePickerDialog.OnDateSetListener myDatePicker_end = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
            myCalender_end.set(Calendar.YEAR, year);
            myCalender_end.set(Calendar.MONTH, month);
            myCalender_end.set(Calendar.DAY_OF_MONTH, dayOfMonth);
            updateLabelEnd();

            String date = year + "년 " + (month+1)+ "월 " + dayOfMonth+"일";
            //dto.setEnd_date(date);
            Toast.makeText(getActivity(), date, Toast.LENGTH_SHORT).show();

        }
    };




    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = (ViewGroup) inflater.inflate(R.layout.frag_reservation, container, false);
        mActivity = (MainActivity) getActivity();
        fragReservationSub = new FragReservationSub();
        ReserDTO dto = new ReserDTO();

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
                mActivity.onFragmentChange(fragReservationSub);
            }
        });
        return rootView;
    } // onCreateView()

    private void updateLabelStart(){
        String myFormat = "yyyy년 MM월 dd일";
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.KOREA);

        EditText et_start = (EditText) rootView.findViewById(R.id.reser_booking_start);
        et_start.setText(sdf.format(myCalender_start.getTime()));

    }

    private void updateLabelEnd(){
        String myFormat = "yyyy년 MM월 dd일";
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.KOREA);

        EditText et_end = (EditText) rootView.findViewById(R.id.reser_booking_end);
        et_end.setText(sdf.format(myCalender_end.getTime()));
    }


} // class
