package com.example.temple.activity.user;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.blankj.utilcode.util.ToastUtils;
import com.example.temple.R;
import com.example.temple.activity.base.BaseTitleActivity;
import com.example.temple.bean.UserInfoBean;
import com.example.temple.network.Comments;
import com.example.temple.network.OnError;
import com.example.temple.utils.BigDecimalUtils;
import com.example.temple.utils.DataCacheUtil;
import com.rxjava.rxlife.RxLife;

import java.math.BigDecimal;

import butterknife.BindView;
import butterknife.ButterKnife;
import rxhttp.wrapper.param.RxHttp;

public class TransOutActivity extends BaseTitleActivity implements View.OnClickListener {


    @BindView(R.id.tv_submit)
    TextView tvSubmit;

    @BindView(R.id.tv_sxf)
    TextView tvSxf;

    @BindView(R.id.et_count)
    EditText etCount;
    @BindView(R.id.et_bank_account)
    EditText etBankAccount;
    @BindView(R.id.et_bank_name)
    EditText etBankName;
    @BindView(R.id.et_people)
    EditText etpeople;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_transout;
    }

    @Override
    protected void initView() {
        baseTitle.setText("转出");
        UserInfoBean user = DataCacheUtil.getInstance(this).getUserInfo();
        statusBar.setBackgroundColor(getResources().getColor(R.color.background));
        rlTop.setBackgroundColor(getResources().getColor(R.color.background));
        tvSxf.setText("收取手续费"+ BigDecimalUtils.mul(user.getWithdrawService(),"100",2)+"%");
    }

    private String mSex;

    @Override
    protected void initListener() {
        super.initListener();
        tvSubmit.setOnClickListener(this);
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.tv_submit) {
            if(etCount.getText().toString().isEmpty()) {
                ToastUtils.showShort("请填写转出数量");
                return;
            }
            if(etBankName.getText().toString().isEmpty()) {
                ToastUtils.showShort("请填写开户行");
                return;
            }
            if(etBankAccount.getText().toString().isEmpty()) {
                ToastUtils.showShort("请填写账号");
                return;
            }
            if(etpeople.getText().toString().isEmpty()) {
                ToastUtils.showShort("请填写开户人");
                return;
            }
            RxHttp.postJson(Comments.COMMIT_WITHDRAW)
                    .add("amount", etCount.getText().toString())
                    .add("bankName", etBankName.getText().toString())
                    .add("bankNo", etBankAccount.getText().toString())
                    .add("personName", etpeople.getText().toString())
                    .asResponse(String.class)
                    .to(RxLife.toMain(this))
                    .subscribe(model -> {
                        onJsonDataGetSuccess(model.getData(), 1000);
                    }, (OnError) error -> {
                        onJsonDataGetFailed(error.getErrorCode(), error.getErrorMsg(), 1000);
                    });
        }
    }

    private String stripTrailingZeros(String s) {
        return new BigDecimal(s).stripTrailingZeros().toPlainString();
    }

    @Override
    public void onJsonDataGetSuccess(Object re_data, int reqcode) {
        super.onJsonDataGetSuccess(re_data, reqcode);
        if (reqcode == 1000) {
            ToastUtils.showShort("提交转出成功");
            finish();
        } else if (reqcode == 2000) {

        } else if (reqcode == 3000) {
        }
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {
        super.onPointerCaptureChanged(hasCapture);
    }
}