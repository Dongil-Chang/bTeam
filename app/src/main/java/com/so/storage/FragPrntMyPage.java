package com.so.storage;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.material.tabs.TabLayout;

public class FragPrntMyPage extends Fragment  implements View.OnClickListener  {

    TabLayout mypageTab;
    StorageFrag storageFrag;
    ReservationFrag reservationFrag;
    AccountFrag accountFrag;
    AccountSubFrag accountSubFrag;
    MemberLeaveFrag memberLeaveFrag;
    Fragment selected = null;

    ViewGroup rootView;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = (ViewGroup) inflater.inflate(R.layout.frag_prnt_mypage, container, false);

        storageFrag = new StorageFrag();            // 내 창고
        reservationFrag = new ReservationFrag();    // 이용내역
        accountFrag = new AccountFrag();            // 비밀번호 확인(정보수정 페이지 전환 전)
        accountSubFrag = new AccountSubFrag();      // 정보수정
        memberLeaveFrag = new MemberLeaveFrag();    // 회원탈퇴

        getChildFragmentManager().beginTransaction().replace(R.id.mcontainer, storageFrag).addToBackStack(null).commit();

        mypageTab = rootView.findViewById(R.id.mypageTab);
        mypageTab.addTab(mypageTab.newTab().setText("내창고"));
        mypageTab.addTab(mypageTab.newTab().setText("이용내역"));
        mypageTab.addTab(mypageTab.newTab().setText("정보수정"));
        mypageTab.addTab(mypageTab.newTab().setText("회원탈퇴"));

        mypageTab.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                int temp_pos = tab.getPosition();
                if(temp_pos == 0) {
                    selected = storageFrag;
                } else if(temp_pos == 1) {
                    selected = reservationFrag;
                } else if(temp_pos == 2) {
                    selected = accountSubFrag;
                } else if(temp_pos == 3) {
                    selected = memberLeaveFrag;
                }
                getChildFragmentManager().beginTransaction().replace(R.id.mcontainer, selected).commit();
            } // onTabSelected()

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
            }
        });
        return rootView;
    } // onCreateView()

    @Override
    public void onClick(View v) {

    }
} // End of class
