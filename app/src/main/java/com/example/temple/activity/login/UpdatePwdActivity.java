package com.example.temple.activity.login;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.blankj.utilcode.util.ToastUtils;
import com.example.temple.R;
import com.example.temple.activity.base.BaseTitleActivity;
import com.example.temple.network.Comments;
import com.example.temple.network.OnError;
import com.rxjava.rxlife.RxLife;

import butterknife.BindView;
import rxhttp.wrapper.param.RxHttp;

public class UpdatePwdActivity extends BaseTitleActivity {


//    @BindView(R.id.et_phone)
//    EditText etPhone;
//    @BindView(R.id.ll_phone)
//    LinearLayout llPhone;
    @BindView(R.id.et_old_pwd)
    EditText etOldPwd;
    @BindView(R.id.ll_code)
    LinearLayout llCode;
    @BindView(R.id.et_pwd)
    EditText etPwd;
    @BindView(R.id.ll_pwd)
    LinearLayout llPwd;
    @BindView(R.id.et_confirm_pwd)
    EditText etConfirmPwd;
    @BindView(R.id.ll_confirm_pwd)
    LinearLayout llConfirmPwd;
    @BindView(R.id.tv_update)
    TextView tvUpdate;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_update_pwd;
    }

    @Override
    protected void initView() {
        baseTitle.setText("修改密码");
    }

    @Override
    protected void initListener() {
        super.initListener();


        tvUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                String phone = etPhone.getText().toString();
                String old = etOldPwd.getText().toString();
                String pwd = etPwd.getText().toString();
                String pwd2 = etConfirmPwd.getText().toString();

//                if (TextUtils.isEmpty(phone)) {
//                    ToastUtils.showShort("请输入手机号");
//                }else
                if (TextUtils.isEmpty(old)) {
                    ToastUtils.showShort("请输入旧密码");
                } else if (TextUtils.isEmpty(pwd)) {
                    ToastUtils.showShort("请输入新密码");
                } else if (TextUtils.isEmpty(pwd2)) {
                    ToastUtils.showShort("请确认新密码");
                } else {
                    RxHttp.postJson(Comments.UPDATE_PWD)
                            .add("oldPassword", old)
                            .add("newPassword", pwd)
                            .add("confirmPassword", pwd2)
                            .asResponse(String.class)
                            .to(RxLife.toMain(UpdatePwdActivity.this))
                            .subscribe(model -> {
                                onJsonDataGetSuccess(model.getData(), 2000);
                            }, (OnError) error -> {
                                onJsonDataGetFailed(error.getErrorCode(), error.getErrorMsg(), 2000);
                            });
                }
            }
        });
    }



    @Override
    public void onJsonDataGetSuccess(Object re_data, int reqcode) {
        super.onJsonDataGetSuccess(re_data, reqcode);
        if (reqcode == 2000) {
            ToastUtils.showShort("修改成功");
            finish();
        }
    }


}