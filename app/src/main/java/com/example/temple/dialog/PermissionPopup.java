package com.example.temple.dialog;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.example.temple.R;
import com.lxj.xpopup.core.CenterPopupView;

import org.jetbrains.annotations.NotNull;

/**
 * 权限弹窗
 */
public class PermissionPopup extends CenterPopupView implements View.OnClickListener {

    private TextView tv_title;
    private TextView tv_content;
    private TextView down;

    private Context context;

    private onClickDone listener;
    String title;
    String content;


    public PermissionPopup(@NonNull @NotNull Context context, String title, String content, onClickDone onClickDone) {
        super(context);
        this.context = context;
        this.title = title;
        this.content = content;
        this.listener = onClickDone;

    }


    @Override
    protected int getImplLayoutId() {
        return R.layout.dialog_permission;
    }

    @Override
    protected void onCreate() {
        super.onCreate();
        tv_title = findViewById(R.id.tv_title);
        tv_title.setText(title);
        tv_content = findViewById(R.id.tv_content);
        tv_content.setText(title);
        down = findViewById(R.id.tv_content);

        down.setOnClickListener(this);

    }


    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.down) {
            if (listener != null) {
                listener.onDowm();
            }
            dialog.dismiss();
        }
    }

    public interface onClickDone {
        void onDowm();
    }

}

