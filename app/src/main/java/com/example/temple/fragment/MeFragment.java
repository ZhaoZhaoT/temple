package com.example.temple.fragment;

import static android.app.Activity.RESULT_OK;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.blankj.utilcode.util.ToastUtils;
import com.example.temple.R;
import com.example.temple.activity.AboutUsActivity;
import com.example.temple.activity.login.LoginActivity;
import com.example.temple.activity.user.GongXian2Activity;
import com.example.temple.activity.user.InviteFriend;
import com.example.temple.activity.user.MyXZActivity;
import com.example.temple.activity.user.SettingActivity;
import com.example.temple.activity.user.UserInfoActivity;
import com.example.temple.bean.UserInfoBean;
import com.example.temple.network.Comments;
import com.example.temple.network.OnError;
import com.example.temple.utils.AppManager;
import com.example.temple.utils.BaseUtils;
import com.example.temple.utils.DataCacheUtil;
import com.example.temple.utils.GlideUtils;
import com.lxj.xpopup.XPopup;
import com.lxj.xpopup.interfaces.OnConfirmListener;
import com.rxjava.rxlife.RxLife;

import butterknife.BindView;
import rxhttp.wrapper.param.RxHttp;


public class MeFragment extends BaseFragment {
    @BindView(R.id.iv_head)
    ImageView ivHead;
    @BindView(R.id.tv_user_phone)
    TextView tvUserPhone;
    @BindView(R.id.tv_user_name)
    TextView tvUserName;
    @BindView(R.id.user_level)
    TextView user_level;
    @BindView(R.id.logout)
    TextView logout;

    @BindView(R.id.rl_mx)
    RelativeLayout rlMx;
    @BindView(R.id.rl_wdxz)
    RelativeLayout rlWdxz;
    @BindView(R.id.rl_aboutus)
    RelativeLayout tlAboutUs;

    @BindView(R.id.rl_setting)
    RelativeLayout rlSetting;
    @BindView(R.id.rl_yqhy)
    RelativeLayout iv_yqhy;

    @Override
    protected void initView(Bundle savedInstanceState) {
        UserInfoBean user = DataCacheUtil.getInstance(getContext()).getUserInfo();
        if (user == null) {
            getUserInfo();
        } else {
            GlideUtils.loadCircleImage(getContext(), user.getAvatarUrl(), R.mipmap.default_head, ivHead);
            ivHead.setTag(user.getAvatarUrl());
            tvUserPhone.setText(user.getPhone());
            if (!TextUtils.isEmpty(user.getUserLevelName())) {
                if (user.getUserLevelName().equals("ZERO")) {
                    user_level.setVisibility(View.GONE);
                    user_level.setBackgroundResource(R.drawable.yk_bg);
                    user_level.setText("游客");
                } else if (user.getUserLevelName().equals("ONE")) {
                    user_level.setVisibility(View.VISIBLE);
                    user_level.setBackgroundResource(R.drawable.xz_bg);
                    user_level.setText("信众");
                } else if (user.getUserLevelName().equals("TWO")) {
                    user_level.setVisibility(View.VISIBLE);
                    user_level.setBackgroundResource(R.drawable.tz_bg);
                    user_level.setText("堂主");
                }
            }

            tvUserName.setText(user.getNickName());
        }

        ivHead.setOnClickListener(v -> {
            Intent intent = new Intent(getContext(), UserInfoActivity.class);
            intent.putExtra("name", tvUserName.getText().toString());
            intent.putExtra("head", (String) ivHead.getTag());
            startActivityForResult(intent, Comments.HOME_TO_ACCOUNT);
        });
        rlMx.setOnClickListener(v -> {
            startActivity(new Intent(getContext(), GongXian2Activity.class));
        });
        rlWdxz.setOnClickListener(v -> {
            startActivity(new Intent(getContext(), MyXZActivity.class));
        });
        tlAboutUs.setOnClickListener(v -> {
            startActivity(new Intent(getContext(), AboutUsActivity.class));
        });
        rlSetting.setOnClickListener(v -> {
            startActivity(new Intent(getContext(), SettingActivity.class));
        });
        iv_yqhy.setOnClickListener(v -> {
            startActivity(new Intent(getContext(), InviteFriend.class));
        });

        logout.setOnClickListener(v -> {
            showConfrimDialog();
        });

    }


