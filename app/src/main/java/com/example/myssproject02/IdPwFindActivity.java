package com.example.myssproject02;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.google.android.material.tabs.TabLayout;

public class IdPwFindActivity extends AppCompatActivity {
    TabLayout tabs;
    IdFindFrag idfrag;
    PwFindFrag pwfrag;
    EmailFrag emailfrag;
    Fragment selected = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_id_pw_find);

        idfrag = new IdFindFrag();
        pwfrag = new PwFindFrag();
        emailfrag = new EmailFrag();

        getSupportFragmentManager().beginTransaction().replace(R.id.container, idfrag).commit();
        tabs = findViewById(R.id.tabs);
        tabs.addTab(tabs.newTab().setText("ID 찾기"));
        tabs.addTab(tabs.newTab().setText("PW 찾기"));

        tabs.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                int temp_pos = tab.getPosition();
                if(temp_pos == 0) {
                    selected = idfrag;
                } else if(temp_pos == 1) {
                    selected = pwfrag;
                } // if ~ else if
                getSupportFragmentManager().beginTransaction().replace(R.id.container, selected).commit();
            } // onTabSelected()

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    } // onCreate()
}