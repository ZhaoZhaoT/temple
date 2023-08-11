package com.example.temple.view;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import androidx.appcompat.widget.AppCompatEditText;

import com.example.temple.R;

public class ClearEditTextView extends AppCompatEditText implements View.OnFocusChangeListener, TextWatcher {

    //右边的删除按钮
    private Drawable mClearDrawable;

    public ClearEditTextView(Context context) {
        this(context, null);
    }

    public ClearEditTextView(Context context, AttributeSet attrs) {
        this(context, attrs, android.R.attr.editTextStyle);
    }

    public ClearEditTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        mClearDrawable = getResources().getDrawable(R.mipmap.search_close);

        //设置删除按钮的边界
        mClearDrawable.setBounds(0, 0, mClearDrawable.getIntrinsicWidth(), mClearDrawable.getIntrinsicHeight());
        //默认隐藏删除按钮
        setClearIcon(false);

        //监听EditText焦点变化，以根据text长度控制删除按钮的显示、隐藏
        setOnFocusChangeListener(this);
        //监听文本内容变化
        addTextChangedListener(this);
    }

    /**
     * 控制EditText右边制删除按钮的显示、隐藏
     */
    public void setClearIcon(boolean isShow) {
        Drawable rightDrawable = isShow ? mClearDrawable : null;
        setCompoundDrawables(getCompoundDrawables()[0], getCompoundDrawables()[1],
                rightDrawable, getCompoundDrawables()[3]);

    }

    /**
     * 有焦点，并文本长度大于0则显示删除按钮
     *
     * @param v
     * @param hasFocus
     */
    @Override
    public void onFocusChange(View v, boolean hasFocus) {
        if (hasFocus) {
            setClearIcon(getText().length() > 0);
        } else {
            setClearIcon(false);
        }
    }

    /**
     * 文本内容变化时回调
     * 当文本长度大于0时显示删除按钮， 否则隐藏
     *
     * @param text
     * @param start
     * @param lengthBefore
     * @param lengthAfter
     */
    @Override
    public void onTextChanged(CharSequence text, int start, int lengthBefore, int lengthAfter) {
        super.onTextChanged(text, start, lengthBefore, lengthAfter);
        setClearIcon(text.length() > 0);
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void afterTextChanged(Editable s) {

    }

/*    *//**
     * 通过手指的触摸位置模式删除按钮的点击事件
     *
     * @param event
     * @return
     *//*
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (getCompoundDrawables()[2] != null) {
            if (event.getAction() == MotionEvent.ACTION_UP) {
                boolean xTouchable = event.getX() > (getWidth() - getPaddingRight() - mClearDrawable.getIntrinsicWidth())
                        && (event.getX() < (getWidth() - getPaddingRight()));

                boolean yTouchable = event.getY() > (getHeight() - mClearDrawable.getIntrinsicHeight()) / 2
                        && event.getY() < (getHeight() + mClearDrawable.getIntrinsicHeight()) / 2;

                //清除文本
                if (xTouchable && yTouchable) {
                    setText("");
                }
            }
        }
        return super.onTouchEvent(event);
    }*/

    /**
     * 因为我们不能直接给EditText设置点击事件，所以我们用记住我们按下的位置来模拟点击事件
     * 当我们按下的位置 在  EditText的宽度 - 图标到控件右边的间距 - 图标的宽度  和
     * EditText的宽度 - 图标到控件右边的间距之间我们就算点击了图标，竖直方向就没有考虑
     */
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_UP) {
            if (getCompoundDrawables()[2] != null) {

                boolean touchable = event.getX() > (getWidth() - getTotalPaddingRight())
                        && (event.getX() < ((getWidth() - getPaddingRight())));

                if (touchable) {
                    this.setText("");
                }
            }
        }

        return super.onTouchEvent(event);
    }


    /**
     * EditText抖动
     *
     * @param counts
     * @return
     */
   /* public void startShake(int counts) {
        Animation translateAnimation = new TranslateAnimation(0, 10, 0, 0);
        translateAnimation.setInterpolator(new CycleInterpolator(counts));
        translateAnimation.setDuration(500);
        startAnimation(translateAnimation);
    }*/
}
