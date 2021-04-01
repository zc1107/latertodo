package com.example.latertodo;

import android.content.Context;
import android.content.Intent;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;

import androidx.annotation.RequiresApi;

import java.util.Locale;
import java.util.Timer;
import java.util.TimerTask;

/**
 * TODO: document your custom view class.
 */
public class CountdownView extends View {

    // 控件宽
    private int width;
    // 控件高
    private int height;
    // 刻度盘半径
    private int dialRadius;
    // 进度条两端高
    private float signHeight = dp2px(6);

    // 定时进度条宽
    private float arcWidth = dp2px(6);
    // 倒计时状态
    private int status = 0;

    public static int COUNTDOWN_INIT = 0;
    public static int COUNTDOWN_START = 1;
    public static int COUNTDOWN_STOP = 2;
    public static int COUNTDOWN_PAUSE = 3;

    // 初始时间秒数
    private int startTime = 0;
    // 总时间秒数
    private int time = 0;
    // 时间-分
    private int minute = 0;
    // 时间-秒
    private int second = 0;
    // 刻度盘画笔
    private Paint dialPaint;
    // 时间画笔
    private Paint timePaint;
    // 是否移动
    private boolean isMove;
    // 当前旋转的角度
    private float rotateAngle;
    // 当前的角度
    private float currentAngle;
    // 时间改变监听
    private OnCountdownListener onCountdownListener;

    public CountdownView(Context context) {
        this(context, null);
    }

    public CountdownView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CountdownView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        // 时间画笔
        timePaint = new Paint();
        timePaint.setAntiAlias(true);
        timePaint.setColor(Color.parseColor("#FFFFFF"));
        timePaint.setTextSize(sp2px(70));
        timePaint.setStyle(Paint.Style.FILL);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        // 绘制时间
        drawTime(canvas);
    }

    private void drawTime(Canvas canvas) {
//        canvas.restore();
        canvas.translate(getWidth() / 2, getHeight() / 2);
        String timeText = String.format(Locale.CHINA, "%02d", minute) + ":" + String.format(Locale.CHINA, "%02d", second);
        // 获取时间的宽高
        float timeWidth = timePaint.measureText(timeText);
        float timeHeight = Math.abs(timePaint.ascent() + timePaint.descent());
        // 居中显示
        canvas.drawText(timeText, -timeWidth / 2, timeHeight / 2, timePaint);
    }

    /**
     * 设置倒计时
     *
     * @param time 总时间秒数
     */
    public void setCountdown(int time) {
        //设置的时间区间段为两小时以内
        if (startTime == 0) {
            startTime = time;
        }
        this.time = time;
        if (time < 0 || time > 7200) {
            return;
        }
        minute = time / 60;
        second = time % 60;
        //设置完时间之后，进度条从最末尾开始
        rotateAngle = time / startTime * 360;
        invalidate();
    }


    /**
     * @param status 倒计时的状态
     */
    public void setCountdownStatus(int status) {
        this.status = status;
    }

    public int getCountdownStatus(){ return this.status;}


    public void setStartTime() {
        onCountdownListener.countdown(startTime / 60);
    }

    /**
     * 设置倒计时监听
     *
     * @param onTempChangeListener 倒计时监听接口
     */
    public void setOnCountdownListener(OnCountdownListener onCountdownListener) {
        this.onCountdownListener = onCountdownListener;
    }

    /**
     * 倒计时监听接口
     */
    public interface OnCountdownListener {
        /**
         * 倒计时
         *
         * @param temp 时间
         */
        void countdown(int minute);
    }

    /**
     * dp转px
     *
     * @param dp dp值
     * @return px值
     */
    public int dp2px(float dp) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp,
                getResources().getDisplayMetrics());
    }

    /**
     * sp转px
     *
     * @param sp sp值
     * @return px值
     */
    private int sp2px(float sp) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, sp,
                getResources().getDisplayMetrics());
    }
   }