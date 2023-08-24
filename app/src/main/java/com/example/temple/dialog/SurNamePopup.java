package com.example.temple.dialog;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.example.temple.R;
import com.lxj.xpopup.core.CenterPopupView;

import org.jetbrains.annotations.NotNull;

/**
 * 姓氏弹窗
 */
public class SurNamePopup extends CenterPopupView implements View.OnClickListener {

    ImageView iv_close;
    TextView tv_title;
    TextView tv_content;


    private String title, content;

    public SurNamePopup(@NonNull @NotNull Context context, String title, String content) {
        super(context);
        this.title = title;
        this.content = content;
    }

    @Override
    protected int getImplLayoutId() {
        return R.layout.dialog_surname;
    }

    @Override
    protected void onCreate() {
        super.onCreate();
        iv_close = findViewById(R.id.iv_close);
        tv_title = findViewById(R.id.tv_title);
        tv_content = findViewById(R.id.tv_content);

        tv_title.setText(title);
        tv_content.setText(content);
        iv_close.setOnClickListener(this);
    }


    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.iv_close) {
            dialog.dismiss();
        }
    }

    public interface onClickDone {
        void copy();
    }

}
