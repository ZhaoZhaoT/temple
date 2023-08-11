
package com.example.temple.activity.item.order;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.blankj.utilcode.util.TimeUtils;
import com.example.temple.R;
import com.example.temple.activity.base.BaseTitleActivity;
import com.example.temple.bean.teacher.MyTeacherListBean;
import com.example.temple.network.Comments;
import com.example.temple.network.OnError;
import com.example.temple.utils.EventBusUtils;
import com.rxjava.rxlife.RxLife;

import butterknife.BindView;
import rxhttp.wrapper.param.RxHttp;

/**
 * 已预约详情页
 * 已取消
 */
public class ReservedActivity extends BaseTitleActivity implements View.OnClickListener {
    @BindView(R.id.iv_left)
    RelativeLayout iv_left;

    @BindView(R.id.tv_state)
    TextView tv_state;
    @BindView(R.id.layout_evaluate)
    LinearLayout layout_evaluate;

    @BindView(R.id.tv_update)
    TextView tv_update;
    @BindView(R.id.tv_cancle)
    TextView tv_cancle;
    @BindView(R.id.tv_finish)
    TextView tv_finish;
    //    @BindView(R.id.tv_delect)
//    TextView tv_delect;
    @BindView(R.id.tv_go_comment)
    TextView tv_go_comment;


    @BindView(R.id.tv_teacher_name)
    TextView tv_teacher_name;
    @BindView(R.id.tv_address)
    TextView tv_address;
    @BindView(R.id.tv_teacher_phone)
    TextView tv_teacher_phone;

    @BindView(R.id.tv_name)
    TextView tv_name;
    @BindView(R.id.tv_phone)
    TextView tv_phone;
    @BindView(R.id.tv_time)
    TextView tv_time;
    @BindView(R.id.tv_price)
    TextView tv_price;

    @BindView(R.id.ratingBar)
    RatingBar ratingBar;
    @BindView(R.id.tv_score)
    TextView tv_score;
    @BindView(R.id.tv_content)
    TextView tv_content;

