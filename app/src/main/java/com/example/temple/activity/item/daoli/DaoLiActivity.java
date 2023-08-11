package com.example.temple.activity.item.daoli;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.blankj.utilcode.util.SizeUtils;
import com.example.temple.R;
import com.example.temple.activity.base.BaseTitleActivity;
import com.example.temple.adapter.DaoLiListerAdapter;
import com.example.temple.bean.daoli.DaoLiBean;
import com.example.temple.network.Comments;
import com.example.temple.network.OnError;
import com.example.temple.utils.DateUtil;
import com.example.temple.utils.SpaceDecoration;
import com.haibin.calendarview.Calendar;
import com.haibin.calendarview.CalendarView;
import com.rxjava.rxlife.RxLife;

import java.util.ArrayList;
import java.util.Date;

import butterknife.BindView;
import rxhttp.wrapper.param.RxHttp;

public class DaoLiActivity extends BaseTitleActivity implements View.OnClickListener {

    @BindView(R.id.iv_left)
    RelativeLayout iv_left;
    @BindView(R.id.tv_last_month)
    TextView tv_last_month;
    @BindView(R.id.tv_title)
    TextView tv_title;
    @BindView(R.id.next_last_month)
    TextView next_last_month;

    @BindView(R.id.calendarView)
    CalendarView mCalendarView;

    @BindView(R.id.tv_data)
    TextView tv_data;

    @BindView(R.id.tv_nongli)
    TextView tv_nongli;
    @BindView(R.id.rView)
    RecyclerView mRView;
    TextView tvShengxiao;
    private DaoLiListerAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_daoli;
    }

    @SuppressLint("MissingInflatedId")
    @Override
    protected void initView() {
        baseTitleGone();
        tv_title.setText(Integer.parseInt(DateUtil.parseDateToStr(new Date(), "yyyy")) + " - " +
                (Integer.parseInt(DateUtil.parseDateToStr(new Date(), "MM")) < 10 ? "0" + Integer.parseInt(DateUtil.parseDateToStr(new Date(), "MM")) :
                        Integer.parseInt(DateUtil.parseDateToStr(new Date(), "MM"))));

        mRView.setLayoutManager(new LinearLayoutManager(this));
        mRView.setHasFixedSize(true);
        mAdapter = new DaoLiListerAdapter(R.layout.item_daoli_list);
        mRView.setAdapter(mAdapter);
        mRView.addItemDecoration(new SpaceDecoration(0, 0, 0, SizeUtils.dp2px(10)));

        mAdapter.setEmptyView(R.layout.empty_view);

        View viewTop = LayoutInflater.from(this).inflate(R.layout.item_daoli_top, null);
        tvShengxiao = viewTop.findViewById(R.id.tv_shengxiao);

        mAdapter.setHeaderView(viewTop);

        mCalendarView.setOnCalendarSelectListener(new CalendarView.OnCalendarSelectListener() {
            @Override
            public void onCalendarOutOfRange(Calendar calendar) {
            }

            @Override
            public void onCalendarSelect(Calendar calendar, boolean isClick) {
                tv_title.setText(calendar.getYear() + " - " + (calendar.getMonth() < 10 ? "0" + calendar.getMonth() : calendar.getMonth()));
                getHuangLiList(calendar.getYear() + "-" + (calendar.getMonth() < 10 ? "0" + calendar.getMonth() : calendar.getMonth()) +
                        "-" + (calendar.getDay() < 10 ? "0" + calendar.getDay() : calendar.getDay()));
            }
        });


        getHuangLiList(DateUtil.parseDateToStr(new Date(), "yyyy-MM-dd"));
    }

    @Override
    protected void initListener() {
        super.initListener();
        iv_left.setOnClickListener(this);
        tv_last_month.setOnClickListener(this);
        next_last_month.setOnClickListener(this);
    }


    public void getHuangLiList(String date) {
        showWaitDialog("", false);
        RxHttp.get(Comments.DAOLI_INFO)
                .add("queryDate", date)
                .asResponse(DaoLiBean.class)
                .to(RxLife.toMain(this))
                .subscribe(model -> {
                    hideWaitDialog();
                    if (model.getData() != null) {
                        DaoLiBean bean = model.getData();
                        tv_data.setText(bean.getResult().getYear() + "/" + bean.getResult().getMonth() + "/" + bean.getResult().getDay() +
                                " 星期" + bean.getResult().getWeek());
                        tv_nongli.setText(bean.getResult().getLunarmonth() + bean.getResult().getLunarday());
                        tvShengxiao.setText(bean.getResult().getShengxiao());

                        ArrayList<String> data = new ArrayList<String>();
                        String yi = "";
                        for (int i = 0; i < model.getData().getResult().getHuangli().getYi().length; i++) {
                            yi = yi + model.getData().getResult().getHuangli().getYi()[i] + " ";
                        }
                        data.add(yi);//今日所宜
                        String ji = "";
                        for (int i = 0; i < model.getData().getResult().getHuangli().getJi().length; i++) {
                            ji = ji + model.getData().getResult().getHuangli().getJi()[i] + " ";
                        }
                        data.add(ji);//今日所忌

                        String suici = "";
                        for (int i = 0; i < model.getData().getResult().getHuangli().getSuici().length; i++) {
                            suici = suici + model.getData().getResult().getHuangli().getSuici()[i];
                        }
                        data.add(suici + model.getData().getResult().getGanzhishi());//今日八字
                        data.add(model.getData().getResult().getHuangli().getChong() + model.getData().getResult().getHuangli().getSha());//今日冲煞
                        data.add("喜神:" + model.getData().getResult().getHuangli().getXishen() + ";"
                                + "财神:" + model.getData().getResult().getHuangli().getCaishen() + ";" +
                                "福神:" + model.getData().getResult().getHuangli().getFushen() + ";" +
                                "天神:" + model.getData().getResult().getHuangli().getTaishen() + ";");//吉祥方位
                        data.add(model.getData().getResult().getHuangli().getJishenyiqu());//吉神宜趋
                        data.add(model.getData().getResult().getHuangli().getWuxing());//今日五行
                        mAdapter.setList(data);
                    }
                }, (OnError) error -> {
                    hideWaitDialog();
                    onJsonDataGetFailed(error.getErrorCode(), error.getErrorMsg(), 2000);
                });
    }


    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.iv_left) {
            finish();
        } else if (v.getId() == R.id.tv_last_month) {
            mCalendarView.scrollToPre();
        } else if (v.getId() == R.id.next_last_month) {
            mCalendarView.scrollToNext();
        }
    }


}