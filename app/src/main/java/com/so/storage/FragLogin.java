package com.so.storage;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class FragLogin extends Fragment {
    Button btn_login_join, btn_login_IdPwFind, extrabtn, btn_login_back;
    MainActivity mActivity;
    ViewGroup rootView;
    FragJoin fragJoin;
    FragPrntIdPwFind fragPrntIdPwFind;
    FragMainPage fragMainPage;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        fragPrntIdPwFind = new FragPrntIdPwFind();
        fragJoin = new FragJoin();
        fragMainPage = new FragMainPage();

        rootView =  (ViewGroup) inflater.inflate(R.layout.frag_login, container, false);

        mActivity = (MainActivity) getActivity();
        btn_login_join = rootView.findViewById(R.id.btn_login_join);
        btn_login_IdPwFind = rootView.findViewById(R.id.btn_login_IdPwFind);
        extrabtn = rootView.findViewById(R.id.extrabtn);
        btn_login_back = rootView.findViewById(R.id.btn_login_back);

        btn_login_join.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*Intent intent = new Intent(LoginActivity.this, JoinActivity.class);
                startActivity(intent);*/
                mActivity.onFragmentChange(fragJoin);
            }
        });

        // 아이디/비밀번호 찾기 액티비티 → 프래그먼트로 변경
        btn_login_IdPwFind.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*Intent intent = new Intent(LoginActivity.this, IdPwFindActivity.class);
                startActivity(intent);*/
                /*Intent intent = new Intent(getActivity(), IdPwFindActivity.class);
                startActivity(intent);*/
                mActivity.onFragmentChange(fragPrntIdPwFind); // 프래그먼트 전환 메소드 호출
            } // onClick()
        });

        extrabtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*Intent intent = new Intent(LoginActivity.this, MypageActivity.class);
                startActivity(intent);*/
            }
        });

        // 로그인 페이지 x 뒤로 가기 버튼 클릭시 메인 화면으로 돌아가는 이벤트
        btn_login_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mActivity.onFragmentChange(fragMainPage);
            }
        }); // btn_login_back

        return rootView;
    } // onCreateView
} // class
