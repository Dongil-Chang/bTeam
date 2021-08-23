package com.so.storage;

import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import com.google.android.material.snackbar.Snackbar;
import com.so.storage.ATask.AccountUpdate;
import com.so.storage.DTO.MemberUserDTO;
import com.so.storage.common.SaveLogin;

import static com.so.storage.MainActivity.loginDTO;


// 마이 페이지 내 개인 정보 수정 실제 페이지
public class MyAccountFrag extends Fragment {
    MainActivity mActivity;
    DatePicker datePicker_account;
    EditText edt_account_pw, edt_account_pwchk, edt_account_name, edt_account_email, edt_account_tel, edt_account_addr3;
    TextView txtv_account_id, txtv_account_pw, txtv_account_pwchk, txtv_account_name, txtv_account_email, txtv_account_tel, myaccount_name, txtv_account_addr1, txtv_account_addr2, txtv_account_birth;
    Button btn_account_back, btn_account_update;
    MyAccountSubFrag fragAccountSub;
    FragMainPage fragMainPage;

    private final TextWatcher textWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            mActivity.account_validityChk();
        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    };

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.frag_my_account, container, false);
        mActivity = (MainActivity) getActivity();
        fragMainPage = new FragMainPage();
        fragAccountSub = new MyAccountSubFrag();

        edt_account_pw = rootView.findViewById(R.id.edt_account_pw);
        edt_account_pwchk = rootView.findViewById(R.id.edt_account_pwchk);
        edt_account_name = rootView.findViewById(R.id.edt_account_name);
        edt_account_email = rootView.findViewById(R.id.edt_account_email);
        edt_account_tel = rootView.findViewById(R.id.edt_account_tel);
        edt_account_addr3 = rootView.findViewById(R.id.edt_account_addr3);

        txtv_account_pw = rootView.findViewById(R.id.txtv_account_pw);
        txtv_account_pwchk = rootView.findViewById(R.id.txtv_account_pwchk);
        txtv_account_name = rootView.findViewById(R.id.txtv_account_name);
        txtv_account_email = rootView.findViewById(R.id.txtv_account_email);
        txtv_account_tel = rootView.findViewById(R.id.txtv_account_tel);
        txtv_account_id = rootView.findViewById(R.id.txtv_account_id);
        txtv_account_addr1 = rootView.findViewById(R.id.txtv_account_addr1);
        txtv_account_addr2 = rootView.findViewById(R.id.txtv_account_addr2);
        txtv_account_birth = rootView.findViewById(R.id.txtv_account_birth);

        myaccount_name = rootView.findViewById(R.id.myaccount_name);

        datePicker_account = rootView.findViewById(R.id.datePicker_account);
        btn_account_update = rootView.findViewById(R.id.btn_account_update);
        btn_account_back = rootView.findViewById(R.id.btn_account_back);

        SaveLogin saveLogin = new SaveLogin();
        SharedPreferences.Editor saveuserinfo = saveLogin.saveUserInfo(mActivity);

        if(saveuserinfo != null) {
            String user_name = saveLogin.getUserName(mActivity);
            String user_id = saveLogin.getUserId(mActivity);
            myaccount_name.setText(user_name+"님, 안녕하세요.");
            txtv_account_id.setText(user_id);
            edt_account_name.setText(loginDTO.getName());
            edt_account_email.setText(loginDTO.getEmail());
            edt_account_tel.setText(loginDTO.getTel());
            txtv_account_birth.setText(loginDTO.getBirth());
        } else {
            myaccount_name.setText(null);
        }



        edt_account_name.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) { }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }
            @Override
            public void afterTextChanged(Editable s) {
                mActivity.account_validityChk();
            }
        });

        edt_account_pw.addTextChangedListener(textWatcher);

        edt_account_pwchk.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) { }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String pwchk = edt_account_pwchk.getText().toString();
                mActivity.accountPwChk(pwchk);
            }
            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        edt_account_email.addTextChangedListener(textWatcher);
        edt_account_tel.addTextChangedListener(textWatcher);

        btn_account_update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(edt_account_pw.getText().toString().isEmpty() ||
                        edt_account_name.getText().toString().isEmpty() || edt_account_email.getText().toString().isEmpty() || edt_account_tel.getText().toString().isEmpty()) {
                    Snackbar.make(v, "필수 정보를 입력하지 않으셨습니다.", Snackbar.LENGTH_LONG).show();
                } else if(!txtv_account_pw.getText().toString().isEmpty() ||
                        !txtv_account_name.getText().toString().isEmpty() || !txtv_account_email.getText().toString().isEmpty() || !txtv_account_tel.getText().toString().isEmpty()) {
                    Snackbar.make(v, "형식에 맞게 입력하셔야 합니다.", Snackbar.LENGTH_LONG).show();
                } else if(txtv_account_pwchk.getText().toString().equals("동일한 비밀번호를 입력하세요")) {
                    Snackbar.make(v, "비밀번호가 일치하지 않습니다.", Snackbar.LENGTH_LONG).show();
                } else {
                    AlertDialog.Builder builder = new AlertDialog.Builder(mActivity);
                    builder.setMessage("수정하시겠습니까?");

                    builder.setPositiveButton("예", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            String addr = txtv_account_addr2.getText().toString() + edt_account_addr3.getText().toString();
                            MemberUserDTO dto = new MemberUserDTO(
                                    loginDTO.getId(),
                                    edt_account_pw.getText().toString(),
                                    edt_account_name.getText().toString(),
                                    edt_account_email.getText().toString(),
                                    addr,
                                    edt_account_tel.getText().toString(),
                                    txtv_account_birth.getText().toString()
                            );
                            AccountUpdate accountUpdate = new AccountUpdate(dto);
                            try {
                                accountUpdate.execute().get();
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                            Snackbar.make(v, "수정이 완료되었습니다.", Snackbar.LENGTH_LONG).show();

                        }
                    });

                    builder.setNegativeButton("아니오", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                        }
                    });

                    AlertDialog dialog = builder.create();
                    dialog.show();

                }//if
            }
        });

        //뒤로 가기
        btn_account_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(mActivity);
                builder.setMessage("취소하시겠습니까?");

                builder.setPositiveButton("예", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        mActivity.onFragmentChange(fragMainPage);
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

        return rootView;
    }



}
