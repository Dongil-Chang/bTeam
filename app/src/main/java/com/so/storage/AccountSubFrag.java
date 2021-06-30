package com.so.storage;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class AccountSubFrag extends Fragment {
    MainActivity mActivity;
    Button btn_accountsub_ok;
    FragPrntIdPwFind fragPrntIdPwFind;
    AccountFrag accountFrag;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mActivity = (MainActivity) getActivity();
        accountFrag = new AccountFrag();

        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.frag_account_sub, container, false);
        btn_accountsub_ok = rootView.findViewById(R.id.btn_accountsub_ok);

        btn_accountsub_ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mActivity.onFragmentChange(accountFrag);
            } // onClick()
        });

        return rootView;
    } // onCreateView()
} // End of class
