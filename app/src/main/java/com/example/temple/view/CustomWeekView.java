package com.example.temple.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.text.TextUtils;

import com.haibin.calendarview.Calendar;
import com.haibin.calendarview.WeekView;

import java.util.List;

/**
 * 演示一个变态需求的周视图
 * Created by huanghaibin on 2018/2/9.
 */

public class CustomWeekView extends WeekView {

    private int mRadius;

    /**
     * 自定义魅族标记的文本画笔
     */
    private Paint mTextPaint = new Paint();


    /**
     * 24节气画笔
     */
    private Paint mSolarTermTextPaint = new Paint();

    /**
     * 背景圆点
     */
    private Paint mPointPaint = new Paint();

    /**
     * 今天的背景色
     */
    private Paint mCurrentDayPaint = new Paint();


    /**
     * 圆点半径
     */
    private float mPointRadius;
    private int mPadding;
    private float mCircleRadius;
//    private float mCornerRadius;

    /**
     * 自定义魅族标记的圆形背景
     */
//    private Paint mSchemeBasicPaint = new Paint();
//    private float mSchemeBaseLine;

    public CustomWeekView(Context context) {
        super(context);
        mTextPaint.setTextSize(dipToPx(context, 9));
        mTextPaint.setColor(0xffFFAD28);
        mTextPaint.setAntiAlias(true);
//        mTextPaint.setFakeBoldText(true);

        mSolarTermTextPaint.setColor(0xffFFAD28);
        mSolarTermTextPaint.setAntiAlias(true);
        mSolarTermTextPaint.setTextAlign(Paint.Align.CENTER);

//        mSchemeBasicPaint.setAntiAlias(true);
//        mSchemeBasicPaint.setStyle(Paint.Style.FILL);
//        mSchemeBasicPaint.setTextAlign(Paint.Align.CENTER);
//        mSchemeBasicPaint.setFakeBoldText(true);
//        mSchemeBasicPaint.setColor(Color.WHITE);

        mPointPaint.setAntiAlias(true);
        mPointPaint.setStyle(Paint.Style.FILL);
        mPointPaint.setTextAlign(Paint.Align.CENTER);
        mPointPaint.setColor(0xFFFFAD28);

        mCurrentDayPaint.setAntiAlias(true);
        mCurrentDayPaint.setStyle(Paint.Style.FILL);
        mCurrentDayPaint.setColor(0x2424B684);

        mCircleRadius = dipToPx(getContext(), 7);
        mPadding = dipToPx(getContext(), 3);
        mPointRadius = dipToPx(context, 2);
//        mCornerRadius = dipToPx(context, 2);

//        Paint.FontMetrics metrics = mSchemeBasicPaint.getFontMetrics();
//        mSchemeBaseLine = mCircleRadius - metrics.descent + (metrics.bottom - metrics.top) / 2 + dipToPx(getContext(), 1);

//        //兼容硬件加速无效的代码
//        setLayerType(View.LAYER_TYPE_SOFTWARE, mSelectedPaint);
//        //4.0以上硬件加速会导致无效
//        mSelectedPaint.setMaskFilter(new BlurMaskFilter(28, BlurMaskFilter.Blur.SOLID));

//        setLayerType(View.LAYER_TYPE_SOFTWARE, mSchemeBasicPaint);
//        mSchemeBasicPaint.setMaskFilter(new BlurMaskFilter(28, BlurMaskFilter.Blur.SOLID));
    }


    @Override
    protected void onPreviewHook() {
        mSolarTermTextPaint.setTextSize(mCurMonthLunarTextPaint.getTextSize());
        mRadius = Math.min(mItemWidth, mItemHeight) / 11 * 5;
    }


    /**
     * @param canvas    canvas
     * @param calendar  日历日历calendar
     * @param x         日历Card x起点坐标
     * @param hasScheme hasScheme 非标记的日期
     * @return true 则绘制onDrawScheme，因为这里背景色不是是互斥的
     */
    @Override
    protected boolean onDrawSelected(Canvas canvas, Calendar calendar, int x, boolean hasScheme) {
        int cx = x + mItemWidth / 2;
        int cy = mItemHeight / 2;
        canvas.drawCircle(cx, cy, mRadius, mSelectedPaint);
        //圆角矩形
//        RectF r2 = new RectF();
//        r2.left = x + mPadding;
//        r2.right = x + mItemWidth - mPadding;
//        r2.top = mPadding;
//        r2.bottom = mItemHeight - mPadding;
//        canvas.drawRoundRect(r2, mCornerRadius, mCornerRadius, mSelectedPaint);
        return true;
    }

