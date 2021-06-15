package com.example.my31_recyclerview2;

import android.view.View;

public interface OnSingerItemClickListner {

    // 어떤 아이템을 가져옴
    public void onItemClick(SingerAdapter.ViewHolder holder, View view, int position);
}
