package com.example.my31_recyclerview2;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
                                                                        //  interface  OnSingerItemClickListner 연결
public class SingerAdapter extends RecyclerView.Adapter<SingerAdapter.ViewHolder> implements OnSingerItemClickListner {
    // 멤버변수
    Context context;
    ArrayList<com.example.my31_recyclerview2.SingerDTO> dtos;
    OnSingerItemClickListner listner;
    // 메인 액티비티



    // 생성자 메소드
    public SingerAdapter(Context context, ArrayList<com.example.my31_recyclerview2.SingerDTO> dtos) {
        this.context = context;
        this.dtos = dtos;
        // 선언 후 초기화 된 상태
        // 값을 사용할 수 있는 상태
    }

    // 화면(xml) 연결 : ListView에서 ViewHolder를 사용한 경우와 똑같음.
    @NonNull
    @Override
    public com.example.my31_recyclerview2.SingerAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View itemview = inflater.inflate(R.layout.singerview, parent, false);

        return new ViewHolder(itemview);
    } // onCreateViewHolder()

    // 데이터 연결부(Binding)
    // ViewHolder가 세팅 되어 있는 상태에서 ViewHolder 를 인자로 받아서 사용
    // 이벤트 처리 onClick, 이곳에서 처리
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        com.example.my31_recyclerview2.SingerDTO dto = dtos.get(position);

        //holder 부분은 우리가 만들어놓은 ViewHolder에서 작업함.
        holder.setItem(dto);

        holder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, dtos.get(position).getName(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    // Item의 갯수가 어느 정도 들어가는지 확인하는 부
    @Override
    public int getItemCount() {
        return dtos.size();
    }

    public void addDto(com.example.my31_recyclerview2.SingerDTO dto) {dtos.add(dto);}

    // 추가 부분 ====

    public void setOnItemClickListner(OnSingerItemClickListner listner){
        this.listner = listner;
    }

    //오버라이드 된 이벤트 처리 부분
    @Override
    public void onItemClick(ViewHolder holder, View view, int position) {
        if (listner != null){
            listner.onItemClick(holder , view,position);
        }
    }

    public SingerDTO getItem(int position) {
        return dtos.get(position);
    }

    // 추가 부분 ==== 끝

    // ViewHolder 를 강제로 만들어야 함
    public class ViewHolder extends RecyclerView.ViewHolder {

        LinearLayout parentLay;
        TextView tvName, tvPhone;
        ImageView imageView;

        public ViewHolder(View itemView) {
            super(itemView);
            parentLay = itemView.findViewById(R.id.linear);
            tvName = itemView.findViewById(R.id.name);
            tvPhone = itemView.findViewById(R.id.phoneNum);
            imageView = itemView.findViewById(R.id.imgv1);
        } // ViewHolder()

        // 데이터 세팅 부분 onBindViewHolder에서 사용함
        // 데이터 의존성
        public void setItem(com.example.my31_recyclerview2.SingerDTO dto) {
            tvName.setText(dto.getName());
            tvPhone.setText(dto.getPhonenum());
            imageView.setImageResource(dto.getResid());
        } // setItem()

    } // ViewHolder.class
}
