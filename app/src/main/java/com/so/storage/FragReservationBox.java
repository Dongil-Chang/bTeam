package com.so.storage;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.so.storage.Adapter.BoxAdapter;
import com.so.storage.DTO.BoxDTO;

import java.util.ArrayList;

import static com.so.storage.MainActivity.reservationDTO;


public class FragReservationBox extends Fragment {
    RecyclerView recyclerView;
    BoxAdapter adapter;
    ArrayList<BoxDTO> dtos;
    private static final String TAG = "subBox:";


    private Context context;
    MainActivity mActivity;
    FragReservationCheck fragReservationCheck;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.frag_reservation_box, container, false);
        context = container.getContext();
        fragReservationCheck = new FragReservationCheck();
        mActivity = (MainActivity) getActivity();
        BoxDTO boxDTO = new BoxDTO();
        dtos = new ArrayList<>();


        recyclerView = view.findViewById(R.id.reser_box_rv);
        LinearLayoutManager layoutManager1 = new LinearLayoutManager(recyclerView.getContext());
        recyclerView.setLayoutManager(layoutManager1);

        adapter = new BoxAdapter();
        adapter.addItem(new BoxDTO( "B1", "B1", R.drawable.plusimg));
        adapter.addItem(new BoxDTO( "B2", "B2", R.drawable.plusimg));
        adapter.addItem(new BoxDTO( "B3", "B3", R.drawable.plusimg));
        adapter.addItem(new BoxDTO( "B4", "B4", R.drawable.plusimg));
        adapter.addItem(new BoxDTO( "B5", "B5", R.drawable.plusimg));
        adapter.addItem(new BoxDTO( "B6", "B6", R.drawable.plusimg));
        adapter.addItem(new BoxDTO( "B7", "B7", R.drawable.plusimg));
        adapter.addItem(new BoxDTO( "B8", "B8", R.drawable.plusimg));
        adapter.addItem(new BoxDTO( "B9", "B9", R.drawable.plusimg));
        adapter.addItem(new BoxDTO("B10", "B10", R.drawable.plusimg));
        recyclerView.setAdapter(adapter);
        adapter.setOnItemClicklistener(new OnSingerItemClickListnerBox() {

            @Override
            public void onItemClick(BoxAdapter.ViewHolder holder, View view, int position) {
                BoxDTO item = adapter.getItem(position);
                if (item.getBox_name() == "B1"){
                    reservationDTO.setProduct_type(item.getBox_name());
                    //Toast.makeText(context,"아이템 선택 " + item.getBox_name(), Toast.LENGTH_LONG).show();
                } else if (item.getBox_name() == "B2"){
                    reservationDTO.setProduct_type(item.getBox_name());
                } else if (item.getBox_name() == "B3"){
                    reservationDTO.setProduct_type(item.getBox_name());
                } else if (item.getBox_name() == "B4"){
                    reservationDTO.setProduct_type(item.getBox_name());
                } else if (item.getBox_name() == "B5"){
                    reservationDTO.setProduct_type(item.getBox_name());
                } else if (item.getBox_name() == "B6"){
                    reservationDTO.setProduct_type(item.getBox_name());
                } else if (item.getBox_name() == "B7"){
                    reservationDTO.setProduct_type(item.getBox_name());
                } else if (item.getBox_name() == "B8"){
                    reservationDTO.setProduct_type(item.getBox_name());
                } else if (item.getBox_name() == "B9"){
                    reservationDTO.setProduct_type(item.getBox_name());
                } else if (item.getBox_name() == "B10"){
                    reservationDTO.setProduct_type(item.getBox_name());
                }
                mActivity.onFragmentChange(fragReservationCheck);
            }
        });

        view.findViewById(R.id.btn_reser_box_back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mActivity.onBackPressed();
            }
        });

        return view;
    }

} // End of class