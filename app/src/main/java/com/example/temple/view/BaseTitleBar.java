package com.example.temple.view;

import android.app.Activity;
import android.content.Context;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.temple.R;


public class BaseTitleBar extends RelativeLayout implements View.OnClickListener {

    private Context mContext;
//    public  String navigationTitle = "标题栏";
    private ImageView ivBaseBack,ivRight;
    private TextView tvBaseTitle,tvBaseRight,tvRight;
    public FrameLayout layoutLeftBack;
    private RelativeLayout rlTitle;
    private EditText baseSearch;
    private View viewLine;
    public BaseTitleBar(Context context, AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
        LayoutInflater.from(context).inflate(R.layout.layout_base_titlebar, this);
        layoutLeftBack = findViewById(R.id.layout_left_back);
        tvBaseTitle=findViewById(R.id.tv_base_title);
        tvBaseRight=findViewById(R.id.tv_base_right);
        ivBaseBack=findViewById(R.id.iv_base_back);
        ivRight=findViewById(R.id.iv_right_add);
        tvRight=findViewById(R.id.tv_right_text);
        baseSearch=findViewById(R.id.base_edit_search);
        viewLine=findViewById(R.id.view_line);
        rlTitle=findViewById(R.id.rl_title);
        layoutLeftBack.setOnClickListener(this);

    }

    public void hiddenLine(){
        viewLine.setVisibility(View.GONE);
    }
    public TextView getTvBaseRight() {
        return tvBaseRight;
    }

    public TextView getTvRight() {
        return tvRight;
    }

    public FrameLayout getLayoutLeft() {
        return layoutLeftBack;
    }

    public ImageView getIvRight() { return ivRight; }

    public void setIvRight(int res) {
        ivRight.setVisibility(View.VISIBLE);
        ivRight.setImageResource(res);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.layout_left_back) {
            ((Activity) mContext).finish();
        }
    }

    /**
     * 设置标题
     * @param navigationTitle
     */
    public void setBaseTitle(String navigationTitle) {
        tvBaseTitle.setText(navigationTitle);
    }
    /*
    * 设置返回箭头白色、黑色图片
    * */
    public void setIvBackRes(int res){
        if(res==0){
            ivBaseBack.setVisibility(View.GONE);
        }else{
            ivBaseBack.setImageResource(res);
        }
    }

    public void setBaseRight(String navigationTitle) {
        if(TextUtils.isEmpty(navigationTitle)){
            tvBaseRight.setVisibility(View.GONE);
        }else{
            tvBaseRight.setText(navigationTitle);
            tvBaseRight.setVisibility(View.VISIBLE);
        }
    }

    public void setTitleSize(int titleSize){
        tvBaseTitle.setTextSize(titleSize);
    }

    public void setRightSize(int titleSize){
        tvBaseRight.setTextSize(titleSize);
    }

    public void setRightColor(int color){
        tvBaseRight.setTextColor(getResources().getColor(color));
    }

    public void setTitleColor(int color){
        tvBaseTitle.setTextColor(getResources().getColor(color));
    }

    public void setHit(String hint){
        baseSearch.setHint(hint);
        baseSearch.setVisibility(View.VISIBLE);
    }

    public void setTextWatcher(TextWatcher textWatcher){
        baseSearch.addTextChangedListener(textWatcher);
    }


    public void setRightclick(OnClickListener onClickListener){
        tvRight.setOnClickListener(onClickListener);
    }

    public void setRightTv(String str){
        tvRight.setText(str);
    }

    public void setRightVisiable(int visiable){
        tvRight.setVisibility(visiable);
    }

    public void showSearch(){
        baseSearch.setVisibility(View.VISIBLE);
    }

    public String getSearchContent(){
        return baseSearch.getText().toString();
    }

    public void setBgColor(int color){
//        setBackgroundColor(getResources().getColor(color));
        rlTitle.setBackgroundColor(getResources().getColor(color));
    }

}
