package com.example.temple.dialog;

import android.content.Context;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.blankj.utilcode.util.ToastUtils;
import com.example.temple.R;
import com.lxj.xpopup.core.CenterPopupView;

import org.jetbrains.annotations.NotNull;

/**
 * 创建家庭
 */
public class CreateFamilyPopup extends CenterPopupView implements View.OnClickListener {

    private TextView tv_affrim;
    private EditText ed_content;
    private TextView tv_close;

    private Context context;

    private String surname = "";
    private onClickDone listener;



    public CreateFamilyPopup(@NonNull @NotNull Context context, CreateFamilyPopup.onClickDone onClickDone) {
        super(context);
        this.context = context;
        this.listener = onClickDone;

    }

    @Override
    protected int getImplLayoutId() {
        return R.layout.dialog_creat_family;
    }

    @Override
    protected void onCreate() {
        super.onCreate();
        tv_affrim = findViewById(R.id.tv_affrim);
        tv_affrim.setOnClickListener(this);
        ed_content = findViewById(R.id.ed_content);
        tv_close = findViewById(R.id.tv_close);
        tv_close.setOnClickListener(this);
    }


    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.tv_affrim) {
            if (ed_content.getText().toString().isEmpty()) {
                ToastUtils.showShort("请输入名字");
            } else {
                if (listener != null) {
                    listener.selectData(ed_content.getText().toString());
                }
                dialog.dismiss();
            }

        } else if (view.getId() == R.id.tv_close) {
            dialog.dismiss();
        }
    }

    public interface onClickDone {
        void selectData(String surname);
    }

}
