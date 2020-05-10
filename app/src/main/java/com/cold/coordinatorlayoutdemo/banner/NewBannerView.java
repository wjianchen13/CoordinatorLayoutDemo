package com.cold.coordinatorlayoutdemo.banner;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.cold.coordinatorlayoutdemo.R;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import static android.graphics.Paint.ANTI_ALIAS_FLAG;

/**
 * Created by Administrator on 2016/12/8.
 */

public class NewBannerView extends RelativeLayout {

    protected ImageSlider switcher;
    private ImageView ivCancel;
    protected ArrayList<BannerBean> beans;
    private final Paint mPaintPageFill = new Paint(ANTI_ALIAS_FLAG);
    private final Paint mPaintStroke = new Paint(ANTI_ALIAS_FLAG);
    private final Paint mPaintFill = new Paint(ANTI_ALIAS_FLAG);
    private boolean mCentered;
    private float mRadius;
    private float mCurrentPage;
    private float dividerPadding;
    private JSONArray jsonArray;
    private OnBannarViewListener mBannarViewListener;//Bannar关闭xx的监听器;

    protected int screenW;

    public NewBannerView(Context context) {
        super(context);
    }

    public NewBannerView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    {
        initView();
        init();
    }


    protected void refresh() {
        List<String> urls = new ArrayList<>();
        for (BannerBean bean : beans) {
            urls.add(bean.getImgUrl());
        }
        switcher.setUrls(urls);
        if (urls.size() > 1)
            switcher.start();
    }

    protected void refresh1() {
        List<Integer> urls = new ArrayList<>();
        for (BannerBean bean : beans) {
            urls.add(bean.getrId());
        }
        switcher.setResIds(urls);
        if (urls.size() > 1)
            switcher.start();
    }

    private void initView() {
        screenW = getResources().getDisplayMetrics().widthPixels;
        switcher = new ImageSlider(getContext());
        switcher.setMode(ImageSlider.Mode.LOCAL);
        ivCancel = new ImageView(getContext());
        ivCancel.setImageResource(R.drawable.btn_clean_black_selector);
        beans = new ArrayList<>();

        switcher.setDisplayer(new ImageSlider.ImageDisplayer() {
            @Override
            public void onDisplay(String url, final ImageView imageView) {
                imageView.setScaleType(ImageView.ScaleType.FIT_XY);
//                if (AppEnviron.isMisheSpecial()
//                        ||AppEnviron.isYueaiSpecial()) {
//                    ImageHelper.loadRoundImage(getContext(), url, imageView, R.drawable.banner_default, ScreenUtils.dip2px(5));
//                } else {
//                    ImageHelper.loadImage(getContext(), url, imageView, R.drawable.banner_default);
//                }

            }

            @Override
            public void onDisplay(int resId, ImageView imageView) {
                imageView.setImageResource(resId);
            }
        });

        switcher.setOnItemClickListener(new ImageSlider.OnItemClickListener() {
            @Override
            public void onItemClick(int index, View imageView) {
                if (index > beans.size() || beans.isEmpty())
                    return;
//                BannerBean bannerBean = beans.get(index);
//                if (bannerBean != null && !TextUtils.isEmpty(bannerBean.getWebUrl())) {
//                    Bundle bundle = new Bundle();
//                    bundle.putString(PayParamKey.PARAM_WEB_URL, bannerBean.getWebUrl());
//                    Context context = getContext();
//                    if(context instanceof PayCoreActivity){
//                        // 来自充值界面的点击
//                        UmengSDKUtil.onEventTimes(DobyApp.app(), UmengSDKUtil.U_CLICK__PAY_BANNER);
//                    }
//                    Go.webload(context)
//                            .with(bundle)
//                            .navigation();
//                }
            }
        });
        switcher.setClickable(true);
        switcher.setBackgroundResource(R.drawable.banner_default);
        switcher.setPagerSelector(new ImageSlider.OnPagerSelector() {
            @Override
            public void pageSelected(int position) {
                mCurrentPage = position;
            }
        });

        addView();
        LayoutParams params = new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        params.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
//        addView(ivCancel, params);
//        ivCancel.setOnClickListener(new OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if (mBannarViewListener != null)
//                    mBannarViewListener.onBannarChangeListener();
//            }
//        });


        setBackgroundColor(Color.WHITE);
    }

    protected void addView() {
        addView(switcher, ViewGroup.LayoutParams.MATCH_PARENT, (int) (screenW * (6f / 25)));
    }


