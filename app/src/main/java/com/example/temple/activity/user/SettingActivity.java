package com.example.temple.activity.user;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.temple.BaseApplication;
import com.example.temple.R;
import com.example.temple.activity.base.BaseTitleActivity;
import com.example.temple.activity.login.UpdatePwdActivity;
import com.example.temple.bean.UserInfoBean;
import com.example.temple.network.Comments;
import com.example.temple.utils.DataCacheUtil;
import com.example.temple.utils.GetCacheUtils;
import com.example.temple.utils.GlideUtils;
import com.lxj.xpopup.XPopup;
import com.lxj.xpopup.interfaces.OnConfirmListener;

import butterknife.BindView;

public class SettingActivity extends BaseTitleActivity {

    @BindView(R.id.tv_cache_size)
    TextView tvCacheSize;

    @BindView(R.id.iv_head)
    ImageView ivHead;
    @BindView(R.id.tv_user_phone)
    TextView tvUserPhone;
    @BindView(R.id.tv_user_name)
    TextView tvUserName;

    @BindView(R.id.rl_userinfo)
    RelativeLayout rlUserInfo;
    @BindView(R.id.rl_address)
    RelativeLayout rlAddress;
    @BindView(R.id.rl_updatepassword)
    RelativeLayout rlUpdatepassword;

    @BindView(R.id.ll_clear)
    RelativeLayout llClear;

    @BindView(R.id.ck_play)
    CheckBox ck_play;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_setting;
    }

    @Override
    protected void initView() {
        baseTitle.setText("设置");
        statusBar.setBackgroundColor(getResources().getColor(R.color.background));
        rlTop.setBackgroundColor(getResources().getColor(R.color.background));

        GetCacheUtils utils = new GetCacheUtils();
        tvCacheSize.setText(utils.getCacheSize(mContext));


        if (DataCacheUtil.getInstance(this).getmSharedPreferences().getBoolean("bgMusic", false)) {
            ck_play.setChecked(true);
        } else {
            ck_play.setChecked(false);
        }
        ck_play.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked == true) {//打开
                    DataCacheUtil.getInstance(SettingActivity.this).getmSharedPreferences().saveBoolean("bgMusic", true);
                    BaseApplication.getInstance().getMediaPlayer().start();
                } else {//关闭
                    DataCacheUtil.getInstance(SettingActivity.this).getmSharedPreferences().saveBoolean("bgMusic", false);
                    BaseApplication.getInstance().getMediaPlayer().pause();
                }
            }
        });
        freshView();
    }

    private void freshView() {
        UserInfoBean user = DataCacheUtil.getInstance(this).getUserInfo();
        if (!TextUtils.isEmpty(user.getAvatarUrl()))
            GlideUtils.loadCircleImage(this, user.getAvatarUrl(), R.mipmap.default_head, ivHead);
        ivHead.setTag(user.getAvatarUrl());
        tvUserPhone.setText(user.getPhone());
        tvUserName.setText(user.getNickName());
    }

    @Override
    protected void onResume() {
        super.onResume();
        freshView();
    }

    @Override
    protected void initListener() {
        super.initListener();
        rlUserInfo.setOnClickListener(v -> {
            Intent intent = new Intent(this, UserInfoActivity.class);
            intent.putExtra("name", tvUserName.getText().toString());
            intent.putExtra("head", (String) ivHead.getTag());
            startActivityForResult(intent, Comments.HOME_TO_ACCOUNT);
        });

        rlAddress.setOnClickListener(v -> {
            startActivity(new Intent(this, AddressListActivity.class));
        });
        llClear.setOnClickListener(v -> {
            showClearDialog();
        });
        rlUpdatepassword.setOnClickListener(v -> {
            startActivity(new Intent(this, UpdatePwdActivity.class));
        });

    }

    private void showClearDialog() {
        new XPopup.Builder(this)
                .dismissOnBackPressed(true)
                .dismissOnTouchOutside(true)
                .asConfirm("提示", "你确定清空缓存吗",
                        "取消", "确定",
                        new OnConfirmListener() {
                            @Override
                            public void onConfirm() {
                                GetCacheUtils utils = new GetCacheUtils();
                                utils.clearImageAllCache(mContext);
                                tvCacheSize.setText("0KB");
                            }
                        }, null, false).show();
    }


}