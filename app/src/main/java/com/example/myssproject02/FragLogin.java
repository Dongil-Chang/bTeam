package com.example.myssproject02;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class FragLogin extends Fragment {
    Button btn_login_join, btn_login_IdPwFind, extrabtn;
    MainActivity mActivity;
    ViewGroup rootView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        rootView =  (ViewGroup) inflater.inflate(R.layout.frag_login, container, false);

        mActivity = (MainActivity) getActivity();
        btn_login_join = rootView.findViewById(R.id.btn_login_join);
        btn_login_IdPwFind = rootView.findViewById(R.id.btn_login_IdPwFind);
        extrabtn = rootView.findViewById(R.id.extrabtn);

        btn_login_join.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*Intent intent = new Intent(LoginActivity.this, JoinActivity.class);
                startActivity(intent);*/
                mActivity.onFragmentChange("join");
            }
        });

        btn_login_IdPwFind.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*Intent intent = new Intent(LoginActivity.this, IdPwFindActivity.class);
                startActivity(intent);*/
                Intent intent = new Intent(getActivity(), IdPwFindActivity.class);
                startActivity(intent);
            }
        });

        extrabtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*Intent intent = new Intent(LoginActivity.this, MypageActivity.class);
                startActivity(intent);*/
            }
        });
        return rootView;
    } // onCreateView
} // class
