package com.so.storage;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.material.snackbar.Snackbar;
import com.so.storage.common.SaveLogin;

// 마이 페이지 내 개인 정보 수정을 위한 비밀번호 확인 페이지
public class MyAccountSubFrag extends Fragment {
    MainActivity mActivity;
    Button btn_accountsub_pwchk;
    FragPrntIdPwFind fragPrntIdPwFind;
    MyAccountFrag myAccountFrag;
    EditText edt_accountSub;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mActivity = (MainActivity) getActivity();
        myAccountFrag = new MyAccountFrag();

        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.frag_my_account_sub, container, false);
        btn_accountsub_pwchk = rootView.findViewById(R.id.btn_accountsub_pwchk);
        edt_accountSub = rootView.findViewById(R.id.edt_accountSub);

        btn_accountsub_pwchk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String pwchk = edt_accountSub.getText().toString();

                SaveLogin saveLogin = new SaveLogin();
                String user_pw = saveLogin.getUserPw(mActivity);

                if(pwchk.equals(user_pw)) {
                    Snackbar.make(v, "비밀번호가 확인되었습니다.", Snackbar.LENGTH_SHORT).show();
                    mActivity.onFragmentChange(myAccountFrag);
                } else {
                    Snackbar.make(v, "비밀번호가 일치하지 않습니다.", Snackbar.LENGTH_LONG).show();
                    edt_accountSub.requestFocus();
                }
            } // onClick()
        });

        return rootView;
    } // onCreateView()
} // End of class
