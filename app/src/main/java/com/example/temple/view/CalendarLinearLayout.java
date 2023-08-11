package com.example.temple.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;

import com.haibin.calendarview.CalendarLayout;

/**
 * 如果嵌套各种View出现事件冲突，可以实现这个方法即可
 */
public class CalendarLinearLayout extends LinearLayout implements CalendarLayout.CalendarScrollView {


    public CalendarLinearLayout(Context context) {
        super(context);
    }

    public CalendarLinearLayout(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean dispatchKeyEvent(KeyEvent event) {
        return super.dispatchKeyEvent(event);
    }


    /**
     * 如果你想让下拉无效，return false
     *
     * @return isScrollToTop
     */
    @Override
    public boolean isScrollToTop() {
        boolean isScrollToTop = true;

        return isScrollToTop;
    }


}
