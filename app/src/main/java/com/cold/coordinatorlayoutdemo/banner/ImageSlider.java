package com.cold.coordinatorlayoutdemo.banner;

import android.content.Context;
import android.graphics.Color;
import android.os.CountDownTimer;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageSwitcher;
import android.widget.ImageView;

import com.cold.coordinatorlayoutdemo.R;

import java.util.ArrayList;
import java.util.List;


/**
 */
public class ImageSlider extends ImageSwitcher {
    /**
     * 播放模式
     * LOCAL，播放本地资源文件
     * REMOTE，播放url或者url和本地文件的混合
     */
    public enum Mode {
        LOCAL,
        REMOTE
    }

    private int mCurPosition;
    private GestureDetector mDetector;
    private Animation mLeftIn, mRightIn, mRightOut, mLeftOut;
    private List<String> mUrls = new ArrayList<String>();
    private List<Integer> mResIds = new ArrayList<>();
    private MyCountDownTimer mMyCountDownTimer = new MyCountDownTimer(5000, 1000);
    private ImageDisplayer mDislayer;
    private OnItemClickListener mOnItemClickListener;
    private boolean mIsStop = false;
    private boolean mAnimDirectionLeft = true;//动画方向是否左边
    private Mode mMode = Mode.REMOTE;
    private OnPagerSelector pagerSelector;
    private boolean isNotLoop; // 是否循环播放，默认可以循环

    public ImageSlider(Context context) {
        super(context);
        init();
    }

