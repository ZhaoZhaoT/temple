
package com.example.temple.dialog;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.example.temple.R;
import com.lxj.xpopup.core.AttachPopupView;

import org.jetbrains.annotations.NotNull;

/**
 * 发布文章添加数据提示弹窗
 */
public class AddContentPopup extends AttachPopupView implements View.OnClickListener {

    private TextView tv_pic, tv_text, tv_video;

    private onClickDone listener;

    public AddContentPopup(@NonNull @NotNull Context context, AddContentPopup.onClickDone onClickDone) {
        super(context);
        this.listener = onClickDone;
    }

    @Override
    protected int getImplLayoutId() {
        return R.layout.dialog_add_content_view;
    }

    @Override
    protected void onCreate() {
        super.onCreate();
        tv_pic = findViewById(R.id.tv_pic);
        tv_pic.setOnClickListener(this);
        tv_text = findViewById(R.id.tv_text);
        tv_text.setOnClickListener(this);
        tv_video = findViewById(R.id.tv_video);
        tv_video.setOnClickListener(this);

    }


    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.tv_pic) {
            if (listener != null) {
                listener.selectPic();
            }
            dialog.dismiss();

        } else if (view.getId() == R.id.tv_text) {
            if (listener != null) {
                listener.selectText();
            }
            dialog.dismiss();
        }else if (view.getId() == R.id.tv_video) {
            if (listener != null) {
                listener.selectVideo();
            }
            dialog.dismiss();
        }
    }

    public interface onClickDone {
        void selectPic();
        void selectText();
        void selectVideo();
    }

}
