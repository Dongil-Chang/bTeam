package com.so.storage.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.so.storage.DTO.BoxDTO;
import com.so.storage.FragReservationCheck;
import com.so.storage.MainActivity;
import com.so.storage.OnSingerItemClickListnerBox;
import com.so.storage.R;

import java.util.ArrayList;

public class BoxAdapter
        extends RecyclerView.Adapter<BoxAdapter.ViewHolder>
        implements OnSingerItemClickListnerBox {

    private static final String TAG = "boxadapter:";
    Context context;
    ArrayList<BoxDTO> items = new ArrayList<BoxDTO>();
    MainActivity mActivity;
    FragReservationCheck fragReservationCheck;
    OnSingerItemClickListnerBox listener;


    //메인액티비티에서 접근할수있게 만들어줌.

/*    //선언만 한상태 . null
    public BoxAdapter(Context context, ArrayList<BoxDTO> items, MainActivity mActivity){
        this.context = context;
        this.items = items;
        this.mActivity = mActivity;
        //선언 후 초기화 된 상태.
        //값을 사용할수있는상태
    }*/

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        View itemview = inflater.inflate(R.layout.item_cardview_box , viewGroup , false);
        fragReservationCheck = new FragReservationCheck();

        return new ViewHolder(itemview,this);
    }

    @Override public void onBindViewHolder(@NonNull ViewHolder viewHolder, int position) {
        BoxDTO item = items.get(position);
        viewHolder.setItem(item);
    }

    @Override public int getItemCount() {
        return items.size();
    }

    public void setOnItemClicklistener(OnSingerItemClickListnerBox listener){
        this.listener = listener;
    }

    @Override
    public void onItemClick(ViewHolder holder, View view, int position) {
        if(listener != null){
            listener.onItemClick(holder,view,position);
        }
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        TextView box_name;
        TextView box_info;
        ImageView reser_imgv_box_change;

        public ViewHolder(View itemView, final OnSingerItemClickListnerBox listener){
            super(itemView);
            box_name = itemView.findViewById(R.id.box_name);
            box_info = itemView.findViewById(R.id.box_info);
            reser_imgv_box_change = itemView.findViewById(R.id.reser_imgv_box_change);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override public void onClick(View v) {
                    int position = getAdapterPosition();
                    if(listener != null){
                        listener.onItemClick(ViewHolder.this, v, position);
                    }
                }
            });
        }

        public void setItem(BoxDTO item){
            box_name.setText(item.getBox_name());
            box_info.setText(item.getBox_info());
            reser_imgv_box_change.setImageResource(item.getColor_img());
        }
    }

    public void addItem(BoxDTO item){
        items.add(item);
    }

    public void setItems(ArrayList<BoxDTO> items){
        this.items = items;
    }

    public BoxDTO getItem(int position){
        return items.get(position);
    }

    public void setItem(int position, BoxDTO item){
        items.set(position,item);
    }

}
