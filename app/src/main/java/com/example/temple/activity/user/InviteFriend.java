package com.example.temple.activity.user;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.blankj.utilcode.util.BarUtils;
import com.blankj.utilcode.util.ConvertUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.example.temple.R;
import com.example.temple.activity.base.BaseActivity;
import com.example.temple.bean.UserInfoBean;
import com.example.temple.utils.DataCacheUtil;
import com.example.temple.utils.GlideUtils;
import com.example.temple.utils.SaveImage;
import com.yzq.zxinglibrary.encode.CodeCreator;

import butterknife.BindView;
import butterknife.ButterKnife;

public class InviteFriend extends BaseActivity {
    @BindView(R.id.tv_back)
    ImageView ivBack;
    @BindView(R.id.iv_head)
    ImageView ivHead;
    @BindView(R.id.tv_user_name)
    TextView tvUserName;
    @BindView(R.id.tv_save)
    TextView tvSave;
    @BindView(R.id.iv_qr_code)
    ImageView ivQrCode;
    @BindView(R.id.save_img)
    LinearLayout saveImg;



    @Override
    protected int getLayoutId() {
        return R.layout.invite_friend;
    }

    @Override
    protected void initView() {
        baseTitleBar.setVisibility(View.GONE);
        BarUtils.setStatusBarLightMode(this, true);

        UserInfoBean user = DataCacheUtil.getInstance(this).getUserInfo();
        GlideUtils.loadCircleImage(this,user.getAvatarUrl(),R.mipmap.default_head,ivHead);
        tvUserName.setText(user.getNickName());
        if (user!=null) {
            String link = user.getSharePath() + user.getRandomId();
            Bitmap bitmap = CodeCreator.createQRCode(link, ConvertUtils.dp2px(95f), ConvertUtils.dp2px(95f), null);
            ivQrCode.setImageBitmap(bitmap);
        }else {//展示其他样式
            ToastUtils.showShort("登录失效");
        }
    }

    @Override
    protected void initListener() {
        ivBack.setOnClickListener(v -> {
            finish();
        });
        tvSave.setOnClickListener(v -> {
            SaveImage.save(this, saveImg);
        });



    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}