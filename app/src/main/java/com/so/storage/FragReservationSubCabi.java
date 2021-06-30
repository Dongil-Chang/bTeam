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

import com.so.storage.DTO.Cabi_MainDTO;
import com.so.storage.DTO.Cabi_SubDTO;

import java.util.ArrayList;

public class FragReservationSubCabi extends Fragment {
    ViewGroup rootView;
    ExpandableListView exp_listv;
    ExpandableListAdapterCabi exp_adpt;
    int lastClickPos = 0;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView =  (ViewGroup) inflater.inflate(R.layout.frag_reservation_sub_cabi, container, false);

        exp_listv = rootView.findViewById(R.id.exp_listv);
        exp_listv.setGroupIndicator(null);

        ArrayList<Cabi_MainDTO> cabi_main = new ArrayList<>();
        ArrayList<Cabi_SubDTO>  cabi_sub = new ArrayList<>();
        for (int i=0; i<5; i++){
            cabi_sub.add(new Cabi_SubDTO(""+i ,""+i,""+i ));
        } // for
        for (int i=0; i<5; i++){
            cabi_main.add(new Cabi_MainDTO(""+i ,""+i,""+i,cabi_sub ));
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
            } // onGroupClick()
        });

        exp_adpt = new ExpandableListAdapterCabi(getContext(),exp_listv,cabi_main,cabi_sub);
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

    /*@Override
    public void onClick(View v) {

    }*/
} // End of class
