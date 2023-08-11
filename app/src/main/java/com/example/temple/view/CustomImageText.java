package com.example.temple.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.temple.R;

public class CustomImageText extends LinearLayout {

    private ImageView customPicIv;
    private TextView customTextTv;

    public CustomImageText(Context context, AttributeSet attrs) {
        super(context, attrs);
        // 加载layout
        View view = LayoutInflater.from(context).inflate(R.layout.custom_img_text,this);
        customPicIv = (ImageView) view.findViewById(R.id.custom_pic_iv);
        customTextTv = (TextView) view.findViewById(R.id.custom_text_tv);


        // 加载自定义属性配置
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.CustomPicText);
        // 为自定义属性添加特性
        if (typedArray != null) {
            // 为图片添加特性
            int picSrc = typedArray.getResourceId(R.styleable.CustomPicText_pic_src,0);
            float picWidth = typedArray.getDimension(R.styleable.CustomPicText_pic_backgroud_width, 0);
            float picHeight = typedArray.getDimension(R.styleable.CustomPicText_pic_backgroud_height, 0);
            float padding=typedArray.getDimension(R.styleable.CustomPicText_pic_padding, 0);
            customPicIv.setImageResource(picSrc);
            LayoutParams params=new LayoutParams((int)picWidth,(int)picHeight);
            params.setMargins(0,0,0,(int) padding);
            customPicIv.setLayoutParams(params);
         /*   customPicIv.setMaxWidth((int) picWidth);
            customPicIv.setMaxHeight((int) picHeight);*/
            // 为标题设置属性
            String picText = typedArray.getString(R.styleable.CustomPicText_pic_text);
            int picTextColor = typedArray.getColor(R.styleable.CustomPicText_pic_text_color,16);
            int picTextSize = typedArray.getInt(R.styleable.CustomPicText_pic_text_size,16);
            customTextTv.setText(picText);
            customTextTv.setTextColor(picTextColor);
            customTextTv.setTextSize(picTextSize);
            typedArray.recycle();
        }
    }
    public void setText(String name){
        customTextTv.setText(name);
    }

    public void setRes(int res){
        customPicIv.setImageResource(res);
    }

    public String getText(){
        return customTextTv.getText().toString();
    }
}