    /**
     * 设置关闭xx的监听器
     *
     * @param onBannarViewListener
     */
    public void setBannarChangeListener(OnBannarViewListener onBannarViewListener) {
        mBannarViewListener = onBannarViewListener;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec + getPaddingBottom());
    }


    private void init() {
        mCentered = false;
        mPaintPageFill.setStyle(Paint.Style.FILL);
        mPaintPageFill.setColor(Color.WHITE);

//        mPaintStroke.setStyle(Paint.Style.STROKE);
        mPaintStroke.setStyle(Paint.Style.FILL);
        mPaintStroke.setColor(Color.parseColor("#E6FFFFFF"));
//        mPaintStroke.setStrokeWidth(dp2px(1));
        mPaintFill.setStyle(Paint.Style.FILL);
        mRadius = dp2px(3);
       if (getResources().getDisplayMetrics().densityDpi <= DisplayMetrics.DENSITY_HIGH)
            dividerPadding = dp2px(4);
        else
            dividerPadding = dp2px(2);


    }

    @Override
    protected void dispatchDraw(Canvas canvas) {
        super.dispatchDraw(canvas);


        final int count = beans.size();
        if (count == 0) {
            return;
        }

        int longSize;
        int longPaddingBefore;
        int longPaddingAfter;
        int shortPaddingBefore;
        longSize = getWidth();
        longPaddingBefore = getPaddingLeft();
        longPaddingAfter = getPaddingRight();
        // shortPaddingBefore = getPaddingTop();

        shortPaddingBefore = (int) (getHeight() - getPaddingBottom() - mRadius * 2.8);
        final float threeRadius = mRadius * dividerPadding;
        final float shortOffset = shortPaddingBefore + mRadius;
        float longOffset = longPaddingBefore + mRadius;
        if (mCentered) {
            longOffset += ((longSize - longPaddingBefore - longPaddingAfter) / 2.0f) - ((count * threeRadius) / 2.0f);
        } else
            longOffset += ((longSize - longPaddingBefore - longPaddingAfter)) - ((count * threeRadius));

        float dX;
        float dY;

//        float pageFillRadius = mRadius;
//        if (mPaintStroke.getStrokeWidth() > 0) {
//            pageFillRadius -= mPaintStroke.getStrokeWidth() / 2.0f;
//        }

        //Draw stroked circles
        for (int iLoop = 0; iLoop < count; iLoop++) {
            float drawLong = longOffset + (iLoop * threeRadius);
            dX = drawLong;
            dY = shortOffset;
            // Only paint fill if not completely transparent
           /* if (mPaintPageFill.getAlpha() > 0) {
                canvas.drawCircle(dX, dY, pageFillRadius + 1, mPaintPageFill);
            }
*/
            // Only paint stroke if a stroke width was non-zero
//            if (pageFillRadius != mRadius) {
            if (mCurrentPage!=iLoop) {
                canvas.drawCircle(dX, dY, mRadius, mPaintStroke);
            }
        }

        //Draw the filled circle according to the current scroll
        /*float cx = (mSnap ? mSnapPage : mCurrentPage) * threeRadius;
        if (!mSnap) {
            cx += mPageOffset * threeRadius;
        }*/
        float cx = mCurrentPage * threeRadius;
        dX = longOffset + cx;
        dY = shortOffset;
        mPaintFill.setColor(Color.WHITE);
//        canvas.drawCircle(dX, dY, mRadius, mPaintFill);
        canvas.drawRoundRect(new RectF(dX-dp2px(6),dY-mRadius,dX+dp2px(6),dY+mRadius),mRadius,mRadius,mPaintFill);

    }

    public float dp2px(int dp) {
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, getResources().getDisplayMetrics());

    }

    @Override
    protected void onDetachedFromWindow() {
        clear();
        super.onDetachedFromWindow();
    }

    public void clear() {
        switcher.stop();
    }

    public void update(JSONArray jsonArray) {

        if (jsonArray == null || TextUtils.isEmpty(jsonArray.toString()) || jsonArray.toString().equals("[]")) {
            setBackgroundResource(R.drawable.banner_default);
            return;
        }

        if (!beans.isEmpty() && this.jsonArray != null && this.jsonArray.toString().equals(jsonArray.toString())) {
            //如果内容和上次的一样 无需重新更新
            if (switcher.isStop())
                switcher.start();
            return;
        }
        this.jsonArray = jsonArray;

        int length = jsonArray.length();
        beans.clear();
        for (int i = 0; i < length; i++) {
            BannerBean bean = new BannerBean();
            JSONObject object = jsonArray.optJSONObject(i);
//			bean.setImgUrl(object.optString("pic_new", ""));
            bean.setImgUrl(object.optString("pic_new", ""));
            bean.setWebUrl(object.optString("url", ""));
            beans.add(bean);
        }
        if (beans.size() > 0) {
            refresh();
        }
    }
}
