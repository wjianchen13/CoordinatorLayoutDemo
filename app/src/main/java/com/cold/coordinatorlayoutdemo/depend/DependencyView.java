package com.cold.coordinatorlayoutdemo.depend;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.ViewConfiguration;
import android.widget.TextView;

import androidx.core.view.ViewCompat;

import com.cold.coordinatorlayoutdemo.utils.Utils;

public class DependencyView extends TextView {

    private  int mSlop;
    private float mLastX;
    private float mLastY;

    public DependencyView(Context context) {
        this(context,null);
    }

    public DependencyView(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public DependencyView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        setClickable(true);

        mSlop = ViewConfiguration.getTouchSlop();
//        mSlop = 1;
        Utils.log("mSlop: " + mSlop);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        // return super.onTouchEvent(event);
        int action = event.getAction();

        switch (action)
        {
            case MotionEvent.ACTION_DOWN:
                mLastX = event.getX();
                mLastY = event.getY();
                break;

            case MotionEvent.ACTION_MOVE:
                int deltax = (int) (event.getX() - mLastX);
                int deltay = (int) (event.getY() - mLastY);
                if (Math.abs(deltax) > mSlop || Math.abs(deltay) > mSlop) {
                    ViewCompat.offsetTopAndBottom(this,deltay);
                    ViewCompat.offsetLeftAndRight(this,deltax);
                    mLastX = event.getX();
                    mLastY = event.getY();
                }

                break;

            case MotionEvent.ACTION_UP:
                mLastX = event.getX();
                mLastY = event.getY();
                break;

            default:
                break;

        }

        return true;
    }
}
