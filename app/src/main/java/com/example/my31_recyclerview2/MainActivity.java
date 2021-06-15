package com.example.my31_recyclerview2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    SingerAdapter adapter;
    ArrayList<SingerDTO> dtos;
	int a = 0;
	String aaaaa = "";
	int b = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // ListView                  / RecyclerView
        // ViewHolder 만들어서 사용(강제성 없음)   / viewHolder 강제함
        // 세로방향만 지원            /  세로, 가로, 지그재그 모두 지원
        // 아이템이 추가/제거 애니메이션 적용 불가  / 적용가능
        // Adapter 자체도 기본 어댑터만 / 사용자가 정의 사용 가능

        // ViewHolder 패턴을 구현하는 이유
        // findViewById 사용할 때마다 리소스가 소모 (사용됨)
        // ViewHolder를 이용해서 반복적인 find를 줄이기 위함
        // inflate 최소화 하고 view를 재활용 하기 위함

        dtos = new ArrayList<>();
        recyclerView = findViewById(R.id.recylerview);

        // 레이아웃 매니저르롱해 레이아웃 형태를 지정하고
        // recyclerView 에 setting 해줌
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(MainActivity.this, RecyclerView.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);

        adapter = new SingerAdapter(MainActivity.this, dtos);
        adapter.addDto(new SingerDTO("홍길동","01012345678",30,R.drawable.singer1));
        adapter.addDto(new SingerDTO("나길동","01011115555",20,R.drawable.singer2));
        adapter.addDto(new SingerDTO("심청이","01065486315",24,R.drawable.singer3));
        adapter.addDto(new SingerDTO("춘향이","01023646415",34,R.drawable.singer4));
        adapter.addDto(new SingerDTO("나애리","01032567485",50,R.drawable.singer5));

        // 추가 부분
        recyclerView.setAdapter(adapter);
        adapter.setOnItemClickListner(new OnSingerItemClickListner() {
            @Override
            public void onItemClick(SingerAdapter.ViewHolder holder, View view, int position) {
                SingerDTO dto = adapter.getItem(position);
                Toast.makeText(MainActivity.this, dto.getName(), Toast.LENGTH_SHORT).show();
            }
        });
    } // onCreate()
} // class