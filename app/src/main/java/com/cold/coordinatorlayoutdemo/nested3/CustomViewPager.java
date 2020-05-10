package com.cold.coordinatorlayoutdemo.nested3;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.viewpager.widget.ViewPager;

public class CustomViewPager extends ViewPager {

    private float mDownY;
    private float mDownX;

    public CustomViewPager(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
//                touch = false;
                intercept = false;
                mDownY = ev.getY();
                mDownX = ev.getX();
                System.out.println("===============> dispatchTouchEvent ACTION_DOWN ev.getY()： " +  ev.getY() + "  ev.getX(): " + ev.getX());
                break;
        }
        return super.dispatchTouchEvent(ev);

    }

    /**
     * 表示是否已经把事件叫给了子view处理，如果是，则这一系列的事件都不处理
     */
    private boolean intercept = false;

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        boolean touch = super.onTouchEvent(ev);
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                mDownY = ev.getY();
                mDownX = ev.getX();
                System.out.println("===============> ACTION_DOWN mDownY： " +  mDownY + "  mDownX: " + mDownX);
                break;
            case MotionEvent.ACTION_MOVE:
                //左右滑动
                // 先记录第一点按下的事件坐标 ACTION_DOWN
                // 每次移动判断是左右移动还是垂直移动，判断条件是垂直位移大于水平位移
                // 如果是垂直移动，添加一个标记，这个标记在ACTION_DOWN下复位，标记只是垂直移动，
                // 如果是垂直移动，则不响应Viepager的左右切换事件，知道下一个ACTION_DOWN来了为止

                System.out.println("===============> ACTION_MOVE mDownY： " +  mDownY + "  mDownX: " + mDownX);
                System.out.println("===============> ACTION_MOVE ev.getY()： " +  ev.getY() + "  ev.getX(): " + ev.getX());
                System.out.println("===============> ACTION_MOVE flag： " +  (Math.abs(ev.getY() - mDownY) - Math.abs(ev.getX() - mDownX) > 0));
                if (Math.abs(ev.getY() - mDownY) - Math.abs(ev.getX() - mDownX) > 0) {
                    intercept = true;
                }
                break;
            case MotionEvent.ACTION_UP:
                System.out.println("===============> ACTION_UP");
                break;
        }
        if(intercept) {
            return false;
        } else {
            return super.onTouchEvent(ev);
        }
//        return touch;
    }
}
