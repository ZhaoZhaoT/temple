package com.example.temple.activity.login;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.blankj.utilcode.util.BarUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.example.temple.MainActivity;
import com.example.temple.R;
import com.example.temple.activity.BaseWebActivity;
import com.example.temple.activity.base.BaseActivity;
import com.example.temple.bean.UserBean;
import com.example.temple.network.Comments;
import com.example.temple.network.OnError;
import com.example.temple.utils.DataCacheUtil;
import com.rxjava.rxlife.RxLife;

import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.functions.Action;
import io.reactivex.rxjava3.functions.Consumer;
import rxhttp.wrapper.param.RxHttp;

public class LoginActivity extends BaseActivity implements View.OnClickListener {

    @BindView(R.id.tv_login)
    TextView tvLogin;

    @BindView(R.id.tv_regiest)
    TextView tvRegiest;

    @BindView(R.id.forget_password)
    TextView tvForgetPassword;

    @BindView(R.id.get_code)
    TextView tvGetCode;

    @BindView(R.id.login)
    ImageView ivLogin;

    @BindView(R.id.regiest)
    ImageView ivRegiest;

    @BindView(R.id.ll_login)
    LinearLayout llLogin;
    @BindView(R.id.ll_regiest)
    LinearLayout llRegiest;

    @BindView(R.id.login_account)
    EditText edLoginAccount;
    @BindView(R.id.regiest_account)
    EditText edRegiestAccount;
    @BindView(R.id.login_password)
    EditText edLoginPassword;
    @BindView(R.id.regiest_code)
    EditText edRegiestCode;
    @BindView(R.id.regiest_password)
    EditText edRegiestPassword;
    @BindView(R.id.regiest_confirm_password)
    EditText edRegiestConfirmPassword;
    boolean flag = false;
    @BindView(R.id.ck_agree)
    CheckBox ckAgree;
    @BindView(R.id.ck_agree1)
    CheckBox ckAgree1;

    @BindView(R.id.tv_user_protrol)
    TextView tvUserProtrol;
    @BindView(R.id.tv_user_sercert)
    TextView tvUserSercert;

