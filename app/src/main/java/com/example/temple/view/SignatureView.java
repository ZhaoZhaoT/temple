package com.example.temple.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PaintFlagsDrawFilter;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import java.io.Closeable;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class SignatureView extends View {
    private Paint mPaint;
    private Path mPath;
    private Canvas mCanvas;
    private Bitmap mBitmap;
    private float mLastX, mLastY;//上次的坐标

    public SignatureView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    /***
     * 初始化
     */
    private void init() {
        //关闭硬件加速
        setLayerType(View.LAYER_TYPE_SOFTWARE, null);

        //画笔
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG | Paint.DITHER_FLAG);
        mPaint.setAntiAlias(true);
        mPaint.setStrokeWidth(30f);
        mPaint.setColor(Color.parseColor("#3B3A38"));
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeJoin(Paint.Join.ROUND);//使画笔更加圆润
        mPaint.setStrokeCap(Paint.Cap.ROUND);//同上

        //路径
        mPath = new Path();

        //保存签名的画布
        post(new Runnable() {//拿到控件的宽和高
            @Override
            public void run() {
                mBitmap = Bitmap.createBitmap(getWidth(), getHeight(), Bitmap.Config.ARGB_8888);
                mCanvas = new Canvas(mBitmap);
                mCanvas.setDrawFilter(new PaintFlagsDrawFilter(0, Paint.ANTI_ALIAS_FLAG|Paint.FILTER_BITMAP_FLAG));
            }
        });
    }

    /**
     * 绘制
     */
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (mBitmap != null) {
            canvas.drawColor(Color.parseColor("#00FFFFFF"));//绘制背景白色
            canvas.setDrawFilter(new PaintFlagsDrawFilter(0, Paint.ANTI_ALIAS_FLAG|Paint.FILTER_BITMAP_FLAG));
            drawSignaturePath(); //将路径绘制在mBitmap上
            canvas.drawBitmap(mBitmap, 0, 0, null);//将mBitmap绘制在canvas上
        }
    }

    /**
     * 清空绘制内容
     */
    public void clear() {
        mPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.CLEAR));
        mCanvas.drawPaint(mPaint);
        mPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC));
        mPath.reset();
        invalidate();
    }
    /**
     * 保存到指定的文件夹中
     */
    public boolean save(String filePath) {
        if (mBitmap != null && mLastY != 0f) {//没有绘制，就不保存
            //从View中得到Bitmap
            setDrawingCacheEnabled(true);
            buildDrawingCache(true);
            Bitmap bitmap = getDrawingCache(true);
            //保存图片
            File file = new File(filePath);
            FileOutputStream fileOutputStream = null;
            try {
                fileOutputStream = new FileOutputStream(file);
                if (bitmap.compress(Bitmap.CompressFormat.PNG, 100, fileOutputStream)) {
                    fileOutputStream.flush();
                    return true;
                }
            } catch (java.io.IOException e) {
                e.printStackTrace();
            } finally {
                closeStream(fileOutputStream);
            }
        }
        return false;
    }

    /**
     * 关闭流
     */
    private void closeStream(Closeable closeable) {
        if (closeable != null) {
            try {
                closeable.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 绘制签名
     */
    private void drawSignaturePath() {
        mCanvas.drawPath(mPath, mPaint);
    }

    /**
     * 触摸事件 触摸绘制
     */
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int action = event.getAction();
        float x = event.getX();
        float y = event.getY();
        switch (action) {
            case MotionEvent.ACTION_DOWN:
                mLastX = x;
                mLastY = y;
                mPath.moveTo(mLastX, mLastY);
                break;
            case MotionEvent.ACTION_MOVE:
                float dx = Math.abs(x - mLastX);
                float dy = Math.abs(y - mLastY);
                if (dx >= 3 || dy >= 3) {//绘制的最小距离 3px
                    //利用二阶贝塞尔曲线，使绘制路径更加圆滑
                    mPath.quadTo(mLastX, mLastY, (mLastX + x) / 2, (mLastY + y) / 2);
                }
                mLastX = x;
                mLastY = y;
                break;
            case MotionEvent.ACTION_UP:
                mPath.reset();
                break;
        }
        invalidate();
        return true;
    }

    /**
     * 测量
     */
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int wSpecMode = MeasureSpec.getMode(widthMeasureSpec);
        int wSpecSize = MeasureSpec.getSize(widthMeasureSpec);
        int hSpecMode = MeasureSpec.getMode(heightMeasureSpec);
        int hSpecSize = MeasureSpec.getSize(heightMeasureSpec);

        if (wSpecMode == MeasureSpec.EXACTLY && hSpecMode == MeasureSpec.EXACTLY) {
            setMeasuredDimension(widthMeasureSpec, heightMeasureSpec);
        } else if (wSpecMode == MeasureSpec.AT_MOST) {
            setMeasuredDimension(200, hSpecSize);
        } else if (hSpecMode == MeasureSpec.AT_MOST) {
            setMeasuredDimension(wSpecSize, 200);
        }
    }
}