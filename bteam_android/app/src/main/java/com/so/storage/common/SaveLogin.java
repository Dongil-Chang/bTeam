package com.so.storage.common;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.so.storage.MainActivity;

import static com.so.storage.MainActivity.loginDTO;



public class SaveLogin {
    //자동로그인
    //로그아웃
    //마이페이지 - 내창고, 정보수정, 회원탈퇴

    MainActivity mActivity;

    private SharedPreferences getSharePreferences(Context context) {
        return PreferenceManager.getDefaultSharedPreferences(context);
    }

    public SharedPreferences.Editor saveUserInfo(Context context) {
        String login_id = "", login_pw = "", login_name = "", login_subcode = "";
        if(loginDTO != null) {
            login_id = loginDTO.getId();
            login_pw = loginDTO.getPw();
            login_name = loginDTO.getName();
            login_subcode = loginDTO.getSubcode();
        }

        SharedPreferences.Editor editor = getSharePreferences(context).edit();
        editor.putString("id", login_id);
        editor.putString("pw", login_pw);
        editor.putString("name", login_name);
        editor.putString("subcode", login_subcode);
        editor.apply();

        return editor;
    }

    public String getUserId(Context context) {
        SharedPreferences pref;
        pref = getSharePreferences(context);
        String user_id = pref.getString("id", "");
        return user_id;
    }

    public String getUserPw(Context context) {
        SharedPreferences pref;
        pref = getSharePreferences(context);
        String user_pw = pref.getString("pw", "");
        return user_pw;
    }

    public String getUserName(Context context) {
        SharedPreferences pref;
        pref = getSharePreferences(context);
        String user_name = pref.getString("name", "");
        return user_name;
    }

    public String getSubCode(Context context) {
        SharedPreferences pref;
        pref = getSharePreferences(context);
        String loginSubCode = pref.getString("subcode", "");
        return loginSubCode;
    }

    public void logout(Context context) {
        SharedPreferences.Editor editor = getSharePreferences(context).edit();
        editor.clear();
        editor.commit();
        //return "";
    }



}//class
