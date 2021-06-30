package com.so.storage;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class FragJoin extends Fragment {
MainActivity mActivity;
Button btn_join_back, btn_postsearch, btn_join_submit;
int year = 0, month = 0, day = 0;
String date;
private static final int SEARCH_ADDRESS_ACTIVITY = 10000;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewGroup rootView =  (ViewGroup) inflater.inflate(R.layout.frag_join, container, false);
        mActivity = (MainActivity) getActivity();
        Fragment fragMainPage = new FragMainPage();
        btn_join_back = rootView.findViewById(R.id.btn_join_back);
        btn_postsearch = rootView.findViewById(R.id.btn_postsearch);
        btn_join_submit = rootView.findViewById(R.id.btn_join_submit);
        DatePicker datePicker = (DatePicker) rootView.findViewById(R.id.date_picker);

        datePicker.init(year, month, day, new DatePicker.OnDateChangedListener() {
            @Override
            public void onDateChanged(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                date = year + "/" + monthOfYear + "/" + dayOfMonth;
            } // onDateChanged()
        });

        // 우편번호 검색 버튼 클릭 이벤트
        btn_postsearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            } // onClick()
        });


        // 회원가입 페이지 x 뒤로 가기 버튼 클릭시 메인 화면으로 돌아가는 이벤트
        btn_join_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mActivity.onFragmentChange(fragMainPage);
            } // onClick()
        }); // btn_login_back
        return rootView;
    } // onCreateView
} // class
