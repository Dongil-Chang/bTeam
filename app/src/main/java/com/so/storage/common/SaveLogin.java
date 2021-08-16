package com.so.storage.common;



import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import static com.so.storage.MainActivity.loginDTO;



public class SaveLogin {
    //자동로그인
    //로그아웃
    //마이페이지 - 내창고, 정보수정, 회원탈퇴

    SharedPreferences pref;

    private SharedPreferences getSharePreferences(Context context) {
        return PreferenceManager.getDefaultSharedPreferences(context);
    }

    //로그인 유저 정보 저장 - id, pw, name, subcode
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
    }//saveUserInfo()

    //자동로그인 on 상태 저장
    public boolean saveAutoLogin(Context context, boolean saveautologin) {
        SharedPreferences.Editor editor = getSharePreferences(context).edit();
        if(saveautologin) {
            editor.putBoolean("AUTO_LOGIN", saveautologin);
            editor.apply();
        }

        return saveautologin;
    }

    //자동로그인 on 상태값 가져오기
    public boolean getAutoLogin(Context context) {
        pref = getSharePreferences(context);
        boolean autologin = pref.getBoolean("AUTO_LOGIN", false);
        return autologin;
    }

    //로그인 유저 id 정보 가져오기
    public String getUserId(Context context) {
        pref = getSharePreferences(context);
        String user_id = pref.getString("id", "");

        return user_id;
    }

    //로그인 유저 pw 정보 가져오기
    public String getUserPw(Context context) {
        pref = getSharePreferences(context);
        String user_pw = pref.getString("pw", "");

        return user_pw;

    }

    //로그인 유저 name 정보 가져오기
    public String getUserName(Context context) {
        pref = getSharePreferences(context);
        String user_name = pref.getString("name", "");

        return user_name;
    }

    //로그인 유저 subcode 정보 가져오기
    public String getSubCode(Context context) {
        pref = getSharePreferences(context);
        String loginSubCode = pref.getString("subcode", "");

        return loginSubCode;
    }

    //로그아웃
    public SharedPreferences.Editor logout(Context context) {
        SharedPreferences.Editor editor = getSharePreferences(context).edit();
        editor.clear();
        editor.commit();
        return editor;
    }

}//class