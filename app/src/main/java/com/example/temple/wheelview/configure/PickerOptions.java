package com.example.temple.wheelview.configure;

import android.content.Context;
import android.graphics.Typeface;
import android.view.Gravity;
import android.view.ViewGroup;

import com.bigkoo.pickerview.listener.CustomListener;
import com.bigkoo.pickerview.listener.OnOptionsSelectChangeListener;
import com.bigkoo.pickerview.listener.OnOptionsSelectListener;
import com.bigkoo.pickerview.listener.OnTimeSelectChangeListener;
import com.bigkoo.pickerview.listener.OnTimeSelectListener;
import com.example.temple.R;
import com.example.temple.wheelview.WheelView;

import java.util.Calendar;

/**
 * 配置类
 * Created by xiaosongzeem on 2018/3/8.
 */

public class PickerOptions {

    //常量
    private static final int PICKER_VIEW_BTN_COLOR_NORMAL_CONFRIM = 0xFF1890FF;
    private static final int PICKER_VIEW_BTN_COLOR_NORMAL_CANCLE = 0xFF666666;
    private static final int PICKER_VIEW_BG_COLOR_TITLE = 0xFFf5f5f5;
    private static final int PICKER_VIEW_COLOR_TITLE = 0xFF333333;
    private static final int PICKER_VIEW_BG_COLOR_DEFAULT = 0xFFFFFFFF;

    public static final int TYPE_PICKER_OPTIONS = 1;
    public static final int TYPE_PICKER_TIME = 2;
    public static final int TYPE_PICKER_WORKING_TIME = 3;

    public OnOptionsSelectListener optionsSelectListener;
    public OnTimeSelectListener timeSelectListener;

    public OnTimeSelectChangeListener timeSelectChangeListener;
    public OnOptionsSelectChangeListener optionsSelectChangeListener;

    public CustomListener customListener;


    //options picker
    public String label1, label2, label3;//单位字符
    public int option1, option2, option3;//默认选中项
    public int x_offset_one, x_offset_two, x_offset_three;//x轴偏移量

    public boolean cyclic1 = false;//是否循环，默认否
    public boolean cyclic2 = false;
    public boolean cyclic3 = false;


    //options picker
    public String label4, label5, label6;//单位字符
    public int option4, option5, option6;//默认选中项
    public int x_offset_four, x_offset_five, x_offset_six;//x轴偏移量

    public boolean cyclic4 = false;//是否循环，默认否
    public boolean cyclic5 = false;
    public boolean cyclic6 = false;


    public boolean isRestoreItem = false; //切换时，还原第一项


    //time picker
    public boolean[] type = new boolean[]{true, true, true, false, false, false};//显示类型，默认显示： 年月日

    public Calendar date;//当前选中时间
    public Calendar startDate;//开始时间
    public Calendar endDate;//终止时间
    public int startYear;//开始年份
    public int endYear;//结尾年份

    public boolean cyclic = false;//是否循环
    public boolean isLunarCalendar = false;//是否显示农历

    public String label_year, label_month, label_day, label_hours, label_minutes, label_seconds;//单位
    public int x_offset_year, x_offset_month, x_offset_day, x_offset_hours, x_offset_minutes, x_offset_seconds;//单位


    public PickerOptions(int buildType) {
        if (buildType == TYPE_PICKER_OPTIONS) {
            layoutRes = R.layout.pickerview_options;
        } else if (buildType == TYPE_PICKER_WORKING_TIME) {
            layoutRes = R.layout.pickerview_working_hours_options;
        } else {
            layoutRes = R.layout.pickerview_time;
        }
    }

    //******* 公有字段  ******//
    public int layoutRes;
    public ViewGroup decorView;
    public int textGravity = Gravity.CENTER;
    public Context context;

    public String textContentConfirm;//确定按钮文字
    public String textContentCancel;//取消按钮文字
    public String textContentTitle;//标题文字

    public int textColorConfirm = PICKER_VIEW_BTN_COLOR_NORMAL_CONFRIM;//确定按钮颜色
    public int textColorCancel = PICKER_VIEW_BTN_COLOR_NORMAL_CANCLE;//取消按钮颜色
    public int textColorTitle = PICKER_VIEW_COLOR_TITLE;//标题颜色

    public int bgColorWheel = PICKER_VIEW_BG_COLOR_DEFAULT;//滚轮背景颜色
    public int bgColorTitle = PICKER_VIEW_BG_COLOR_TITLE;//标题背景颜色

    public int textSizeSubmitCancel = 16;//确定取消按钮大小
    public int textSizeTitle = 16;//标题文字大小
    public int textSizeContent = 16;//内容文字大小

    public int itemsVisible = 5; //可见数量
    public int textColorOut = 0xFFa8a8a8; //分割线以外的文字颜色
    public int textColorCenter = 0xFF2a2a2a; //分割线之间的文字颜色
    public int dividerColor = 0xFFeeeeef; //分割线的颜色
    public int backgroundId = -1; //显示时的外部背景色颜色,默认是灰色

    public float lineSpacingMultiplier = 2.2f; // 条目间距倍数 默认1.6
    public boolean isDialog;//是否是对话框模式

    public boolean cancelable = true;//是否能取消
    public boolean isCenterLabel = false;//是否只显示中间的label,默认每个item都显示
    public Typeface font = Typeface.MONOSPACE;//字体样式
    public WheelView.DividerType dividerType = WheelView.DividerType.FILL;//分隔线类型


}
