package com.so.storage.Manager;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.so.storage.ATask.MgMemberListSelect;
import com.so.storage.Adapter.MemberListAdapter;
import com.so.storage.DTO.MemberUserDTO;
import com.so.storage.MainActivity;
import com.so.storage.R;

import java.util.ArrayList;

public class FragMgMemberList extends Fragment {

    ViewGroup rootView;
    MainActivity mActivity;
    ListView listv_mg_member_list;
    MemberListAdapter adapter;
    ArrayList<MemberUserDTO> dtos;
    MgMemberListSelect mgMemberListSelect;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        rootView = (ViewGroup) inflater.inflate(R.layout.frag_mg_member_list, container, false);

        mActivity = (MainActivity) getActivity();

        listv_mg_member_list = rootView.findViewById(R.id.listv_mg_member_list);

        dtos = new ArrayList<>();
        adapter = new MemberListAdapter(mActivity, dtos);

        mgMemberListSelect = new MgMemberListSelect(dtos, adapter);
        mgMemberListSelect.execute();

        listv_mg_member_list.setAdapter(adapter);

        return rootView;
    } // onCreateView
} // class
