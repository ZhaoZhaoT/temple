package com.example.temple.dialog;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.example.temple.R;
import com.lxj.xpopup.core.BottomPopupView;

import org.jetbrains.annotations.NotNull;

/**
 * 相机  相册 选项弹窗
 */
public class StyleSelectPopup extends BottomPopupView implements View.OnClickListener {

    private TextView tv_top;
    private TextView tv_bottom;
    private TextView tv_cancle;

    private Context context;
     private onClickDone listener;


    public StyleSelectPopup(@NonNull @NotNull Context context, StyleSelectPopup.onClickDone onClickDone) {
        super(context);
        this.context = context;
        this.listener = onClickDone;

    }

    @Override
    protected int getImplLayoutId() {
        return R.layout.dialog_style_select;
    }

    @Override
    protected void onCreate() {
        super.onCreate();
        tv_top = findViewById(R.id.tv_top);
        tv_top.setOnClickListener(this);
        tv_bottom = findViewById(R.id.tv_bottom);
        tv_bottom.setOnClickListener(this);
        tv_cancle = findViewById(R.id.tv_cancle);
        tv_cancle.setOnClickListener(this);

    }


    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.tv_top) {
            if (listener != null) {
                listener.selectData("photograph");
            }
            dialog.dismiss();
        }else if (view.getId() == R.id.tv_bottom) {
            if (listener != null) {
                listener.selectData("album");
            }
            dialog.dismiss();
        }else if (view.getId() == R.id.tv_cancle) {
            dialog.dismiss();
        }
    }

    public interface onClickDone {
        void selectData(String surname);
    }

}
