
package com.example.temple.activity.item.order;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.blankj.utilcode.util.ToastUtils;
import com.example.temple.R;
import com.example.temple.activity.base.BaseTitleActivity;
import com.example.temple.utils.DateUtil;
import com.example.temple.utils.StringUtils;
import com.haibin.calendarview.Calendar;
import com.haibin.calendarview.CalendarView;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import butterknife.BindView;

/**
 * 预约信息填写
 */
public class ReservationInformationActivity extends BaseTitleActivity implements View.OnClickListener {
    @BindView(R.id.iv_left)
    RelativeLayout iv_left;
    @BindView(R.id.tv_click)
    TextView tv_click;
    @BindView(R.id.ed_name)
    EditText ed_name;
    @BindView(R.id.ed_phone)
    EditText ed_phone;

    @BindView(R.id.tv_last_month)
    TextView tv_last_month;
    @BindView(R.id.data_title)
    TextView data_title;
    @BindView(R.id.next_last_month)
    TextView next_last_month;
    @BindView(R.id.calendarView)
    CalendarView mCalendarView;

    @BindView(R.id.group1)
    RadioGroup group1;


    private List<String> selectDate = new ArrayList<String>();
    private String time;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_reservation_info;
    }

    @Override
    protected void initView() {
        baseTitleGone();
        selectDate.clear();
        selectDate.add(DateUtil.parseDateToStr(new Date(), "yyyy") + "-" +
                DateUtil.parseDateToStr(new Date(), "MM") + "-"
                + DateUtil.parseDateToStr(new Date(), "dd"));

        data_title.setText(Integer.parseInt(DateUtil.parseDateToStr(new Date(), "yyyy")) + " - " +
                (Integer.parseInt(DateUtil.parseDateToStr(new Date(), "MM")) < 10 ? "0" + Integer.parseInt(DateUtil.parseDateToStr(new Date(), "MM")) :
                        Integer.parseInt(DateUtil.parseDateToStr(new Date(), "MM"))));

//        // 初始化，设置日期范围
//        calendar_view.setDateRange(startDate.timeInMillis(), endDate.timeInMillis(), selectedDate.timeInMillis());
//        calendar_view.setOnSingleDateSelectedListener(new Function2<SingleCalendarView, DateInfo, Unit>() {
//            @Override
//            public Unit invoke(SingleCalendarView singleCalendarView, DateInfo dateInfo) {
//                selectDate.clear();
//                selectDate.add(dateInfo.getYear() + "-" + (dateInfo.getMonth() < 10 ? ("0" + dateInfo.getMonth()) : dateInfo.getMonth()) + "-"
//                        + (dateInfo.getDay() < 10 ? ("0" + dateInfo.getDay()) : dateInfo.getDay()));
//                return null;
//            }
//        });
        mCalendarView.setOnCalendarSelectListener(new CalendarView.OnCalendarSelectListener() {
            @Override
            public void onCalendarOutOfRange(Calendar calendar) {
            }

            @Override
            public void onCalendarSelect(Calendar calendar, boolean isClick) {
                selectDate.clear();
                selectDate.add(calendar.getYear() + "-" + (calendar.getMonth() < 10 ? ("0" + calendar.getMonth()) : calendar.getMonth()) + "-"
                        + (calendar.getDay() < 10 ? ("0" + calendar.getDay()) : calendar.getDay()));
                data_title.setText(calendar.getYear() + " - " + (calendar.getMonth() < 10 ? "0" + calendar.getMonth() : calendar.getMonth()));
                canNext();
            }
        });

        ed_name.addTextChangedListener(new TextWatcher() {
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
        ed_phone.addTextChangedListener(new TextWatcher() {
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
        if (!TextUtils.isEmpty(ed_name.getText().toString()) &&
                !TextUtils.isEmpty(ed_phone.getText().toString()) &&
                selectDate.size() != 0 &&
                !TextUtils.isEmpty(time)) {
            tv_click.setBackgroundResource(R.mipmap.submit_bt);
        } else {
            tv_click.setBackgroundResource(R.mipmap.submit_bt_unselect);

        }
    }


    @Override
    protected void initListener() {
        super.initListener();

        group1.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                switch (radioGroup.getCheckedRadioButtonId()) {
                    case R.id.radio1:
                        //点击左面的处理
                        time = "08:00~11:00";
                        break;
                    case R.id.radio2:
                        //点击右面的处理
                        time = "14:00~17:00";
                        break;
                }
                canNext();
            }
        });

        iv_left.setOnClickListener(this);
        tv_click.setOnClickListener(this);

        tv_last_month.setOnClickListener(this);
        next_last_month.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.iv_left) {
            finish();
        } else if (v.getId() == R.id.tv_last_month) {
            mCalendarView.scrollToPre();
        } else if (v.getId() == R.id.next_last_month) {
            mCalendarView.scrollToNext();
        } else if (v.getId() == R.id.tv_click) {

            if (TextUtils.isEmpty(ed_name.getText().toString())) {
                ToastUtils.showShort("请输入预约人真实姓名");
            } else if (TextUtils.isEmpty(ed_phone.getText().toString())) {
                ToastUtils.showShort("请输入预约人手机号码");
            } else if (selectDate.size() == 0) {
                ToastUtils.showShort("请选择日期");
            } else if (TextUtils.isEmpty(time)) {
                ToastUtils.showShort("请输入时间段");
            } else {
                if (!StringUtils.isPhone(ed_phone.getText().toString())) {
                    ToastUtils.showShort("请输入正确的手机号");
                    return;
                }
                startActivity(new Intent(mContext, AdvanceOrderSubmissionActivity.class)
                        .putExtra("price", getIntent().getDoubleExtra("price", 0))
                        .putExtra("address", getIntent().getStringExtra("address"))
                        .putExtra("name", ed_name.getText().toString())
                        .putExtra("teachername", getIntent().getStringExtra("teachername"))
                        .putExtra("phone", ed_phone.getText().toString())
                        .putExtra("id", getIntent().getStringExtra("id"))
                        .putExtra("date", selectDate.get(0))
                        .putExtra("time", time));
            }


        }
    }


}