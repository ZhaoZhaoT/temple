package com.example.temple.dialog;

import android.content.Context;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.example.temple.R;
import com.lxj.xpopup.core.CenterPopupView;

import org.jetbrains.annotations.NotNull;

public class JieQianPopup extends CenterPopupView implements View.OnClickListener  {

    TextView tv_click;
    TextView tv_jieqian;

    LinearLayout ll_top;

    public JieQianPopup(@NonNull @NotNull Context context) {
        super(context);

    }

    @Override
    protected int getImplLayoutId() {
        return R.layout.dialog_qr_code;
    }

    @Override
    protected void onCreate() {
        super.onCreate();
        tv_click = findViewById(R.id.tv_click);
        ll_top = findViewById(R.id.ll_top);
        tv_jieqian = findViewById(R.id.tv_jieqian);
        tv_click.setOnClickListener(this);
    }


    @Override
    public void onClick(View view) {
        if(view.getId() == R.id.tv_click) {
            ll_top.setVisibility(GONE);
            tv_jieqian.setVisibility(VISIBLE);
        }
    }

    public interface onClickDone {
        void copy();
    }

}
