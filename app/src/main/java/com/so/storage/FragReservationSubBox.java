package com.so.storage;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.so.storage.DTO.Box_MainDTO;
import com.so.storage.DTO.Box_SubDTO;

import java.util.ArrayList;

public class FragReservationSubBox extends Fragment {
    ViewGroup rootView;
    MainActivity mActivity;
    ExpandableListView exp_listv;
    ExpandableListAdapterBox exp_adpt;
    int lastClickPos = 0;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView =  (ViewGroup) inflater.inflate(R.layout.frag_reservation_sub_box, container, false);

        exp_listv = rootView.findViewById(R.id.exp_listv);
        exp_listv.setGroupIndicator(null);
        ArrayList<Box_MainDTO> arr_main = new ArrayList<>();
        ArrayList<Box_SubDTO> arr_sub = new ArrayList<>();
        for (int i=0; i<5; i++){
            arr_sub.add(new Box_SubDTO(""+i ,""+i,""+i ));
        } // for
        for (int i=0; i<5; i++){
            arr_main.add(new Box_MainDTO(""+i ,""+i,""+i,arr_sub ));
        } // for

        exp_listv.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
            @Override
            public boolean onGroupClick(ExpandableListView parent, View v, int groupPosition, long id) {
                Boolean isExpand = (!exp_listv.isGroupExpanded(groupPosition));
                exp_listv.collapseGroup(lastClickPos);
                if (isExpand){
                    exp_listv.expandGroup(groupPosition);
                }
                setListViewHeightBasedOnChildren(exp_listv);
                lastClickPos = groupPosition;
                return true;
            }
        });

        /* exp_adpt = new ExpandableListAdapterBox(getApplicationContext(),exp_listv,arr_main,arr_sub);*/
        /* Fragment 내에 Context 객체가 필요한 경우, getContext() 를 호출하여 사용한다.*/
        exp_adpt = new ExpandableListAdapterBox(getContext(),exp_listv,arr_main,arr_sub);
        exp_listv.setAdapter(exp_adpt);
        exp_adpt.setOnClickListener(new OnTextClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(), "d", Toast.LENGTH_SHORT).show();
            }
        });

        return rootView;
    } // onCreateView()

    public static void setListViewHeightBasedOnChildren(ListView listView) {
        ListAdapter listAdapter = listView.getAdapter();
        if (listAdapter == null) {
// pre-condition
            return;
        }
        int totalHeight = 0;
        int desiredWidth = View.MeasureSpec.makeMeasureSpec(listView.getWidth(), View.MeasureSpec.AT_MOST);
        for (int i = 0; i < listAdapter.getCount(); i++) {
            View listItem = listAdapter.getView(i, null, listView);
//listItem.measure(0, 0);
            listItem.measure(desiredWidth, View.MeasureSpec.UNSPECIFIED);
            totalHeight += listItem.getMeasuredHeight();
        }
        ViewGroup.LayoutParams params = listView.getLayoutParams();

        params.height = totalHeight+50;
        listView.setLayoutParams(params);

        listView.requestLayout();
    }
/*
    @Override
    public void onClick(View v) {

    }*/

} // End of class