package com.example.myssproject02;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.Navigation;

import android.content.Intent;
import android.os.Bundle;

import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.internal.NavigationMenu;
import com.google.android.material.internal.NavigationMenuItemView;
import com.google.android.material.internal.NavigationMenuView;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.snackbar.Snackbar;

import java.time.Instant;


public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    Button btn_login , btn_sign;
    Toolbar toolbar;
    Frag_Login fragLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        btn_login = findViewById(R.id.btn_login);
        btn_sign = findViewById(R.id.btn_sign);

        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              Intent intent = new Intent( MainActivity.this ,GuideActivity.class);
              startActivity(intent);
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

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.nav_main){
            Toast.makeText(this, "메인화면 눌림", Toast.LENGTH_SHORT).show();

        } 
        else if(id == R.id.nav_order){
            Toast.makeText(this, "주문하기 눌림", Toast.LENGTH_SHORT).show();
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
