
package com.example.temple.dialog;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.example.temple.R;
import com.lxj.xpopup.core.CenterPopupView;

import org.jetbrains.annotations.NotNull;

/**
 * VIP提示弹窗
 */
public class HintViewPopup extends CenterPopupView implements View.OnClickListener {

    private TextView mTvAffrim, mTvCancle, mTvContent;

    private Context context;
    private String content = "";

    private onClickDone listener;

    public HintViewPopup(@NonNull @NotNull Context context, String content, HintViewPopup.onClickDone onClickDone) {
        super(context);
        this.context = context;
        this.content = content;
        this.listener = onClickDone;

    }

    @Override
    protected int getImplLayoutId() {
        return R.layout.dialog_hint_view;
    }

    @Override
    protected void onCreate() {
        super.onCreate();
        mTvAffrim = findViewById(R.id.tv_affrim);
        mTvAffrim.setOnClickListener(this);
        mTvCancle = findViewById(R.id.tv_close);
        mTvCancle.setOnClickListener(this);

        mTvContent = findViewById(R.id.tv_content);
        mTvContent.setText(content);
    }


    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.tv_affrim) {
            if (listener != null) {
                listener.selectAffrim();
            }
        } else if (view.getId() == R.id.tv_close) {
//            if (listener != null) {
//                listener.selectCancle();
//            }
            dismiss();
        }
    }

    public interface onClickDone {
        void selectAffrim();
//        void selectCancle();
    }

}
