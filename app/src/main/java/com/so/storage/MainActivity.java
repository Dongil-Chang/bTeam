package com.so.storage;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;

import android.os.Bundle;

import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
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
    FragPrntIdPwFind fragPrntIdPwFind;
    FragPrntMyPage fragPrntMyPage;
    AccountFrag fragAccount;
    FragMainPage fragMainPage;
    FragReservationSubBox fragReservationSubBox;
    FragReservationSubCabi fragReservationSubCabi;

    // Fragment selected = null;   fragment 전환 시 사용할 변수


    // Navigation Drawer 가 열린 상태에서 뒤로가기 버튼 클릭시 앱이 종료되는 것을 방지하고
    // 드로어가 열린 상태에서 뒤로가기 버튼 클릭시 드로어가 닫히도록 처리
    @Override
    public void onBackPressed() {
        if(drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawers();
          // Toast.makeText(this, "back btn Clicked", Toast.LENGTH_SHORT).show();
        } else {
            super.onBackPressed();
        } // if ~ else
    }  // onBackPressed()

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fragLogin = new FragLogin();
        fragJoin = new FragJoin();
        fragReservation = new FragReservation();
        fragPrntIdPwFind = new FragPrntIdPwFind();
        fragPrntMyPage = new FragPrntMyPage();
        fragAccount = new AccountFrag();
        fragMainPage = new FragMainPage();
        fragReservationSubBox = new FragReservationSubBox();
        fragReservationSubCabi = new FragReservationSubCabi();

        // Main
        getSupportFragmentManager().beginTransaction().add(R.id.container, fragMainPage).addToBackStack(null).commit();
        //getSupportFragmentManager().beginTransaction().replace(R.id.container, fragMainPage).addToBackStack(null).commit();

        drawerLayout = findViewById(R.id.drawer_layout); // drawlayout

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        btn_login = findViewById(R.id.btn_login);
        btn_sign = findViewById(R.id.btn_sign);

        // 로그인 버튼 클릭시
        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              /*Intent intent = new Intent( MainActivity.this ,GuideActivity.class);
              startActivity(intent);*/
                onFragmentChange(fragLogin);
                drawerLayout.closeDrawers(); // 추가 : drawerlayout 닫기

            }
        });



        // 회원가입 버튼 클릭시
        btn_sign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onFragmentChange(fragJoin);
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
                this,drawer,toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
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

    /*// Fragment 이동 메소드
    public void onFragmentChange(String frag) {
        if(frag.equalsIgnoreCase("login")) {
            selected = fragLogin;
        } else if(frag.equalsIgnoreCase("join")){
            selected = fragJoin;
            //getSupportFragmentManager().beginTransaction().replace(R.id.container, fragJoin).addToBackStack(null).commit();
        } else if((frag.equalsIgnoreCase("reservation"))) {
            selected = fragReservation;
        } else if((frag.equalsIgnoreCase("find_id_pw"))) {
            selected = fragPrntIdPwFind;
        } else if((frag.equalsIgnoreCase("mypage"))) {
            selected = fragPrntMyPage;
        } else if((frag.equalsIgnoreCase("pwcheck"))) {
            selected = fragAccount;
        } else if ((frag.equalsIgnoreCase("mainpage"))) {
            selected = fragMainPage;
        } // if ~ else if
        getSupportFragmentManager().beginTransaction().replace(R.id.container, selected ).addToBackStack(null).commit();
        // 공통 코드 정리 및 .addToBackStack(null) 추가 기입
    } // onFragmentChange()*/

    // Fragment 이동 메소드
    public void onFragmentChange(Fragment frag) {
        if(frag.equals(fragLogin)) {
        } else if(frag.equals(fragJoin)) {
        } else if(frag.equals(fragReservation)) {
        } else if(frag.equals(fragPrntIdPwFind)) {
        } else if(frag.equals(fragPrntMyPage)) {
        } else if(frag.equals(fragAccount)) {
        } else if (frag.equals(fragMainPage)) {
        } else if (frag.equals(fragReservationSubBox)) {
        } else if (frag.equals(fragReservationSubCabi)) {
        } // if ~ else if
        getSupportFragmentManager().beginTransaction().replace(R.id.container, frag ).addToBackStack(null).commit();
        // 공통 코드 정리 및 .addToBackStack(null) 추가 기입
    } // onFragmentChange()

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.nav_main){
            Toast.makeText(this, "메인화면 눌림", Toast.LENGTH_SHORT).show();
            onFragmentChange(fragMainPage);
        } 
        else if(id == R.id.nav_reservation) {
            Toast.makeText(this, "예약하기 눌림", Toast.LENGTH_SHORT).show();
            onFragmentChange(fragReservation);
        } 
        else if(id == R.id.nav_notice){
            Toast.makeText(this, "공지사항 눌림", Toast.LENGTH_SHORT).show();
        }
        else if(id == R.id.nav_event){
            Toast.makeText(this, "이벤트 눌림", Toast.LENGTH_SHORT).show();
        }
        else if(id == R.id.nav_guide){
            Toast.makeText(this, "이용 안내 눌림", Toast.LENGTH_SHORT).show();
            /*Intent intent = new Intent(MainActivity.this , GuideActivity.class);
            startActivity(intent);*/
        }
        else if(id == R.id.nav_mypage){
            Toast.makeText(this, "마이페이지 눌림", Toast.LENGTH_SHORT).show();
            onFragmentChange(fragPrntMyPage);
        }

        DrawerLayout drawerLayout = findViewById(R.id.drawer_layout);
        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    } // onNavigationItemSelected()
} // End of class