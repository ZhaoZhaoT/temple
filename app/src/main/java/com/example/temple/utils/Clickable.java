//package com.example.temple.utils;
//
//import android.text.TextPaint;
//import android.text.style.ClickableSpan;
//import android.view.View;
//
//public class Clickable extends ClickableSpan {
//    private final View.OnClickListener mListener;
//
//    public Clickable(View.OnClickListener mListener) {
//        this.mListener = mListener;
//    }
//
//    @Override
//    public void onClick(View v) {
//        mListener.onClick(v);
//    }
//    @Override
//    public void updateDrawState(TextPaint ds) {
///*        ds.setARGB(255, 255, 255, 255);
//        ds.setColor(Color.parseColor("#CC161D"));*/
//        ds.setUnderlineText(false);    //去除超链接的下划线
//    }
//}
