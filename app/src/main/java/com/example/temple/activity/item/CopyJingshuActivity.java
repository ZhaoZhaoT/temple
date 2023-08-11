package com.example.temple.activity.item;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.util.Log;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.core.content.ContextCompat;

import com.example.temple.R;
import com.example.temple.activity.base.BaseTitleActivity;
import com.example.temple.bean.haode.ChaoJinListBean;
import com.example.temple.dialog.HintSelectPopup;
import com.example.temple.dialog.SuccessHintPopup;
import com.example.temple.network.Comments;
import com.example.temple.network.OnError;
import com.example.temple.utils.EventBusUtils;
import com.example.temple.view.SignatureView;
import com.github.xch168.stroketextview.StrokeTextView;
import com.lxj.xpopup.XPopup;
import com.rxjava.rxlife.RxLife;

import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import butterknife.BindView;
import rxhttp.wrapper.param.RxHttp;

public class CopyJingshuActivity extends BaseTitleActivity implements View.OnClickListener {

    @BindView(R.id.iv_left)
    RelativeLayout iv_left;
    @BindView(R.id.tv_word)
    TextView tv_word;
    @BindView(R.id.tv_next)
    TextView tv_next;
    @BindView(R.id.tv_clear)
    TextView tv_clear;
    @BindView(R.id.tv_save)
    TextView tv_save;
    @BindView(R.id.write_word)
    StrokeTextView write_word;
    @BindView(R.id.signatureview)
    SignatureView signatureview;
    @BindView(R.id.tv_title)
    TextView tv_title;
    String name;
    String word;//抄写的数据
    int index = 0;
    String id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_copybook;
    }

    @Override
    protected void initView() {
        baseTitleGone();
        word = getIntent().getStringExtra("content");
        id = getIntent().getStringExtra("id");
        tv_word.setText(word);

        write_word.setText(word.substring(index, index + 1));
        name = getIntent().getStringExtra("title");
        tv_title.setText(name);

    }


    private final String APP_DIR = "com.example.temple";
    HintSelectPopup hintSelectPopup;


    @Override
    protected void initListener() {
        super.initListener();
        iv_left.setOnClickListener(this);
        tv_next.setOnClickListener(this);
        tv_clear.setOnClickListener(this);
        tv_save.setOnClickListener(this);
    }

    private String dir = Environment.getExternalStorageDirectory().getAbsolutePath() + File.separator + APP_DIR + File.separator;

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.iv_left) {
            //先保存
//            saveChaoJin();
            onBackPressed();

        } else if (v.getId() == R.id.tv_next) {
            //先保存
//            saveChaoJin();//不需要保存图片
            index++;
            if (index == word.length()) {
                //去保存
                getSaveHaoderCopy();
            } else {
                if (index == word.length() - 1) {
                    tv_next.setText("完成");
                }
                write_word.setText(word.substring(index, index + 1));
                SpannableString span = new SpannableString(word);
                ForegroundColorSpan colorSpan = new ForegroundColorSpan(ContextCompat.getColor(mContext, R.color.colors_d12e05));
                span.setSpan(colorSpan, 0, index, Spannable.SPAN_EXCLUSIVE_INCLUSIVE);
                tv_word.setText(span);
                signatureview.clear();
            }

        } else if (v.getId() == R.id.tv_clear) {
            signatureview.clear();
        } else if (v.getId() == R.id.tv_save) {
            saveChaoJin();
            signatureview.clear();
        }
    }

    SuccessHintPopup successHintPopup;

    public void getSaveHaoderCopy() {
        RxHttp.postJson(Comments.HAODE_SAVE)
                .add("id", id)
                .add("type", "ZERO")
                .asResponse(ChaoJinListBean.class)
                .doFinally(() -> {

                })
                .to(RxLife.toMain(this))
                .subscribe(model -> {
                    EventBusUtils.post(Comments.HAODE_SAVE_SUCCESS);
                    if (successHintPopup == null) {
                        successHintPopup = new SuccessHintPopup(CopyJingshuActivity.this, "经文抄写保存成功",
                                "我知道了", new SuccessHintPopup.onClickDone() {
                            @Override
                            public void selectAffrim() {
                                successHintPopup.dismiss();
                                finish();
                            }

                        });
                    }
                    new XPopup.Builder(CopyJingshuActivity.this)
                            .dismissOnBackPressed(true)
                            .dismissOnTouchOutside(true)
                            .asCustom(successHintPopup)
                            .show();


                }, (OnError) error -> {
                    onJsonDataGetFailed(error.getErrorCode(), error.getErrorMsg(), 2000);
                });
    }


    private void saveChaoJin() {
        final File fileDir = new File(dir);
        if (!fileDir.exists()) {
            fileDir.mkdirs();
        }
        final String filePath = getFilePath(fileDir);
        if (signatureview.save(filePath)) {//保存成功
//                ToastUtils.showShort("经文抄写保存成功！");

            //发送广播 通知系统相册更新
            Intent intent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
            Uri uri = Uri.fromFile(new File(filePath));
            intent.setData(uri);
            sendBroadcast(intent);
        }

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                //删除图片
                //删除手机中图片
                file.delete();
            }
        }, 3000);//3秒后执行Runnable中的run方法
    }

    /**
     * 得到图片的路径 以及图片的名字
     */
    File file;

    private String getFilePath(File fileDir) {
        SimpleDateFormat simpleDateFormat = (SimpleDateFormat) DateFormat.getDateTimeInstance();
        final String fileName = simpleDateFormat.format(new Date()) + ".png";
        Log.e("filename", "---" + fileName);
        file = new File(fileDir, fileName);
        return file.getAbsolutePath();
    }

    @Override
    public void onBackPressed() {
        if (hintSelectPopup == null) {
            hintSelectPopup = new HintSelectPopup(CopyJingshuActivity.this, "退出当前页面抄经记录不会保存，确定关闭吗？",
                    "关闭", "继续", new HintSelectPopup.onClickDone() {
                @Override
                public void selectAffrim() {
                    hintSelectPopup.dismiss();
                }

                @Override
                public void selectCancle() {
                    hintSelectPopup.dismiss();
                    finish();
                }

            });
        }
        new XPopup.Builder(CopyJingshuActivity.this)
                .dismissOnBackPressed(true)
                .dismissOnTouchOutside(true)
                .asCustom(hintSelectPopup)
                .show();
    }
}