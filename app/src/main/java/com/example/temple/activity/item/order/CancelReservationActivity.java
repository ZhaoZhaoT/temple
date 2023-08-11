
package com.example.temple.activity.item.order;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.blankj.utilcode.util.ToastUtils;
import com.example.temple.R;
import com.example.temple.activity.base.BaseTitleActivity;
import com.example.temple.network.Comments;
import com.example.temple.network.OnError;
import com.example.temple.utils.AppManager;
import com.example.temple.utils.EventBusUtils;
import com.rxjava.rxlife.RxLife;

import butterknife.BindView;
import rxhttp.wrapper.param.RxHttp;

/**
 * 取消预约
 */
public class CancelReservationActivity extends BaseTitleActivity implements View.OnClickListener {
    @BindView(R.id.iv_left)
    RelativeLayout iv_left;

    @BindView(R.id.group1)
    RadioGroup group1;

    @BindView(R.id.tv_click)
    TextView tv_click;
    String remark;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_cancel_reservation;
    }

    @Override
    protected void initView() {
        baseTitleGone();
    }


    @Override
    protected void initListener() {
        super.initListener();

        group1.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                switch (radioGroup.getCheckedRadioButtonId()) {

                    case R.id.radio1:
                    case R.id.radio2:
                    case R.id.radio3:
                        //点击右面的处理
                        //点击左面的处理
                        RadioButton radioButton = (RadioButton) findViewById(radioGroup.getCheckedRadioButtonId());
                        remark = radioButton.getText().toString();
                        tv_click.setBackgroundResource(R.mipmap.submit_bt);
                        break;
                }
            }
        });


        iv_left.setOnClickListener(this);
        tv_click.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.iv_left) {
            finish();
        } else if (v.getId() == R.id.tv_click) {
            //去取消界面
            if (TextUtils.isEmpty(remark)) {
                ToastUtils.showShort("请选择取消原因");
            } else {
                onCancle();
            }

        }
    }

    //取消
    protected void onCancle() {
        showWaitDialog("", false);
        RxHttp.postJson(Comments.CANCLE_TEACHER_LIST)
                .add("remark", remark)
                .add("teacherOrderId", getIntent().getStringExtra("id"))
                .asResponse(String.class)
                .to(RxLife.toMain(this))
                .subscribe(model -> {
                    hideWaitDialog();
                    if (model.getData() != null) {
                        EventBusUtils.post(Comments.TEACHER_CANCLE_SUCCESS);
                        ToastUtils.showShort("已取消");
                        finish();
                        AppManager.getAppManager().finishActivity(ReservedActivity.class);
                    }
                }, (OnError) error -> {
                    onJsonDataGetFailed(error.getErrorCode(), error.getErrorMsg(), 3000);
                });
    }


}