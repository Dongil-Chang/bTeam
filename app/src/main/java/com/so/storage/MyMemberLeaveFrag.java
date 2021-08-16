package com.so.storage;

import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
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
import com.so.storage.ATask.LeaveDelete;
import com.so.storage.common.SaveLogin;

import static com.so.storage.MainActivity.loginDTO;

public class MyMemberLeaveFrag extends Fragment {
    Button btn_memberleave, btn_leave_pwchk;
    EditText edt_memberleave;
    MainActivity mActivity;
    FragMainPage fragMainPage;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.frag_my_member_leave, container, false);

        mActivity = (MainActivity) getActivity();
        fragMainPage = new FragMainPage();

        edt_memberleave = rootView.findViewById(R.id.edt_memberleave);
        btn_memberleave = rootView.findViewById(R.id.btn_memberleave);
        btn_leave_pwchk = rootView.findViewById(R.id.btn_leave_pwchk);

        //비밀번호 확인 버튼
        btn_leave_pwchk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String pwchk = edt_memberleave.getText().toString();

                SaveLogin saveLogin = new SaveLogin();
                String user_pw = saveLogin.getUserPw(mActivity);

                if(pwchk.equals(user_pw)) {
                    Snackbar.make(v, "비밀번호가 확인되었습니다.", Snackbar.LENGTH_LONG).show();
                    btn_memberleave.setVisibility(View.VISIBLE);
                } else {
                    Snackbar.make(v, "비밀번호가 일치하지 않습니다.", Snackbar.LENGTH_LONG).show();
                    edt_memberleave.requestFocus();
                }

            }
        });

        //비밀번호 확인 완료 후 생기는 탈퇴버튼
        btn_memberleave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(mActivity);
                builder.setMessage("정말 탈퇴하시겠습니까?");

                builder.setPositiveButton("탈퇴", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        SaveLogin saveLogin = new SaveLogin();
                        String id = loginDTO.getId();
                        LeaveDelete leaveDelete = new LeaveDelete(id);

                        try {
                            leaveDelete.execute().get();

                            Snackbar.make(v, "탈퇴가 완료되었습니다.", Snackbar.LENGTH_LONG).show();
                            mActivity.onFragmentChange(fragMainPage);
                            SharedPreferences.Editor logOut = saveLogin.logout(mActivity);
                            if(logOut != null) {
                                int loginstate = 0;
                                mActivity.LoginState(loginstate);
                                mActivity.menuOnOff("0", loginstate);

                                mActivity.btn_logout.setVisibility(View.GONE);
                                mActivity.btn_login.setVisibility(View.VISIBLE);

                            }

                        } catch (Exception e) {
                            e.printStackTrace();
                        }

                    }
                });

                builder.setNegativeButton("취소", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });

                AlertDialog dialog = builder.create();
                dialog.show();
            }
        });

        return rootView;
    }
}