    @BindView(R.id.tv_user_protrol1)
    TextView tvUserProtrol1;
    @BindView(R.id.tv_user_sercert1)
    TextView tvUserSercert1;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_login;
    }

    @Override
    protected void initView() {
        baseTitleBar.setVisibility(View.GONE);
        BarUtils.setStatusBarLightMode(this, true);
    }

    @Override
    protected void initListener() {
        tvLogin.setOnClickListener(this);
        tvRegiest.setOnClickListener(this);
        tvGetCode.setOnClickListener(this);
        ivRegiest.setOnClickListener(this);
        ivLogin.setOnClickListener(this);
        tvForgetPassword.setOnClickListener(this);
        tvUserProtrol.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(mContext, BaseWebActivity.class)
                        .putExtra("title","用户协议"));
            }
        });
        tvUserSercert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(mContext, BaseWebActivity.class)
                        .putExtra("title","隐私政策"));
            }
        });
        tvUserProtrol1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(mContext, BaseWebActivity.class)
                        .putExtra("title","用户协议"));
            }
        });
        tvUserSercert1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(mContext, BaseWebActivity.class)
                        .putExtra("title","隐私政策"));
            }
        });

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @Override
    public void onClick(View view) {
        String regPhone = edRegiestAccount.getText().toString();
        String regPwd = edRegiestPassword.getText().toString();
        String regConfirmPwd = edRegiestConfirmPassword.getText().toString();
        String regCode = edRegiestCode.getText().toString();

        switch (view.getId()) {
            case R.id.tv_login:
                llLogin.setVisibility(View.VISIBLE);
                llRegiest.setVisibility(View.GONE);
                tvLogin.setTextColor(getResources().getColor(R.color.text_main2));
                tvRegiest.setTextColor(getResources().getColor(R.color.text_gray6));
                tvLogin.getPaint().setFakeBoldText(true);
                tvRegiest.getPaint().setFakeBoldText(false);
                break;
            case R.id.tv_regiest:
                llLogin.setVisibility(View.GONE);
                llRegiest.setVisibility(View.VISIBLE);
                tvLogin.setTextColor(getResources().getColor(R.color.text_gray6));
                tvRegiest.setTextColor(getResources().getColor(R.color.text_main2));
                tvLogin.getPaint().setFakeBoldText(false);
                tvRegiest.getPaint().setFakeBoldText(true);
                break;
            case R.id.login:
                String phone = edLoginAccount.getText().toString();
                String pwd = edLoginPassword.getText().toString();

                if (TextUtils.isEmpty(phone)) {
                    ToastUtils.showShort("请输入手机号");
                } else if (TextUtils.isEmpty(pwd)) {
                    ToastUtils.showShort("请输入密码");
                } else if (!ckAgree.isChecked()) {
                    ToastUtils.showShort("请阅读《服务条款及隐私协议》");
                } else {
                    getData(phone, pwd);
                }
                break;
            case R.id.regiest:

                if (TextUtils.isEmpty(regPhone)) {
                    ToastUtils.showShort("请输入手机号");
                } else if (TextUtils.isEmpty(regCode)) {
                    ToastUtils.showShort("请输入验证码");
                } else if (TextUtils.isEmpty(regPwd)) {
                    ToastUtils.showShort("请输入密码");
                } else if (TextUtils.isEmpty(regConfirmPwd)) {
                    ToastUtils.showShort("请输入确认密码");
                } else if (!ckAgree1.isChecked()) {
                    ToastUtils.showShort("请阅读《服务条款及隐私协议》");
                }else {
                    getRegData(regPhone, regPwd,regConfirmPwd,regCode);
                }
                break;
            case R.id.get_code:
                if (TextUtils.isEmpty(regPhone)) {
                    ToastUtils.showShort("请输入手机号");
                }
                if(!flag) {
                    getSmsCode(regPhone);

                }
//                startActivity(new Intent(mContext, MainActivity.class));
                break;
            case R.id.forget_password:
                startActivity(new Intent(mContext,FindPassword.class));
                break;
        }
    }

    private void daoJs() {
        //intervalRange四个参数分别为：从0开始、到60结束、延时0开始，单位时间（NANOSECONDS,MICROSECONDS,MILLISECONDS,SECONDS,MINUTES,HOURS,DAYS）。
        Disposable countdownDisposable = Flowable.intervalRange(0, 60, 0, 1, TimeUnit.SECONDS)
                .observeOn(AndroidSchedulers.mainThread())
                .doOnNext(new Consumer<Long>() {
                    @Override
                    public void accept(Long aLong) throws Exception {
                        tvGetCode.setText(""+ (60 - aLong));
                        flag = true;
                    }
                })
                .doOnComplete(new Action() {
                    @Override
                    public void run() throws Exception {
                        tvGetCode.setText("重新获取");
                        flag = false;
                        //倒计时完毕事件处理
                        finish();
                    }
                })
                .subscribe();
    }

    public void getData(String phone, String pwd) {
        showWaitDialog("",false);
        RxHttp.postJson(Comments.USER_LOGIN)
                .add("phone", phone)
                .add("password", pwd)
                .asResponse(UserBean.class)
                .to(RxLife.toMain(this))
                .subscribe(model -> {
                    onJsonDataGetSuccess(model.getData(), 1000);
                }, (OnError) error -> {
                    onJsonDataGetFailed(error.getErrorCode(), error.getErrorMsg(), 1000);
                });
    }

    public void getSmsCode(String phone) {
        showWaitDialog("",false);
        RxHttp.postJson(Comments.SMS_CODE)
                .add("phone", phone)
                .asResponse(UserBean.class)
                .to(RxLife.toMain(this))
                .subscribe(model -> {
                    onJsonDataGetSuccess(model.getData(), 3000);
                }, (OnError) error -> {
                    onJsonDataGetFailed(error.getErrorCode(), error.getErrorMsg(), 3000);
                });
    }

    public void getRegData(String phone, String pwd,String confiemPwd,String code) {
        showWaitDialog("",false);
        RxHttp.postJson(Comments.REGISTER_USER)
                .add("phone", phone)
                .add("phoneCode", code)
                .add("inviteCode", "")
                .add("password", pwd)
                .add("confirmPassword", confiemPwd)
                .asResponse(String.class)
                .to(RxLife.toMain(this))
                .subscribe(model -> {
                    onJsonDataGetSuccess(model.getData(), 2000);
                }, (OnError) error -> {
                    onJsonDataGetFailed(error.getErrorCode(), error.getErrorMsg(), 2000);
                });
    }

    @Override
    public void onJsonDataGetSuccess(Object re_data, int reqcode) {
        super.onJsonDataGetSuccess(re_data, reqcode);
        if (reqcode == 1000) {
            UserBean user = (UserBean) re_data;
            DataCacheUtil.getInstance(this).saveUser(user);
            DataCacheUtil.getInstance(this).getmSharedPreferences().saveString("token", "bearer "+user.getToken());
            startActivity(new Intent(mContext, MainActivity.class));
            finish();
        }else if (reqcode == 2000) {
           /* AboutUsBean bean= (AboutUsBean) re_data;
            phone=bean.getCompany_phone();*/
            ToastUtils.showShort("注册成功");
            llLogin.setVisibility(View.VISIBLE);
            llRegiest.setVisibility(View.GONE);
            tvLogin.setTextColor(getResources().getColor(R.color.text_main2));
            tvRegiest.setTextColor(getResources().getColor(R.color.text_gray6));
            tvLogin.getPaint().setFakeBoldText(true);
            tvRegiest.getPaint().setFakeBoldText(false);
        }else if(reqcode == 3000) {
            ToastUtils.showShort("验证码发送成功成功");
            daoJs();
        }
    }
}