    public ImageSlider(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public boolean isStop() {
        return mIsStop;
    }

    private void init() {
        mDetector = new GestureDetector(getContext(), new MyOnGestureListener());
        mDetector = new GestureDetector(getContext(), new MyOnGestureListener());
        mLeftIn = AnimationUtils.loadAnimation(getContext(), R.anim.slide_in_from_left);
        mLeftOut = AnimationUtils.loadAnimation(getContext(), R.anim.slide_out_to_right);

        mRightIn = AnimationUtils.loadAnimation(getContext(), R.anim.slide_in_from_right);
        mRightOut = AnimationUtils.loadAnimation(getContext(), R.anim.slide_out_to_left);

        setInAnimation(mLeftIn);
        setOutAnimation(mLeftOut);
        setBackgroundColor(Color.WHITE);
        setFactory(new ViewFactory() {
            @Override
            public View makeView() {
                ImageView imageView = new ImageView(getContext());
                imageView.setScaleType(ImageView.ScaleType.FIT_XY);
                imageView.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT,
                        LayoutParams.MATCH_PARENT));
                return imageView;
            }
        });
       /* setOnTouchListener(new OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {

                return true;
            }
        });*/
    }

   /*@Override
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_UP) {
            mMyCountDownTimer.start();
        }

       return mDetector.onTouchEvent(event);
    }
*/

    @Override
    public boolean onTouchEvent(MotionEvent event) {

    //    Log.e("xxx", event.toString());

        getParent().requestDisallowInterceptTouchEvent(true);
        if (event.getAction() == MotionEvent.ACTION_UP || event.getAction() == MotionEvent.ACTION_CANCEL) {
            mMyCountDownTimer.start();
        }

        mDetector.onTouchEvent(event);

        return true;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
//        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    public void setMode(Mode mode) {
        mMode = mode;
    }

    public void start() {
        mIsStop = false;
        mMyCountDownTimer.start();
//        mSliderThread.start();
    }

    public void stop() {
        mIsStop = true;
    }

    public void setNotLoop(boolean notLoop) {
        isNotLoop = notLoop;
    }

    public void setUrls(List<String> urls) {
        if (urls == null) {
            mUrls = new ArrayList<String>();
        } else {
            mUrls.clear();
            mUrls = urls;
        }
        if (!"".equals(urls))
            autoDisplayImage(mCurPosition);
    }

    public void setResIds(List<Integer> resIds) {
        if (resIds == null) {
            mResIds = new ArrayList<>();
            return;
        }
        mResIds.clear();
        mResIds = resIds;
    }

    public void setDisplayer(ImageDisplayer dislayer) {
        mDislayer = dislayer;
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        mOnItemClickListener = onItemClickListener;
    }

    public void setPagerSelector(OnPagerSelector pagerSelector) {
        this.pagerSelector = pagerSelector;
    }

    public interface ImageDisplayer {
        void onDisplay(String url, ImageView imageView);

        void onDisplay(int resId, ImageView imageView);
    }

    public interface OnItemClickListener {
        void onItemClick(int index, View imageView);
    }

    private class MyOnGestureListener extends GestureDetector.SimpleOnGestureListener {

        @Override
        public boolean onDown(MotionEvent e) {
            return super.onDown(e);
        }

        @Override
        public boolean onSingleTapConfirmed(MotionEvent e) {
            mMyCountDownTimer.cancel();
            if (mOnItemClickListener != null) {
                mOnItemClickListener.onItemClick(mCurPosition, getCurrentView());
            }
            return super.onSingleTapConfirmed(e);
        }

        @Override
        public boolean onSingleTapUp(MotionEvent e) {
            return super.onSingleTapUp(e);
        }

        @Override
        public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
            return super.onScroll(e1, e2, distanceX, distanceY);
        }

        @Override
        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
            if (mResIds.isEmpty()) {
                return false;
            }
            if (velocityX > 0) {
                --mCurPosition;
                if (mCurPosition < 0) {
                    if(!isNotLoop) {
                        mCurPosition = mResIds.size() - 1;
                    } else {
                        mCurPosition = mCurPosition + 1;
                        return false;
                    }
                }
                displayLeftImage(mCurPosition);
            } else {//右边
                ++mCurPosition;
                if(mCurPosition >= mResIds.size()) {
                    if (!isNotLoop) {
                        mCurPosition = mCurPosition % mResIds.size();
                    } else {
                        mCurPosition = mCurPosition - 1;
                        return false;
                    }
                }
                autoDisplayImage(mCurPosition);
            }
            return false;
        }
    }

    private class ImageDisplayRunnable implements Runnable {
        private int position;

        public ImageDisplayRunnable(int position) {
            this.position = position;
        }

        @Override
        public void run() {
            autoDisplayImage(position);
        }
    }

    private void autoDisplayImage(int position) {
        if (mAnimDirectionLeft) {
            setInAnimation(mRightIn);
            setOutAnimation(mRightOut);
            mAnimDirectionLeft = false;
        }
        if (mDislayer != null) {
            ImageView imageView = (ImageView) getNextView();

            if (mMode == Mode.REMOTE && position < mUrls.size()) {
                mDislayer.onDisplay(mUrls.get(position), imageView);
            } else if (mMode == Mode.LOCAL && position < mResIds.size()) {
                mDislayer.onDisplay(mResIds.get(position), imageView);
            }
        }
//        LogUtils.d("下标" + mCurPosition);
        showNext();
        if (pagerSelector != null)
            pagerSelector.pageSelected(mCurPosition);

    }

    private void displayLeftImage(int position) {
        if (!mAnimDirectionLeft) {
            setInAnimation(mLeftIn);
            setOutAnimation(mLeftOut);
            mAnimDirectionLeft = true;
        }
        if (mDislayer != null) {
            ImageView imageView = (ImageView) getNextView();

            if (mMode == Mode.REMOTE && position < mUrls.size()) {
                mDislayer.onDisplay(mUrls.get(position), imageView);
            } else if (mMode == Mode.LOCAL && position < mResIds.size()) {
                mDislayer.onDisplay(mResIds.get(position), imageView);
            }
        }
        showPrevious();
        if (pagerSelector != null)
            pagerSelector.pageSelected(mCurPosition);

    }

    private class MyCountDownTimer extends CountDownTimer {

        /**
         * @param millisInFuture    The number of millis in the future from the call
         *                          to {@link #start()} until the countdown is done and {@link
         *                          #onFinish()}
         *                          is called.
         * @param countDownInterval The interval along the way to receive
         *                          {@link #onTick(long)} callbacks.
         */
        public MyCountDownTimer(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);
        }

        @Override
        public void onTick(long millisUntilFinished) {

        }

        @Override
        public void onFinish() {
            if (mIsStop) {
                cancel();
                return;
            }
//            LogUtils.d("onFinish(long millisUntilFinished)");
            if (mUrls.isEmpty() && mResIds.isEmpty()) {
                return;
            }
            int totalSize = 0;
            if (mMode == Mode.LOCAL) {
                totalSize = mResIds.size();
            } else if (mMode == Mode.REMOTE) {
                totalSize = mUrls.size();
            }
            ++mCurPosition;
            mCurPosition = mCurPosition % totalSize;
            post(new ImageDisplayRunnable(mCurPosition));
            start();
        }
    }

    public static interface OnPagerSelector {
        public void pageSelected(int position);
    }
}

