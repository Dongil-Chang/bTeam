package com.so.storage;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.TextView;

import com.so.storage.DTO.Cabi_MainDTO;
import com.so.storage.DTO.Cabi_SubDTO;

import java.util.ArrayList;


public class ExpandableListAdapterCabi extends BaseExpandableListAdapter
        implements OnTextClickListener , OnGroupClickListener {

    public Context mContext;
    public ExpandableListView mListView;
    public ArrayList<Cabi_MainDTO> category_main;
    public ArrayList<Cabi_SubDTO> category_sub;
    static OnTextClickListener listener;
    static OnGroupClickListener glistener;

    public ExpandableListAdapterCabi(Context pContext, ExpandableListView pListView, ArrayList<Cabi_MainDTO> category_main
            , ArrayList<Cabi_SubDTO> category_sub) {
        this.mContext = pContext;
        this.mListView = pListView;
        this.category_main = category_main;
        this.category_sub = category_sub;
    }

    public void addItem(Cabi_MainDTO groupData, Cabi_SubDTO item) {
        if (!category_main.contains(groupData)) {
            category_main.add(groupData);
        }
        int ind = category_main.indexOf(groupData);
        ArrayList<Cabi_SubDTO> lstItems = category_main.get(ind).getItems();
        lstItems.add(item);
// category_main.get(ind).setItems(lstItems);
    }


    @Override
    public Object getChild(int groupPosition, int childPosition) {
        ArrayList<Cabi_SubDTO> item = (category_main.get(groupPosition)).getItems();
        return item.get(childPosition);
    }


    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    public void setOnClickListener(OnTextClickListener listener) {
        this.listener = listener;
    }
    public void setOnClickListener(OnGroupClickListener glistener) {
        this.glistener = glistener;
    }


    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View view,
                             ViewGroup parent) {
        Cabi_SubDTO item = (Cabi_SubDTO) getChild(groupPosition, childPosition);
// if (view == null) {
        LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(mContext.LAYOUT_INFLATER_SERVICE);
        view = inflater.inflate(R.layout.list_item, null,false);
// }

/* if (childPosition % 2 == 0){
view.setBackgroundColor(Color.parseColor("#F6F6F6"));
}else{
view.setBackgroundColor(Color.parseColor("#F9F9F9"));
}*/

        TextView txt_category_sub = view.findViewById(R.id.txt_sub);

        txt_category_sub.setText(item.getVal2());




        txt_category_sub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.onClick(view);
            }
        });
        return view;
    }


    @Override
    public int getChildrenCount(int groupPosition) {
        String aaaa = category_main.get(groupPosition).getItems().size()+"";
        return category_main.get(groupPosition).getItems().size();
    }


    @Override
    public Object getGroup(int groupPosition) {
        return category_main.get(groupPosition);
    }


    @Override
    public int getGroupCount() {
        return category_main.size();
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }


    @Override
    public View getGroupView(int groupPosition, boolean isLastChild, View view, ViewGroup parent) {
        Cabi_MainDTO model = (Cabi_MainDTO) getGroup(groupPosition);
// if (view == null) {
        LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(mContext.LAYOUT_INFLATER_SERVICE);
        view = inflater.inflate(R.layout.group_item, null);
// }


        TextView txt_category_main = view.findViewById(R.id.txt_main);
        ImageView imgv_category = view.findViewById(R.id.img_main);
        txt_category_main.setText(model.getVal1());

        Drawable img = null;


        return view;

    }


    @Override
    public boolean hasStableIds() {
        return true;
    }


    @Override
    public boolean isChildSelectable(int arg0, int arg1) {
        return true;
    }


    @Override
    public void onClick(View v) {

    }
}