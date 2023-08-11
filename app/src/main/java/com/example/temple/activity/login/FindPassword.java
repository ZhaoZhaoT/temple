package com.example.temple.activity.login;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.blankj.utilcode.util.BarUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.example.temple.R;
import com.example.temple.activity.base.BaseActivity;
import com.example.temple.bean.UserBean;
import com.example.temple.network.Comments;
import com.example.temple.network.OnError;
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

public class FindPassword extends BaseActivity {
    @BindView(R.id.tv_back)
    ImageView ivBack;
    @BindView(R.id.login)
    ImageView ivFindPassword;

    @BindView(R.id.et_phone)
    EditText etPhone;

    @BindView(R.id.et_code)
    EditText etCode;
    @BindView(R.id.tv_send_sms)
    TextView tvSendSms;

    @BindView(R.id.et_pwd)
    EditText etPwd;

    @BindView(R.id.et_confirm_pwd)
    EditText etConfirmPwd;



    @Override
    protected int getLayoutId() {
        return R.layout.findpassword_regiest;
    }

    @Override
    protected void initView() {
        baseTitleBar.setVisibility(View.GONE);
        BarUtils.setStatusBarLightMode(this, true);
    }

    @Override
    protected void initListener() {
        ivBack.setOnClickListener(v -> {
            finish();
        });

        tvSendSms.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String phone = etPhone.getText().toString();
                if (TextUtils.isEmpty(phone)) {
                    ToastUtils.showShort("请输入手机号");
                } else {
                    getSms(phone);
                }
            }
        });

        ivFindPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String phone = etPhone.getText().toString();
                String code = etCode.getText().toString();
                String pwd = etPwd.getText().toString();
                String pwd2 = etConfirmPwd.getText().toString();

                if (TextUtils.isEmpty(phone)) {
                    ToastUtils.showShort("请输入手机号");
                }else if (TextUtils.isEmpty(code)) {
                    ToastUtils.showShort("请输入验证码");
                }  else if (TextUtils.isEmpty(pwd)) {
                    ToastUtils.showShort("请输入密码");
                } else if (TextUtils.isEmpty(pwd2)) {
                    ToastUtils.showShort("请确认密码");
                } else {
                    RxHttp.postJson(Comments.FIND_PWD)
                            .add("phone", phone)
                            .add("verificationCode",code)
                            .add("newPassword",pwd)
                            .add("confirmPassword",pwd2)
                            .asResponse(String.class)
                            .to(RxLife.toMain(FindPassword.this))
                            .subscribe(model -> {
                                onJsonDataGetSuccess(model.getData(), 2000);
                            }, (OnError) error -> {
                                onJsonDataGetFailed(error.getErrorCode(), error.getErrorMsg(), 2000);
                            });
                }
            }
        });

    }


    public void getSms(String phone) {
        showWaitDialog("",false);
        RxHttp.postJson(Comments.SMS_CODE)
                .add("phone", phone)
                .asResponse(UserBean.class)
                .to(RxLife.toMain(this))
                .subscribe(model -> {
                    onJsonDataGetSuccess(model.getData(), 1000);
                }, (OnError) error -> {
                    onJsonDataGetFailed(error.getErrorCode(), error.getErrorMsg(), 1000);
                });
    }


    @Override
    public void onJsonDataGetSuccess(Object re_data, int reqcode) {
        super.onJsonDataGetSuccess(re_data, reqcode);
        if (reqcode == 1000) {
            ToastUtils.showShort("验证码发送成功成功");
            daoJs();
        }else if(reqcode==2000){
            ToastUtils.showShort("修改成功");
            finish();
        }
    }

    boolean flag = false;

    private void daoJs() {
        //intervalRange四个参数分别为：从0开始、到60结束、延时0开始，单位时间（NANOSECONDS,MICROSECONDS,MILLISECONDS,SECONDS,MINUTES,HOURS,DAYS）。
        Disposable countdownDisposable = Flowable.intervalRange(0, 60, 0, 1, TimeUnit.SECONDS)
                .observeOn(AndroidSchedulers.mainThread())
                .doOnNext(new Consumer<Long>() {
                    @Override
                    public void accept(Long aLong) throws Exception {
                        tvSendSms.setText(""+ (60 - aLong));
                        flag = true;
                    }
                })
                .doOnComplete(new Action() {
                    @Override
                    public void run() throws Exception {
                        tvSendSms.setText("重新获取");
                        flag = false;
                        //倒计时完毕事件处理
                        finish();
                    }
                })
                .subscribe();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}