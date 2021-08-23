package com.so.storage.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.so.storage.DTO.CabiDTO;
import com.so.storage.FragReservationCheck;
import com.so.storage.MainActivity;
import com.so.storage.OnSingerItemClickListnerCabi;
import com.so.storage.R;

import java.util.ArrayList;

public class CabiAdapter extends RecyclerView.Adapter<CabiAdapter.ViewHolder> implements OnSingerItemClickListnerCabi{
    private static final String TAG = "boxadapter:";
    Context context;
    ArrayList<CabiDTO> items = new ArrayList<CabiDTO>();
    MainActivity mActivity;
    FragReservationCheck fragReservationCheck;
    OnSingerItemClickListnerCabi listener;


    @NonNull
    @Override
    public CabiAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        View itemview = inflater.inflate(R.layout.item_cardview_cabi , viewGroup , false);
        fragReservationCheck = new FragReservationCheck();

        return new CabiAdapter.ViewHolder(itemview,this);
    }

    @Override public void onBindViewHolder(@NonNull CabiAdapter.ViewHolder viewHolder, int position) {
        CabiDTO item = items.get(position);
        viewHolder.setItem(item);
    }

    @Override public int getItemCount() {
        return items.size();
    }

    public void setOnItemClicklistener(OnSingerItemClickListnerCabi listener){
        this.listener = listener;
    }

    @Override
    public void onItemClick(CabiAdapter.ViewHolder holder, View view, int position) {
        if(listener != null){
            listener.onItemClick(holder,view,position);
        }
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        TextView cabi_name;
        TextView cabi_info;
        ImageView reser_imgv_cabi_change;

        public ViewHolder(View itemView, final OnSingerItemClickListnerCabi listener){
            super(itemView);
            cabi_name = itemView.findViewById(R.id.cabi_name);
            cabi_info = itemView.findViewById(R.id.cabi_info);
            reser_imgv_cabi_change = itemView.findViewById(R.id.reser_imgv_cabi_change);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override public void onClick(View v) {
                    int position = getAdapterPosition();
                    if(listener != null){
                        listener.onItemClick(CabiAdapter.ViewHolder.this, v, position);
                    }
                }
            });
        }

        public void setItem(CabiDTO item){
            cabi_name.setText(item.getCabi_name());
            cabi_info.setText(item.getCabi_info());
            reser_imgv_cabi_change.setImageResource(item.getColor_img());
        }
    }

    public void addItem(CabiDTO item){
        items.add(item);
    }

    public void setItems(ArrayList<CabiDTO> items){
        this.items = items;
    }

    public CabiDTO getItem(int position){
        return items.get(position);
    }

    public void setItem(int position, CabiDTO item){
        items.set(position,item);
    }

}
