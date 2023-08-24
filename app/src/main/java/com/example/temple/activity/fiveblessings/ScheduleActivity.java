
package com.example.temple.activity.fiveblessings;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.blankj.utilcode.util.SizeUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.example.temple.R;
import com.example.temple.activity.base.BaseTitleActivity;
import com.example.temple.activity.item.order.OrderTeacherDetailsActivity;
import com.example.temple.adapter.ScheduleAdapter;
import com.example.temple.utils.DateUtil;
import com.example.temple.utils.SpaceDecoration;
import com.haibin.calendarview.Calendar;
import com.haibin.calendarview.CalendarView;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;

/**
 * 姓氏 日程表
 */
public class ScheduleActivity extends BaseTitleActivity implements View.OnClickListener {
    @BindView(R.id.iv_left)
    RelativeLayout mIvLeft;
    @BindView(R.id.tv_last_month)
    TextView mTvLastMonth;
    @BindView(R.id.tv_title)
    TextView mTvTitle;
    @BindView(R.id.next_last_month)
    TextView mNextLastMonth;
    @BindView(R.id.calendarView)
    CalendarView mCalendarView;
    @BindView(R.id.rView)
    RecyclerView mRView;

    @BindView(R.id.tv_add_schedule)
    TextView mTvAddSchedule;

    private ScheduleAdapter mAdapter;
    private int from;//0 姓氏  1 宗亲
    String desc = "";//班休标识
    String restfulDay = "1";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_schedule;
    }


    /**
     * 班休状态
     */
    public enum RestfulDayType {
        RESTFUL("1", "休"),
        WORK("0", "班"),
        ;
        private String type;
        private String desc;

        private RestfulDayType(String type, String desc) {
            this.type = type;
            this.desc = desc;
        }

        public String getType() {
            return type;
        }

        public String getDesc() {
            return desc;
        }
    }

    @Override
    protected void initView() {
        baseTitleGone();
        from = getIntent().getIntExtra("from", 0);
        mTvTitle.setText(Integer.parseInt(DateUtil.parseDateToStr(new Date(), "yyyy")) + " - " +
                (Integer.parseInt(DateUtil.parseDateToStr(new Date(), "MM")) < 10 ? "0" + Integer.parseInt(DateUtil.parseDateToStr(new Date(), "MM")) :
                        Integer.parseInt(DateUtil.parseDateToStr(new Date(), "MM"))));


        if (RestfulDayType.RESTFUL.getType().equals(restfulDay)) {
            desc = RestfulDayType.RESTFUL.getDesc();
        } else if (RestfulDayType.WORK.getType().equals(restfulDay)) {
            desc = RestfulDayType.WORK.getDesc();
        }

        Calendar schemeCalendar = new Calendar();
        schemeCalendar.setYear(2023);
        schemeCalendar.setMonth(7);
        schemeCalendar.setDay(1);
//        schemeCalendar.addScheme(11, 0xFFFFAD28, desc);//班休标识
        schemeCalendar.addScheme(10, Color.parseColor("#8BC343"), "");//圆点标识
        Map<String, Calendar> holidayMap = new HashMap<>();
        holidayMap.put(schemeCalendar.toString(), schemeCalendar);

        Calendar schemeCalendar2 = new Calendar();
        schemeCalendar2.setYear(2023);
        schemeCalendar2.setMonth(7);
        schemeCalendar2.setDay(2);
//        schemeCalendar.addScheme(11, 0xFFFFAD28, desc);//班休标识
        schemeCalendar2.addScheme(10, Color.parseColor("#8BC343"), "");//圆点标识
        holidayMap.put(schemeCalendar2.toString(), schemeCalendar2);


        Calendar schemeCalendar3 = new Calendar();
        schemeCalendar3.setYear(2023);
        schemeCalendar3.setMonth(8);
        schemeCalendar3.setDay(15);
//        schemeCalendar.addScheme(11, 0xFFFFAD28, desc);//班休标识
        schemeCalendar3.addScheme(10, Color.parseColor("#8BC343"), "");//圆点标识
        holidayMap.put(schemeCalendar3.toString(), schemeCalendar3);

        mCalendarView.setSchemeDate(holidayMap);

        //日历显示隐藏
//        if (!mCalendarView.isShown()) {
//            if (mOldExpand) {
//                mCalendarLayout.expand(0);
//            } else {
//                mCalendarLayout.shrink(0);
//            }
//            mCalendarLayout.showCalendarView();
//        }

        mRView.setLayoutManager(new LinearLayoutManager(this));
        mRView.setHasFixedSize(true);
        mAdapter = new ScheduleAdapter(R.layout.item_schedule_list);
        mRView.setAdapter(mAdapter);
        mRView.addItemDecoration(new SpaceDecoration(0, 0, 0, SizeUtils.dp2px(10)));

        View view = LayoutInflater.from(this).inflate(R.layout.empty_view, null);
        TextView tv_load_empty = view.findViewById(R.id.tv_load_empty);
        tv_load_empty.setText("暂无数据");
        ImageView iv_load_empty = view.findViewById(R.id.iv_load_empty);
        iv_load_empty.setImageResource(R.mipmap.haode_botton);
        mAdapter.setEmptyView(view);

        //假数据
        ArrayList<String> data = new ArrayList<String>();
        data.add("ko1ko");
        data.add("ko2ko");
        data.add("ko3ko");
        data.add("ko4ko");
        data.add("ko5ko");
        mAdapter.setList(data);

        mAdapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(@NonNull BaseQuickAdapter<?, ?> adapter, @NonNull View view, int position) {
                startActivity(new Intent(mContext, OrderTeacherDetailsActivity.class));
            }
        });

        mCalendarView.setOnCalendarSelectListener(new CalendarView.OnCalendarSelectListener() {
            @Override
            public void onCalendarOutOfRange(Calendar calendar) {
            }

            @Override
            public void onCalendarSelect(Calendar calendar, boolean isClick) {
                mTvTitle.setText(calendar.getYear() + " - " + (calendar.getMonth() < 10 ? "0" + calendar.getMonth() : calendar.getMonth()));
            }
        });

    }


    @Override
    protected void initListener() {
        super.initListener();
        mIvLeft.setOnClickListener(this);
        mTvLastMonth.setOnClickListener(this);
        mNextLastMonth.setOnClickListener(this);
        mTvAddSchedule.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.iv_left) {
            finish();
        } else if (v.getId() == R.id.tv_last_month) {
            mCalendarView.scrollToPre();
        } else if (v.getId() == R.id.next_last_month) {
            mCalendarView.scrollToNext();
        } else if (v.getId() == R.id.tv_add_schedule) {
            startActivity(new Intent(ScheduleActivity.this, CreateScheduleActivity.class));
        }

    }


}