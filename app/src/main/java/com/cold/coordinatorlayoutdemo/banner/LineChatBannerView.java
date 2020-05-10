package com.cold.coordinatorlayoutdemo.banner;

import android.content.Context;
import android.util.AttributeSet;
import android.view.ViewGroup;

import com.cold.coordinatorlayoutdemo.R;

/**
 * Created by Administrator on 2016/12/8.
 * 有问题，滑动冲突处理不了
 */

public class LineChatBannerView extends NewBannerView {

    public LineChatBannerView(Context context) {
        super(context);
    }

    public LineChatBannerView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    protected void addView() {
        addView(switcher, ViewGroup.LayoutParams.MATCH_PARENT, screenW);
    }

    public void testBanner() {
        beans.clear();
        BannerBean bean = new BannerBean();
        bean.setImgUrl("http://tapi.95xiu.com/upload/anchor_image/240X220/10/134707310_1586235696.jpg");
        bean.setrId(R.drawable.img1);
        beans.add(bean);
        BannerBean bean1 = new BannerBean();
        bean1.setImgUrl("http://tapi.95xiu.com/upload/anchor_image/345X257/63/6156363_56447f0c8a443.jpg");
        bean.setrId(R.drawable.img2);
        beans.add(bean1);
        BannerBean bean2 = new BannerBean();
        bean2.setImgUrl("http://tapi.95xiu.com/upload/anchor_image/345X257/69/369_574819ef696ba.png");
        bean.setrId(R.drawable.img3);
        beans.add(bean2);
        if (beans.size() > 0) {
            refresh1();
        }
    }
}
