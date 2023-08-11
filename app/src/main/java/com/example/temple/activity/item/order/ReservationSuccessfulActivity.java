package com.example.temple.activity.item.order;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.blankj.utilcode.util.ToastUtils;
import com.example.temple.R;
import com.example.temple.activity.base.BaseTitleActivity;
import com.example.temple.network.Comments;
import com.example.temple.network.OnError;
import com.rxjava.rxlife.RxLife;

import butterknife.BindView;
import rxhttp.wrapper.param.RxHttp;

/**
 * 预约 订单完成成功
 */
public class ReservationSuccessfulActivity extends BaseTitleActivity implements View.OnClickListener {
    @BindView(R.id.iv_left)
    RelativeLayout iv_left;
    @BindView(R.id.tv_click)
    TextView tv_click;
    @BindView(R.id.later_on)
    TextView later_on;
    @BindView(R.id.ed_content)
    EditText ed_content;

    @BindView(R.id.ratingBar)
    RatingBar ratingBar;
    float overRating;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_reservation_success;
    }

    @Override
    protected void initView() {
        baseTitleGone();
    }


    @Override
    protected void initListener() {
        super.initListener();

        iv_left.setOnClickListener(this);
        tv_click.setOnClickListener(this);
        later_on.setOnClickListener(this);
        ratingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                overRating = rating;
                canNext();
            }
        });

        ed_content.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                canNext();

            }
        });

    }

    private void canNext() {
        if (overRating == 0 && TextUtils.isEmpty(ed_content.getText().toString())) {
            tv_click.setBackgroundResource(R.mipmap.submit_bt_unselect);

        } else {
            tv_click.setBackgroundResource(R.mipmap.submit_bt);
        }
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.iv_left) {
            finish();

        } else if (v.getId() == R.id.tv_click) {
            if (overRating == 0 && TextUtils.isEmpty(ed_content.getText().toString())) {
                ToastUtils.showShort("请对老师做出评价");
            } else {
                onSuccess();
            }

        } else if (v.getId() == R.id.later_on) {

            finish();


        }
    }

    //评论
    protected void onSuccess() {
        showWaitDialog("", false);
        RxHttp.postJson(Comments.REVIEW_TEACHER_LIST)
                .add("reviewContent", ed_content.getText().toString())
                .add("score", overRating)
                .add("teacherOrderId", getIntent().getStringExtra("id"))
                .asResponse(String.class)
                .to(RxLife.toMain(this))
                .subscribe(model -> {
                    hideWaitDialog();
                    if (model.getData() != null) {
                        Intent intent = new Intent();
                        intent.putExtra("result", "success");
                        setResult(RESULT_OK, intent);
                        finish();
                    }
                }, (OnError) error -> {
                    onJsonDataGetFailed(error.getErrorCode(), error.getErrorMsg(), 3000);
                });
    }


}