
package com.example.temple.dialog;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.example.temple.R;
import com.lxj.xpopup.core.CenterPopupView;

import org.jetbrains.annotations.NotNull;

/**
 * 提示弹窗
 */
public class HintSelectPopup extends CenterPopupView implements View.OnClickListener {

    private TextView mTvAffrim, mTvCancle, mTvContent;

    private Context context;
    private String content = "";
    private String cancle = "";
    private String affrim = "";

    private onClickDone listener;

    public HintSelectPopup(@NonNull @NotNull Context context, String content, String cancle, String affrim, HintSelectPopup.onClickDone onClickDone) {
        super(context);
        this.context = context;
        this.content = content;
        this.cancle = cancle;
        this.affrim = affrim;
        this.listener = onClickDone;

    }

    @Override
    protected int getImplLayoutId() {
        return R.layout.dialog_hint;
    }

    @Override
    protected void onCreate() {
        super.onCreate();
        mTvAffrim = findViewById(R.id.tv_affrim);
        mTvAffrim.setOnClickListener(this);
        mTvCancle = findViewById(R.id.tv_cancle);
        mTvCancle.setText(cancle);
        mTvAffrim.setText(affrim);
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
        } else if (view.getId() == R.id.tv_cancle) {
            if (listener != null) {
                listener.selectCancle();
            }
        }
    }

    public interface onClickDone {
        void selectAffrim();

        void selectCancle();
    }

}
