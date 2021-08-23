package com.so.storage;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.so.storage.Adapter.CabiAdapter;
import com.so.storage.DTO.CabiDTO;

import java.util.ArrayList;

import static com.so.storage.MainActivity.reservationDTO;


public class FragReservationCabi extends Fragment {
    RecyclerView recyclerView;
    CabiAdapter adapter;
    ArrayList<CabiDTO> dtos;
    private static final String TAG = "subBox:";


    private Context context;
    MainActivity mActivity;
    FragReservationCheck fragReservationCheck;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.frag_reservation_cabi, container, false);
        context = container.getContext();
        fragReservationCheck = new FragReservationCheck();
        mActivity = (MainActivity) getActivity();
        CabiDTO cabiDTO = new CabiDTO();
        dtos = new ArrayList<>();


        recyclerView = view.findViewById(R.id.reser_cabi_rv);
        LinearLayoutManager layoutManager1 = new LinearLayoutManager(recyclerView.getContext());
        recyclerView.setLayoutManager(layoutManager1);

        adapter = new CabiAdapter();
        adapter.addItem(new CabiDTO( "C1", "C1", R.drawable.plusimg));
        adapter.addItem(new CabiDTO("C2", "C2", R.drawable.plusimg));
        adapter.addItem(new CabiDTO("C3", "C3", R.drawable.plusimg));
        adapter.addItem(new CabiDTO("C4", "C4", R.drawable.plusimg));
        adapter.addItem(new CabiDTO("C5", "C5", R.drawable.plusimg));
        adapter.addItem(new CabiDTO("C6", "C6", R.drawable.plusimg));
        adapter.addItem(new CabiDTO("C7", "C7", R.drawable.plusimg));
        adapter.addItem(new CabiDTO("C8", "C8", R.drawable.plusimg));
        adapter.addItem(new CabiDTO("C9", "C9", R.drawable.plusimg));
        adapter.addItem(new CabiDTO( "C10", "C10", R.drawable.plusimg));

        recyclerView.setAdapter(adapter);
        adapter.setOnItemClicklistener(new OnSingerItemClickListnerCabi() {

            @Override
            public void onItemClick(CabiAdapter.ViewHolder holder, View view, int position) {
                CabiDTO item = adapter.getItem(position);
                if (item.getCabi_name() == "C1"){
                    reservationDTO.setProduct_type(item.getCabi_name());
                    //Toast.makeText(context,"아이템 선택 " + item.getCabi_name(), Toast.LENGTH_LONG).show();
                } else if (item.getCabi_name() == "C2"){
                    reservationDTO.setProduct_type(item.getCabi_name());
                } else if (item.getCabi_name() == "C3"){
                    reservationDTO.setProduct_type(item.getCabi_name());
                } else if (item.getCabi_name() == "C4"){
                    reservationDTO.setProduct_type(item.getCabi_name());
                } else if (item.getCabi_name() == "C5"){
                    reservationDTO.setProduct_type(item.getCabi_name());
                } else if (item.getCabi_name() == "C6"){
                    reservationDTO.setProduct_type(item.getCabi_name());
                } else if (item.getCabi_name() == "C7"){
                    reservationDTO.setProduct_type(item.getCabi_name());
                } else if (item.getCabi_name() == "C8"){
                    reservationDTO.setProduct_type(item.getCabi_name());
                } else if (item.getCabi_name() == "C9"){
                    reservationDTO.setProduct_type(item.getCabi_name());
                } else if (item.getCabi_name() == "C10"){
                    reservationDTO.setProduct_type(item.getCabi_name());
                }
                mActivity.onFragmentChange(fragReservationCheck);
            }
        });

        view.findViewById(R.id.btn_reser_cabi_back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mActivity.onBackPressed();
            }
        });

        return view;
    }
} // End of class