    /**
     * 绘制标记的事件日子
     *
     * @param canvas   canvas
     * @param calendar 日历calendar
     * @param x        日历Card x起点坐标
     */
    @Override
    protected void onDrawScheme(Canvas canvas, Calendar calendar, int x) {
        boolean isSelected = isSelected(calendar);

        List<Calendar.Scheme> schemes = calendar.getSchemes();
        if (null != schemes && !schemes.isEmpty()) {
            for (Calendar.Scheme scheme : schemes) {
                if (isSelected) {
                    mPointPaint.setColor(Color.WHITE);
                    mTextPaint.setColor(Color.WHITE);
                } else {
                    mPointPaint.setColor(scheme.getShcemeColor());
                    mTextPaint.setColor(scheme.getShcemeColor());
                }
//                Logger.d("type: " + scheme.getType());
                if (scheme.getType() == 11) { //自定义事件类型
                    canvas.drawText(scheme.getScheme(), x + mItemWidth - 6 * mPadding, 5 * mPadding, mTextPaint);
                } else if (scheme.getType() == 10) {
                    canvas.drawCircle(x + mItemWidth / 2, mItemHeight - 3 * mPadding, mPointRadius, mPointPaint);
                }
            }
        }

    }

    /**
     * 绘制文本
     *
     * @param canvas     canvas
     * @param calendar   日历calendar
     * @param x          日历Card x起点坐标
     * @param hasScheme  是否是标记的日期
     * @param isSelected 是否选中
     */
    @Override
    protected void onDrawText(Canvas canvas, Calendar calendar, int x, boolean hasScheme, boolean isSelected) {
        int cx = x + mItemWidth / 2;
        int cy = mItemHeight / 2;
        int top = -mItemHeight / 5;

        if (calendar.isCurrentDay() && !isSelected) {
            canvas.drawCircle(cx, cy, mRadius, mCurrentDayPaint);
//            RectF r2 = new RectF();
//            r2.left = x + mPadding;
//            r2.right = x + mItemWidth - mPadding;
//            r2.top = mPadding;
//            r2.bottom = mItemHeight - mPadding;
//            canvas.drawRoundRect(r2, mCornerRadius, mCornerRadius, mCurrentDayPaint);
        }

        if (hasScheme) {
            List<Calendar.Scheme> schemes = calendar.getSchemes();
        }

//        if (calendar.isWeekend() && calendar.isCurrentMonth()) {
//            mCurMonthTextPaint.setColor(0xFF489dff);
//            mCurMonthLunarTextPaint.setColor(0xFF489dff);
//            mSchemeTextPaint.setColor(0xFF489dff);
//            mSchemeLunarTextPaint.setColor(0xFF489dff);
//            mOtherMonthLunarTextPaint.setColor(0xFF489dff);
//            mOtherMonthTextPaint.setColor(0xFF489dff);
//        } else {
//            mCurMonthTextPaint.setColor(0xff333333);
//            mCurMonthLunarTextPaint.setColor(0xffCFCFCF);
//            mSchemeTextPaint.setColor(0xff333333);
//            mSchemeLunarTextPaint.setColor(0xffCFCFCF);
//
//            mOtherMonthTextPaint.setColor(0xFFe1e1e1);
//            mOtherMonthLunarTextPaint.setColor(0xFFe1e1e1);
//        }

        if (isSelected) {
            canvas.drawText(String.valueOf(calendar.getDay()), cx, mTextBaseLine + top,
                    mSelectTextPaint);
            canvas.drawText(calendar.getLunar(), cx, mTextBaseLine + mItemHeight / 10, mSelectedLunarTextPaint);
        } else if (hasScheme) {

            canvas.drawText(String.valueOf(calendar.getDay()), cx, mTextBaseLine + top,
                    calendar.isCurrentMonth() ? mSchemeTextPaint : mOtherMonthTextPaint);

            boolean hasFestival = !TextUtils.isEmpty(calendar.getGregorianFestival())
                    || !TextUtils.isEmpty(calendar.getTraditionFestival());

            canvas.drawText(calendar.getLunar(), cx, mTextBaseLine + mItemHeight / 10,
                    hasFestival ? mSolarTermTextPaint : mSchemeLunarTextPaint);
        } else {
            canvas.drawText(String.valueOf(calendar.getDay()), cx, mTextBaseLine + top,
                    calendar.isCurrentDay() ? mCurDayTextPaint :
                            calendar.isCurrentMonth() ? mCurMonthTextPaint : mOtherMonthTextPaint);

            boolean hasFestival = !TextUtils.isEmpty(calendar.getGregorianFestival())
                    || !TextUtils.isEmpty(calendar.getTraditionFestival());

            canvas.drawText(calendar.getLunar(), cx, mTextBaseLine + mItemHeight / 10,
                    calendar.isCurrentDay() ? mCurDayLunarTextPaint :
                            hasFestival ? mSolarTermTextPaint :
                                    calendar.isCurrentMonth() ?
                                            mCurMonthLunarTextPaint : mOtherMonthLunarTextPaint);
        }
    }

    /**
     * dp转px
     *
     * @param context context
     * @param dpValue dp
     * @return px
     */
    private static int dipToPx(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }
}