    private void showConfrimDialog() {
        new XPopup.Builder(getContext())
                .dismissOnBackPressed(true)
                .dismissOnTouchOutside(true)
                .asConfirm("确定要退出登录吗？", null,
                        "取消", "确认退出",
                        new OnConfirmListener() {
                            @Override
                            public void onConfirm() {
                                loginOut();
                            }
                        }, null, false).show();
    }

    public void loginOut() {
        RxHttp.postJson(Comments.LOGIN_OUT)
                .asResponse(String.class)
                .to(RxLife.toMain(this))
                .subscribe(model -> {
                    onJsonDataGetSuccess(model.getData(), 2000);
                }, (OnError) error -> {
                    onJsonDataGetFailed(error.getErrorCode(), error.getErrorMsg(), 2000);
                });
    }

    public void getUserInfo() {
        RxHttp.get(Comments.GET_USER_INFO)
                .asResponse(UserInfoBean.class)
                .to(RxLife.toMain(this))
                .subscribe(model -> {
                    if (model.getData() != null) {
                        onJsonDataGetSuccess(model.getData(), 1000);
                    }
                }, (OnError) error -> {
                    onJsonDataGetFailed(error.getErrorCode(), error.getErrorMsg(), 1000);
                });

    }

    @Override
    public void onJsonDataGetSuccess(Object re_data, int reqcode) {
        super.onJsonDataGetSuccess(re_data, reqcode);
        if (reqcode == 1000) {
            UserInfoBean user = (UserInfoBean) re_data;
            DataCacheUtil.getInstance(getContext()).getmSharedPreferences().saveString("randomId", user.getRandomId());
            DataCacheUtil.getInstance(getContext()).saveUserInfo(user);

            if (tvUserName != null) {
                tvUserName.setText(user.getNickName());
                tvUserPhone.setText(BaseUtils.phoneEncode(user.getPhone()));
                if (!TextUtils.isEmpty(user.getUserLevelName())) {
                    if (user.getUserLevelName().equals("ZERO")) {
                        user_level.setVisibility(View.GONE);
                        user_level.setBackgroundResource(R.drawable.yk_bg);
                        user_level.setText("游客");
                    } else if (user.getUserLevelName().equals("ONE")) {
                        user_level.setVisibility(View.VISIBLE);
                        user_level.setBackgroundResource(R.drawable.xz_bg);
                        user_level.setText("信众");
                    } else if (user.getUserLevelName().equals("TWO")) {
                        user_level.setVisibility(View.VISIBLE);
                        user_level.setBackgroundResource(R.drawable.tz_bg);
                        user_level.setText("堂主");
                    }
                }
                GlideUtils.loadCircleImage(getContext(), user.getAvatarUrl(), R.mipmap.default_head, ivHead);
                ivHead.setTag(user.getAvatarUrl());
            } else {
                ToastUtils.showLong("应用程序异常，请重启APP");
            }
        } else if (reqcode == 2000) {
            DataCacheUtil.getInstance(getContext()).saveUser(null);
            DataCacheUtil.getInstance(getContext()).saveUserInfo(null);
            DataCacheUtil.getInstance(getContext()).getmSharedPreferences().saveString("token", "");
            DataCacheUtil.getInstance(getContext()).getmSharedPreferences().saveString("randomId", "");
            DataCacheUtil.getInstance(getContext()).getmSharedPreferences().saveString("kfid", "");
            getActivity().setResult(RESULT_OK);
            startActivity(new Intent(getContext(), LoginActivity.class));
            AppManager.getAppManager().finishAllActivity();
//            finish();
        }
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_me;
    }

    @Override
    protected void initListener() {

    }


    @Override
    protected void lazyInit() {
        super.lazyInit();

    }


    @Override
    public void onResume() {
        super.onResume();
        getUserInfo();
    }


}
