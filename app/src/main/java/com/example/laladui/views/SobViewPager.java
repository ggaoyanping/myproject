package com.example.laladui.views;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.viewpager.widget.ViewPager;

public class SobViewPager extends ViewPager {
    private long delayTime=1000;//设置滑动时间
    public SobViewPager(@NonNull Context context) {
        this(context,null);
    }
    public SobViewPager(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        //设置轮播的图的点击状态事件
        setOnTouchListener(new OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                int action=motionEvent.getAction();
                switch (action){
                    case MotionEvent.ACTION_DOWN:
                        stopLooper();//暂停轮播
                        break;
                    case MotionEvent.ACTION_CANCEL:
                    case MotionEvent.ACTION_UP:
                        startLooper();//启动轮播
                        break;
                }
                return false;
            }
        });
    }
    public void setDelayTime(long delayTime){
        this.delayTime=delayTime;
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        startLooper();
    }
    //启动轮播的函数
    private void startLooper() {
        postDelayed(mTask,delayTime);
    }
    private Runnable mTask= new Runnable() {
            @Override
            public void run() {
                int currentItem=getCurrentItem();
                currentItem++;
                setCurrentItem(currentItem);
                postDelayed(this,delayTime);

            }
    };

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        stopLooper();
    }
    //停止轮播函数
    private void stopLooper() {
        removeCallbacks(mTask);
    }
}
