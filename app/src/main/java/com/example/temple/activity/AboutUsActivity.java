package com.example.temple.activity;

import android.os.Bundle;
import android.widget.TextView;

import com.blankj.utilcode.util.AppUtils;
import com.example.temple.R;
import com.example.temple.activity.base.BaseTitleActivity;
import com.example.temple.bean.AboutUsBean;
import com.example.temple.network.Comments;
import com.example.temple.network.OnError;
import com.rxjava.rxlife.RxLife;

import butterknife.BindView;
import rxhttp.wrapper.param.RxHttp;

public class AboutUsActivity extends BaseTitleActivity {
    @BindView(R.id.tv_version)
    TextView tvVersion;
    @BindView(R.id.tv_kefu_phone)
    TextView tvKefuPhone;

    @BindView(R.id.tv_work_time)
    TextView tvWorkTime;
    @BindView(R.id.tv_company)
    TextView tvCompanyName;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_about_us;
    }

    @Override
    protected void initView() {
        baseTitle.setText("关于我们");
        statusBar.setBackgroundColor(getResources().getColor(R.color.background));
        rlTop.setBackgroundColor(getResources().getColor(R.color.background));
        tvVersion.setText(AppUtils.getAppVersionName());
        getInfo();
    }

    @Override
    protected void initListener() {
        super.initListener();

    }

    public void getInfo() {
        RxHttp.get(Comments.GET_INFO)
                .asResponse(AboutUsBean.class)
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
            AboutUsBean bean = (AboutUsBean) re_data;
            tvKefuPhone.setText(bean.getCompany_phone());
            tvWorkTime.setText(bean.getWork_day());
            tvCompanyName.setText("最终解释权归" + bean.getCompany_name() + "所有");
        }
    }


}