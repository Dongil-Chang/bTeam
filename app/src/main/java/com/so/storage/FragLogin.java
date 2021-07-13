package com.so.storage;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import com.google.android.material.snackbar.Snackbar;
import com.so.storage.DTO.MemberUserDTO;

public class FragLogin extends Fragment {
    Button btn_login_join, btn_login_IdPwFind, btn_login, btn_login_back;
    MainActivity mActivity;
    ViewGroup rootView;
    FragJoin fragJoin;
    FragPrntIdPwFind fragPrntIdPwFind;
    FragMainPage fragMainPage;
    EditText edt_login_id, edt_login_pw;

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
        btn_login = rootView.findViewById(R.id.btn_login);
        btn_login_back = rootView.findViewById(R.id.btn_login_back);
        edt_login_id = rootView.findViewById(R.id.edt_login_id);
        edt_login_pw = rootView.findViewById(R.id.edt_login_pw);

        //회원가입버튼
        btn_login_join.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //mActivity.onFragmentChange(fragJoin);
                AlertDialog.Builder builder = new AlertDialog.Builder(mActivity);
                builder.setMessage("회원가입 하시겠습니까?");

                builder.setPositiveButton("예", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        mActivity.onFragmentChange(fragJoin);
                    }
                });

                builder.setNegativeButton("아니오", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });

                AlertDialog dialog = builder.create();
                dialog.show();
            }
        });

        // 아이디/비밀번호 찾기 액티비티 → 프래그먼트로 변경
        btn_login_IdPwFind.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mActivity.onFragmentChange(fragPrntIdPwFind); // 프래그먼트 전환 메소드 호출
            } // onClick()
        });

        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MemberUserDTO dto = new MemberUserDTO();
                String id = edt_login_id.getText().toString();
                String pw = edt_login_pw.getText().toString();
                dto.setId(id);
                dto.setPw(pw);
                if(!edt_login_id.getText().toString().isEmpty() || !edt_login_pw.getText().toString().isEmpty()) {
                    if(id.equals("userid") && pw.equals("userpw")) {
                        Snackbar.make(v, "로그인 되었습니다.", Snackbar.LENGTH_LONG).show();
                        mActivity.onFragmentChange(fragMainPage);
                    } else {
                        Snackbar.make(v, "아이디, 비밀번호를 확인하세요.", Snackbar.LENGTH_LONG).show();
                        edt_login_id.setText(null);
                        edt_login_pw.setText(null);
                    }
                } else {
                    Snackbar.make(v, "아이디, 비밀번호를 확인하세요.", Snackbar.LENGTH_LONG).show();
                }
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
