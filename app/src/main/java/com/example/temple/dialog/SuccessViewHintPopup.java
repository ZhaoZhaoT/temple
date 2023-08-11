
package com.example.temple.dialog;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.example.temple.R;
import com.lxj.xpopup.core.CenterPopupView;

import org.jetbrains.annotations.NotNull;

/**
 * 提示 长提示框弹窗
 */
public class SuccessViewHintPopup extends CenterPopupView implements View.OnClickListener {
    private TextView mTvAffrim, mTvContent;
    private Context context;
    private String content = "";
    private String affrim = "";
    private onClickDone listener;

    public SuccessViewHintPopup(@NonNull @NotNull Context context, String content, String affrim, SuccessViewHintPopup.onClickDone onClickDone) {
        super(context);
        this.context = context;
        this.content = content;
        this.affrim = affrim;
        this.listener = onClickDone;
    }

    @Override
    protected int getImplLayoutId() {
        return R.layout.dialog_success_view_hint;
    }

    @Override
    protected void onCreate() {
        super.onCreate();
        mTvAffrim = findViewById(R.id.tv_affrim);
        mTvAffrim.setOnClickListener(this);
        mTvAffrim.setText(affrim);

        mTvContent = findViewById(R.id.tv_content);
        mTvContent.setText(content);
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.tv_affrim) {
            if (listener != null) {
                listener.selectAffrim();
            }
            dialog.dismiss();
        }
    }

    public interface onClickDone {
        void selectAffrim();
    }

}
