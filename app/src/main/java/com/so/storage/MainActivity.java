package com.so.storage;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;

import android.content.Context;
import android.graphics.Rect;
import android.os.Bundle;

import android.util.Patterns;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;
import com.so.storage.Manager.FragMgMemberList;

import java.util.regex.Pattern;


public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    DrawerLayout drawerLayout;
    Button btn_login;
    Toolbar toolbar;
    FragLogin fragLogin;
    FragJoin fragJoin;
    FragReservation fragReservation;
    FragPrntIdPwFind fragPrntIdPwFind;
    FragPrntMyPage fragPrntMyPage;
    FragPrntInfoUse fragPrntInfoUse;
    MyAccountFrag fragAccount;
    FragMainPage fragMainPage;
    FragReservationSubBox fragReservationSubBox;
    FragReservationSubCabi fragReservationSubCabi;
    FragMgMemberList fragMgMemberList;
    FragNotice fragNotice;
    private View header;

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
        fragPrntInfoUse = new FragPrntInfoUse();
        fragAccount = new MyAccountFrag();
        fragMainPage = new FragMainPage();
        fragReservationSubBox = new FragReservationSubBox();
        fragReservationSubCabi = new FragReservationSubCabi();
        fragMgMemberList = new FragMgMemberList();
        fragNotice = new FragNotice();

        // Main
        getSupportFragmentManager().beginTransaction().add(R.id.container, fragMainPage).addToBackStack(null).commit();
        //getSupportFragmentManager().beginTransaction().replace(R.id.container, fragMainPage).addToBackStack(null).commit();

        drawerLayout = findViewById(R.id.drawer_layout); // drawlayout

        // 다른 위치 레이아웃 생성
        //header = getLayoutInflater().inflate(R.layout.nav_header_main, null, false);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        // btn_login = findViewById(R.id.btn_login);
        // btn_sign = findViewById(R.id.btn_sign);



       /* // 회원가입 버튼 클릭시
        btn_sign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onFragmentChange(fragJoin);
                drawerLayout.closeDrawers(); // 추가 : drawerlayout 닫기
            }
        });*/

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

        header = navigationView.getHeaderView(0);
        btn_login = (Button) header.findViewById(R.id.btn_login);

        // 로그인 버튼 클릭시
        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              /*Intent intent = new Intent( MainActivity.this ,GuideActivity.class);
              startActivity(intent);*/
                onFragmentChange(fragLogin);
                drawerLayout.closeDrawers(); // 추가 : drawerlayout 닫기

            } // (btn_login) onClick
        });


        int userLevel  = 1;
        String loginID = "admin";
        View headerView = navigationView.getHeaderView(0);

        if (userLevel == 0){
            navigationView.getMenu().findItem(R.id.myinfo).setVisible(false);
            navigationView.getMenu().findItem(R.id.managerinfo).setVisible(false);
        }else if(userLevel == 1){
            navigationView.getMenu().findItem(R.id.myinfo).setVisible(true);
            navigationView.getMenu().findItem(R.id.managerinfo).setVisible(false);
        }else if(userLevel == 2){
            navigationView.getMenu().findItem(R.id.managerinfo).setVisible(true);
            navigationView.getMenu().findItem(R.id.myinfo).setVisible(false);
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
        } else if (frag.equals(fragPrntInfoUse)) {
        } else if (frag.equals(fragMgMemberList)) {
        } else if (frag.equals(fragNotice)) {
        }
        // if ~ else if
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
            onFragmentChange(fragNotice);
        }
        else if(id == R.id.nav_event){
            Toast.makeText(this, "이벤트 눌림", Toast.LENGTH_SHORT).show();
            onFragmentChange(fragMgMemberList);
        }
        else if(id == R.id.nav_guide){
            Toast.makeText(this, "이용 안내 눌림", Toast.LENGTH_SHORT).show();
            /*Intent intent = new Intent(MainActivity.this , GuideActivity.class);
            startActivity(intent);*/
            onFragmentChange(fragPrntInfoUse);

        }
        else if(id == R.id.nav_mypage){
            Toast.makeText(this, "마이페이지 눌림", Toast.LENGTH_SHORT).show();
            onFragmentChange(fragPrntMyPage);
        }

        DrawerLayout drawerLayout = findViewById(R.id.drawer_layout);
        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    } // onNavigationItemSelected()

    public void validityChk() {
        EditText edt_join_id, edt_join_pw, edt_join_pwchk, edt_join_name, edt_join_email, edt_join_tel;
        TextView txtv_join_id, txtv_join_pw, txtv_join_pwchk, txtv_join_name, txtv_join_email, txtv_join_tel;

        txtv_join_id = findViewById(R.id.txtv_join_id);
        txtv_join_pw = findViewById(R.id.txtv_join_pw);
        txtv_join_pwchk = findViewById(R.id.txtv_join_pwchk);
        txtv_join_name = findViewById(R.id.txtv_join_name);
        txtv_join_email = findViewById(R.id.txtv_join_email);
        txtv_join_tel = findViewById(R.id.txtv_join_tel);

        edt_join_id = findViewById(R.id.edt_join_id);
        edt_join_pw = findViewById(R.id.edt_join_pw);
        edt_join_pwchk = findViewById(R.id.edt_join_pwchk);
        edt_join_name = findViewById(R.id.edt_join_name);
        edt_join_email = findViewById(R.id.edt_join_email);
        edt_join_tel = findViewById(R.id.edt_join_tel);


        //------------------포커스 관련 처리해주면 가능할 것 같음------------------
        if (Pattern.matches("^[a-z0-9]\\w{5,12}$", edt_join_id.getText().toString()) || edt_join_id.length() == 0) {
            txtv_join_id.setText("");
            edt_join_id.setBackgroundResource(R.drawable.gray_edittext);
        } else if(!Pattern.matches("^[a-z0-9]\\w{5,12}$", edt_join_id.getText().toString())) {
            txtv_join_id.setVisibility(View.VISIBLE);
            txtv_join_id.setText("형식이 올바르지 않습니다.");
            edt_join_id.setBackgroundResource(R.drawable.red_edittext);
        } // 아이디

        if (Pattern.matches("(?=.*[a-zA-ZS])(?=.*?[\\?\\!\\@\\*]).{8,20}", edt_join_pw.getText().toString()) || edt_join_pw.length() == 0) {
            txtv_join_pw.setText("");
            edt_join_pw.setBackgroundResource(R.drawable.gray_edittext);
        } else if(!Pattern.matches("(?=.*[a-zA-ZS])(?=.*?[\\?\\!\\@\\*]).{8,20}", edt_join_pw.getText().toString())) {
            txtv_join_pw.setVisibility(View.VISIBLE);
            txtv_join_pw.setText("형식이 올바르지 않습니다.");
            edt_join_pw.setBackgroundResource(R.drawable.red_edittext);
        } // 비밀번호

        if (Pattern.matches("^[가-힣]{2,5}$", edt_join_name.getText().toString()) || edt_join_name.length() == 0) {
            txtv_join_name.setText("");
            edt_join_name.setBackgroundResource(R.drawable.gray_edittext);
        } else if(!Pattern.matches("^[가-힣]{2,5}$", edt_join_name.getText().toString())) {
            txtv_join_name.setVisibility(View.VISIBLE);
            txtv_join_name.setText("형식이 올바르지 않습니다.");
            edt_join_name.setBackgroundResource(R.drawable.red_edittext);
        } // 이름

        if (Patterns.EMAIL_ADDRESS.matcher(edt_join_email.getText().toString()).matches() || edt_join_email.length() == 0) {
            txtv_join_email.setText("");
            edt_join_email.setBackgroundResource(R.drawable.gray_edittext);
        } else if(!Patterns.EMAIL_ADDRESS.matcher(edt_join_email.getText().toString()).matches()) {
            txtv_join_email.setVisibility(View.VISIBLE);
            txtv_join_email.setText("형식이 올바르지 않습니다.");
            edt_join_email.setBackgroundResource(R.drawable.red_edittext);
        } // 이메일

        if (Pattern.matches("^01(?:0|1|[6-9])\\d{3,4}\\d{4}$", edt_join_tel.getText().toString()) || edt_join_tel.length() == 0) {
            txtv_join_tel.setText("");
            edt_join_tel.setBackgroundResource(R.drawable.gray_edittext);
        } else if(!Pattern.matches("^01(?:0|1|[6-9])\\d{3,4}\\d{4}$", edt_join_tel.getText().toString())) {
            txtv_join_tel.setVisibility(View.VISIBLE);
            txtv_join_tel.setText("형식이 올바르지 않습니다.");
            edt_join_tel.setBackgroundResource(R.drawable.red_edittext);
        } // 전화번호

        /*if(edt_join_id.hasFocus()) {
            //if(!Pattern.matches("^[a-z]\\w{4,11}$", edt_join_id.getText().toString())) {
            if(!Pattern.matches("^[a-z0-9]\\w{5,12}$", edt_join_id.getText().toString())) {
                txtv_join_id.setVisibility(View.VISIBLE);
                edt_join_id.setBackgroundResource(R.drawable.red_edittext);
                txtv_join_id.setText("형식이 올바르지 않습니다.");
            } else {
                txtv_join_id.setText("");
                edt_join_id.setBackgroundResource(R.drawable.gray_edittext);
                //txtv_join_id.setText(null);
                //edt_join_id.clearFocus();
            }//아이디
        } else if(edt_join_pw.hasFocus()) {
            if (!Pattern.matches("(?=.*[a-zA-ZS])(?=.*?[\\?\\!\\@\\*]).{8,20}", edt_join_pw.getText().toString())) {
                txtv_join_pw.setVisibility(View.VISIBLE);
                txtv_join_pw.setText("형식이 올바르지 않습니다.");
            } else {
                txtv_join_pw.setText(null);
                edt_join_pw.clearFocus();
            }//비밀번호
        } else if(edt_join_name.hasFocus()) {
            if (!Pattern.matches("^[가-힣]{2,5}$", edt_join_name.getText().toString())) {
                txtv_join_name.setVisibility(View.VISIBLE);
                txtv_join_name.setText("형식이 올바르지 않습니다.");
            } else {
                txtv_join_name.setText(null);
                edt_join_name.clearFocus();
            }//이름
        } else if(edt_join_email.hasFocus()) {
            if (!Patterns.EMAIL_ADDRESS.matcher(edt_join_email.getText().toString()).matches()) {
                txtv_join_email.setVisibility(View.VISIBLE);
                txtv_join_email.setText("형식이 올바르지 않습니다.");
            } else {
                txtv_join_email.setText(null);
                edt_join_email.clearFocus();
            }//이메일
        } else if(edt_join_tel.hasFocus()) {
            // if (!Pattern.matches("^(010|011|016|017|018|019)\\d{3,4}\\d{4}$", edt_join_tel.getText().toString())) {
            if (!Pattern.matches("^01(?:0|1|[6-9])\\d{3,4}\\d{4}$", edt_join_tel.getText().toString())) {
                txtv_join_tel.setVisibility(View.VISIBLE);
                txtv_join_tel.setText("형식이 올바르지 않습니다.");
            } else {
                txtv_join_tel.setText(null);
                edt_join_tel.clearFocus();
            }//연락처
        } // if ~ else if ~ else*/
    } // validityChk()


    // Fragment 내 EditText의 외부 클릭 즉, 포커스가 바뀌었을 때 키보드를 숨기기 위한 메소드
    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        if (ev.getAction() == MotionEvent.ACTION_DOWN) {
            View v = getCurrentFocus();
            if (v instanceof EditText) {
                Rect outRect = new Rect();
                v.getGlobalVisibleRect(outRect);
                if (!outRect.contains((int)ev.getRawX(), (int)ev.getRawY())) {
                    v.clearFocus();
                    InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
                } // if
            } // if
        } // if

        return super.dispatchTouchEvent(ev);
    } // dispatchTouchEvent()

    public void pwChk(String pwchk) {
        EditText edt_join_pw;
        TextView txtv_join_pwchk;
        txtv_join_pwchk = findViewById(R.id.txtv_join_pwchk);
        edt_join_pw = findViewById(R.id.edt_join_pw);

        String pw = edt_join_pw.getText().toString();
        if(!pwchk.equals(pw)) {
            txtv_join_pwchk.setVisibility(View.VISIBLE);
            txtv_join_pwchk.setText("동일한 비밀번호를 입력하세요");
        } else {
            txtv_join_pwchk.setVisibility(View.VISIBLE);
            txtv_join_pwchk.setText("비밀번호가 확인되었습니다.");
        } // if ~ else
    } // pkChk()
} // End of class