    String id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_reserved;
    }

    @Override
    protected void initView() {
        baseTitleGone();
    }


    @Override
    protected void initListener() {
        super.initListener();
        iv_left.setOnClickListener(this);
        id = getIntent().getStringExtra("id");
        onDetails();

        tv_update.setOnClickListener(this);
        tv_cancle.setOnClickListener(this);
        tv_finish.setOnClickListener(this);
//        tv_delect.setOnClickListener(this);
        tv_go_comment.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.iv_left) {
            finish();
        } else if (v.getId() == R.id.tv_update) {
            //修改界面
            startActivity(new Intent(ReservedActivity.this, ReservationInformationUpdateActivity.class));

        } else if (v.getId() == R.id.tv_cancle) {
            //去取消界面
            startActivity(new Intent(ReservedActivity.this, CancelReservationActivity.class).putExtra("id", id));


        } else if (v.getId() == R.id.tv_finish) {

            onSuccess();
        } else if (v.getId() == R.id.tv_go_comment) {
            //去评分
            Intent intent = new Intent(ReservedActivity.this, ReservationSuccessfulActivity.class);
            intent.putExtra("id", id);
            startActivityForResult(intent, 1);

        }
    }


    //完成
    protected void onSuccess() {
        showWaitDialog("", false);
        RxHttp.postJson(Comments.OVER_TEACHER_LIST)
                .add("remark", "")
                .add("teacherOrderId", getIntent().getStringExtra("id"))
                .asResponse(String.class)
                .to(RxLife.toMain(this))
                .subscribe(model -> {
                    hideWaitDialog();
                    if (model.getData() != null) {
                        tv_state.setText("已完成");
                        tv_state.setTextColor(Color.parseColor("#333333"));
                        tv_state.setBackgroundColor(Color.parseColor("#ffffff"));

                        tv_update.setVisibility(View.GONE);
                        tv_cancle.setVisibility(View.GONE);
                        tv_finish.setVisibility(View.GONE);
//                        tv_delect.setVisibility(View.GONE);
                        tv_go_comment.setVisibility(View.VISIBLE);
                        //查详情  ，有无评分
                        //有无评分
                        //假设无评分
                        layout_evaluate.setVisibility(View.GONE);
                        EventBusUtils.post(Comments.TEACHER_OVER_SUCCESS);
                    }
                }, (OnError) error -> {
                    onJsonDataGetFailed(error.getErrorCode(), error.getErrorMsg(), 3000);
                });
    }

    //查详情
    protected void onDetails() {
        showWaitDialog("", false);
        RxHttp.get(Comments.DETAILS_TEACHER_LIST)
                .add("teacherOrderId", getIntent().getStringExtra("id"))
                .asResponse(MyTeacherListBean.ContentBean.class)
                .to(RxLife.toMain(this))
                .subscribe(model -> {
                    hideWaitDialog();
                    if (model.getData() != null) {
                        tv_teacher_name.setText("预约" + model.getData().getTeacherVO().getTeacherName());
                        tv_address.setText(model.getData().getTeacherVO().getTeacherAddress());
                        tv_teacher_phone.setText(model.getData().getTeacherVO().getTeacherPhone());

                        tv_name.setText(model.getData().getName());
                        tv_phone.setText(model.getData().getPhone());
                        tv_time.setText(TimeUtils.millis2String(model.getData().getStartAt(), "yyyy-MM-dd") + " " +
                                TimeUtils.millis2String(model.getData().getStartAt(), "HH:mm") + "~" +
                                TimeUtils.millis2String(model.getData().getEndAt(), "HH:mm"));

                        tv_price.setText("¥" + model.getData().getPrice());


                        if (model.getData().getStatus().equals("FIVE")) {//已取消
                            tv_state.setText("已取消");
                            tv_state.setTextColor(Color.parseColor("#999999"));
                            tv_state.setBackgroundColor(Color.parseColor("#ffffff"));
                            tv_update.setVisibility(View.GONE);
                            tv_cancle.setVisibility(View.GONE);
                            tv_finish.setVisibility(View.GONE);
//                            tv_delect.setVisibility(View.VISIBLE);
                            tv_go_comment.setVisibility(View.GONE);
                            layout_evaluate.setVisibility(View.GONE);

                        } else if (model.getData().getStatus().equals("FOUR")) {//已完成
                            tv_state.setText("已完成");
                            tv_state.setTextColor(Color.parseColor("#333333"));
                            tv_state.setBackgroundColor(Color.parseColor("#ffffff"));

                            tv_update.setVisibility(View.GONE);
                            tv_cancle.setVisibility(View.GONE);
                            tv_finish.setVisibility(View.GONE);
//                            tv_delect.setVisibility(View.GONE);
                            tv_go_comment.setVisibility(View.VISIBLE);
                            if (TextUtils.isEmpty(model.getData().getScore()) && TextUtils.isEmpty(model.getData().getRemark())) {
                                layout_evaluate.setVisibility(View.GONE);
                                tv_go_comment.setVisibility(View.VISIBLE);
                            } else {
                                //评论成功
                                layout_evaluate.setVisibility(View.VISIBLE);
                                tv_update.setVisibility(View.GONE);
                                tv_cancle.setVisibility(View.GONE);
                                tv_finish.setVisibility(View.GONE);
//                                tv_delect.setVisibility(View.VISIBLE);//暂移除删除
                                tv_go_comment.setVisibility(View.GONE);
                                ratingBar.setRating(Float.parseFloat(model.getData().getScore()));
                                tv_score.setText(model.getData().getScore() + "分");
                                tv_content.setText(model.getData().getReviewContent());
                            }

                        } else if (model.getData().getStatus().equals("TWO")) {//已预约
                            tv_state.setText("已预约");
                            tv_state.setTextColor(Color.parseColor("#ff6d1b13"));
                            tv_state.setBackgroundColor(Color.parseColor("#F5E0CB"));

                            tv_update.setVisibility(View.GONE);//修改功能暂时不做
                            tv_cancle.setVisibility(View.VISIBLE);
                            tv_finish.setVisibility(View.VISIBLE);
//                            tv_delect.setVisibility(View.GONE);
                            tv_go_comment.setVisibility(View.GONE);
                            layout_evaluate.setVisibility(View.GONE);
                        }


                    }
                }, (OnError) error -> {
                    onJsonDataGetFailed(error.getErrorCode(), error.getErrorMsg(), 3000);
                });
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1 && resultCode == RESULT_OK) {
            onDetails();


        }
    }
}