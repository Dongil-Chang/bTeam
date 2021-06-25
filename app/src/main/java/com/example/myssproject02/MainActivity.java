package com.example.myssproject02;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager2.widget.ViewPager2;

import android.content.Intent;
import android.os.Bundle;

import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;


public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    DrawerLayout drawerLayout;
    Button btn_login , btn_sign;
    Toolbar toolbar;
    FragLogin fragLogin;
    FragJoin fragJoin;
    FragReservation fragReservation;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);








        fragLogin = new FragLogin();
        fragJoin = new FragJoin();
        fragReservation = new FragReservation();
        drawerLayout = findViewById(R.id.drawer_layout); // drawlayout
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        btn_login = findViewById(R.id.btn_login);
        btn_sign = findViewById(R.id.btn_sign);

        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              /*Intent intent = new Intent( MainActivity.this ,GuideActivity.class);
              startActivity(intent);*/
                onFragmentChange("login");
                drawerLayout.closeDrawers(); // 추가 : drawerlayout 닫기
            }
        });

        btn_sign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onFragmentChange("join");
                drawerLayout.closeDrawers(); // 추가 : drawerlayout 닫기
            }
        });




        //툴바의 타이틀

        setSupportActionBar(toolbar);  //툴바를 액션바로 지정
        getSupportActionBar().setDisplayShowCustomEnabled(true);    //툴바를 보여줌
        getSupportActionBar().setDisplayShowTitleEnabled(false);  //툴바의 기본제목을 없애줌

        //네비게이션 드로어 생성해서 리스너 붙이기 : res-string으로
        //정의해놓은 값을 사용할 예정

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this,drawer,toolbar,
                R.string.navigation_drawer_open,
                R.string.navigation_drawer_close

        );
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        int userLevel  = 1;
        String loginID = "admin";
        View headerView = navigationView.getHeaderView(0);

        if (userLevel == 0){
            navigationView.getMenu().findItem(R.id.nav_guide).setVisible(false);

        }else if(userLevel == 1){
            navigationView.getMenu().findItem(R.id.nav_guide).setVisible(true);
        }



    }







    // Fragment 이동 메소드
    public void onFragmentChange(String frag) {
        if(frag.equalsIgnoreCase("login")) {
            getSupportFragmentManager().beginTransaction().replace(R.id.container, fragLogin ).commit();
        } else if(frag.equalsIgnoreCase("join")){
            getSupportFragmentManager().beginTransaction().replace(R.id.container, fragJoin).commit();
        } else if((frag.equalsIgnoreCase("reservation"))) {
            getSupportFragmentManager().beginTransaction().replace(R.id.container, fragReservation).commit();
        } /*else if(flag == 3) {
            getSupportFragmentManager().beginTransaction().replace(R.id.container, mainFragment).commit();*/
         // if ~ else
    } // onFragmentChange()

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.nav_main){
            Toast.makeText(this, "메인화면 눌림", Toast.LENGTH_SHORT).show();

        } 
        else if(id == R.id.nav_reservation) {
            Toast.makeText(this, "예약하기 눌림", Toast.LENGTH_SHORT).show();
            onFragmentChange("reservation");
        } 
        else if(id == R.id.nav_notice){
            Toast.makeText(this, "공지사항 눌림", Toast.LENGTH_SHORT).show();
        }
        else if(id == R.id.nav_event){
            Toast.makeText(this, "이벤트 눌림", Toast.LENGTH_SHORT).show();
        }
        else if(id == R.id.nav_guide){
            Toast.makeText(this, "이용 안내 눌림", Toast.LENGTH_SHORT).show();

            Intent intent = new Intent(MainActivity.this , GuideActivity.class);
            startActivity(intent);
        }
        else if(id == R.id.nav_mypage){
            Toast.makeText(this, "마이페이지 눌림", Toast.LENGTH_SHORT).show();
        }




        DrawerLayout drawerLayout = findViewById(R.id.drawer_layout);
        drawerLayout.closeDrawer(GravityCompat.START);
        return true;


    }
}
