package com.so.storage;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.so.storage.common.SaveLogin;

public class MyStorageFrag extends Fragment {
    MainActivity mActivity;
    Button justcheckBtn_1;
    EmptyStorageFrag emptyStorageFrag;
    TextView mystorage_name;
    private WebView mWebView; // 웹뷰 선언
    private WebSettings mWebSettings; // 웹뷰 세팅

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.frag_my_storage, container, false);

        mActivity = (MainActivity) getActivity();
        emptyStorageFrag = new EmptyStorageFrag();
        SaveLogin saveLogin = new SaveLogin();

        // CCTV 웹뷰 설정
        mWebView = (WebView) rootView.findViewById(R.id.cctvView);

        mWebView.setWebViewClient(new WebViewClient()); // 클릭시 새창 안뜨게
        mWebSettings = mWebView.getSettings(); //세부 세팅 등록
        mWebSettings.setJavaScriptEnabled(true); // 웹페이지 자바스크립트 허용 여부
        mWebSettings.setSupportMultipleWindows(false); // 새창 띄우기 허용 여부
        mWebSettings.setJavaScriptCanOpenWindowsAutomatically(false); // 자바스크립트 새창 띄우기(멀티뷰) 허용 여부
        mWebSettings.setLoadWithOverviewMode(true); // 메타태그 허용 여부
        mWebSettings.setUseWideViewPort(true); // 화면 사이즈 맞추기 허용 여부
        mWebSettings.setSupportZoom(false); // 화면 줌 허용 여부
        mWebSettings.setBuiltInZoomControls(false); // 화면 확대 축소 허용 여부
        mWebSettings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN); // 컨텐츠 사이즈 맞추기
        mWebSettings.setCacheMode(WebSettings.LOAD_NO_CACHE); // 브라우저 캐시 허용 여부
        mWebSettings.setDomStorageEnabled(true); // 로컬저장소 허용 여부
        mWebView.loadUrl("https://www.naver.com"); // 웹뷰에 표시할 라즈베리파이 주소, 웹뷰 시작
//        mWebView.loadUrl("221.144.89.105:8003"); // 웹뷰에 표시할 라즈베리파이 주소, 웹뷰 시작

        justcheckBtn_1 = rootView.findViewById(R.id.justcheckBtn_1);
        mystorage_name = rootView.findViewById(R.id.mystorage_name);

        SharedPreferences.Editor saveuserinfo = saveLogin.saveUserInfo(mActivity);

        //(사용자이름)님의 창고상태
        if(saveuserinfo != null) {
            mystorage_name.setText(null);
            String user_name = saveLogin.getUserName(mActivity);
            mystorage_name.setText(user_name+"님의 창고상태");
        } else {
            mystorage_name.setText(null);
        }

        justcheckBtn_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mActivity.onFragmentChange(emptyStorageFrag);
            }
        });

        return rootView;
    }
}