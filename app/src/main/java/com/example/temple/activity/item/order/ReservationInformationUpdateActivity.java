
package com.example.temple.activity.item.order;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.blankj.utilcode.util.ToastUtils;
import com.example.temple.R;
import com.example.temple.activity.base.BaseTitleActivity;
import com.example.temple.utils.AppManager;
import com.example.temple.utils.DateUtil;
import com.haibin.calendarview.Calendar;
import com.haibin.calendarview.CalendarView;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import butterknife.BindView;

/**
 * 修改预约信息填写
 */
public class ReservationInformationUpdateActivity extends BaseTitleActivity implements View.OnClickListener {
    @BindView(R.id.iv_left)
    RelativeLayout iv_left;
    @BindView(R.id.tv_title)
    TextView tv_title;
    @BindView(R.id.tv_click)
    TextView tv_click;
    @BindView(R.id.ed_name)
    EditText ed_name;
    @BindView(R.id.ed_phone)
    EditText ed_phone;

    @BindView(R.id.radio1)
    RadioButton radio1;
    @BindView(R.id.radio2)
    RadioButton radio2;
    @BindView(R.id.group1)
    RadioGroup group1;

    @BindView(R.id.tv_last_month)
    TextView tv_last_month;
    @BindView(R.id.data_title)
    TextView data_title;
    @BindView(R.id.next_last_month)
    TextView next_last_month;
    @BindView(R.id.calendarView)
    CalendarView mCalendarView;

    private List<String> selectDate = new ArrayList<String>();
    private Double price;
    private String address;
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
    }


    @Override
    protected void initListener() {
        super.initListener();
        tv_title.setText("修改信息");
        price = Double.valueOf(getIntent().getIntExtra("price", 0));
        address = getIntent().getStringExtra("address");

        radio2.setChecked(true);
        time = "14:00~17:00";
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
            }
        });

        // 选中 设置当前日期
        selectDate.clear();
        selectDate.add(DateUtil.parseDateToStr(new Date(), "yyyy") + "-" +
                DateUtil.parseDateToStr(new Date(), "MM") + "-"
                + DateUtil.parseDateToStr(new Date(), "dd"));

        data_title.setText(Integer.parseInt(DateUtil.parseDateToStr(new Date(), "yyyy")) + " - " +
                (Integer.parseInt(DateUtil.parseDateToStr(new Date(), "MM")) < 10 ? "0" + Integer.parseInt(DateUtil.parseDateToStr(new Date(), "MM")) :
                        Integer.parseInt(DateUtil.parseDateToStr(new Date(), "MM"))));

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
            }
        });


        tv_click.setBackgroundResource(R.mipmap.submit_bt);
        iv_left.setOnClickListener(this);
        tv_click.setOnClickListener(this);
        tv_last_month.setOnClickListener(this);
        next_last_month.setOnClickListener(this);

        ed_name.setText("王明");
        ed_phone.setText("1317896559");

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
                finish();
                AppManager.getAppManager().finishActivity(ReservedActivity.class);

            }

        }
    }


}