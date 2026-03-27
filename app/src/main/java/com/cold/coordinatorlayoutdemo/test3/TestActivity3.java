package com.cold.coordinatorlayoutdemo.test3;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import com.cold.coordinatorlayoutdemo.R;
import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.youth.banner.Banner;

import java.util.ArrayList;
import java.util.List;

/**
 * 上下拉刷新的例子,不修改
 */
public class TestActivity3 extends AppCompatActivity {
    private TabLayout tabLayout;
    private ViewPager2 viewPager;
    private List<String> tabTitles;
    private ImageView mHeaderIcon;
    private Toolbar toolbaretail;
    private AppBarLayout appBar;
    private ImageView shareImg;
    private LinearLayout llytContent;
    private Banner nbv;
    private LinearLayout llytTab;
    private View vHolder;
    private RelativeLayout tvTitle;
    private SmartRefreshLayout activityRefreshLayout;
    private HomeTabMainFragmentAdapter mAdapter;
//    private LineChatBannerView nbv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_test3);

        // Activity 级别的下拉刷新，只负责刷新动画，不负责上拉加载
        activityRefreshLayout = findViewById(R.id.activity_refresh_layout);
        activityRefreshLayout.setEnableLoadMore(false);
        activityRefreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshLayout) {
                // 找到当前显示的 Fragment，委托它执行数据刷新
                // ViewPager2 中 Fragment 的 tag 格式为 "f" + itemId
                int currentItem = viewPager.getCurrentItem();
                Fragment fragment = getSupportFragmentManager()
                        .findFragmentByTag("f" + mAdapter.getItemId(currentItem));
                if (fragment instanceof BaseFragment) {
                    ((BaseFragment) fragment).onActivityRefresh(activityRefreshLayout);
                } else {
                    // 找不到 Fragment 时直接结束刷新
                    activityRefreshLayout.finishRefresh();
                }
            }
        });

        llytContent = findViewById(R.id.llyt_content);
        tvTitle = findViewById(R.id.rlyt_title);
        llytContent.setVisibility(View.GONE);
        vHolder = findViewById(R.id.v_holder);
        nbv = findViewById(R.id.banner);
        llytTab = findViewById(R.id.llyt_tab);
//        llytTab.setVisibility(View.GONE);
        tabLayout = findViewById(R.id.community_container_tab_layout);
        viewPager = findViewById(R.id.viewPager);
        mHeaderIcon = findViewById(R.id.person_user_head_icon);
        mHeaderIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                final CustomDialog customDialog = new CustomDialog(MainActivity177.this);
//                customDialog.setAwardGetNum(10);
//                customDialog.setmClickListener(new CustomDialog.ViewClickListener() {
//                    @Override
//                    public void onClick(View view) {
//                        if (customDialog != null) {
//                            customDialog.dismiss();
//                        }
//                    }
//                });
//
//                customDialog.show();
                Toast.makeText(TestActivity3.this, "click", Toast.LENGTH_SHORT).show();
            }
        });


        toolbaretail = findViewById(R.id.toolbaretail);
//        toolbaretail.setTitle("姓名不能超过10个字呀呀呀");
//        View v = toolbaretail.getChildAt(0);
//        if(v != null && v instanceof TextView) {
//           TextView tv = (TextView)v;
//            tv.getLayoutParams().width = LinearLayout.LayoutParams.MATCH_PARENT;
//
//            tv.setGravity(Gravity.CENTER_HORIZONTAL);
//        }
        toolbaretail.setTitle("");

        tabTitles = new ArrayList<>();
        tabTitles.add("ta回答的");
        tabTitles.add("ta得到的");

        mAdapter = new HomeTabMainFragmentAdapter(getSupportFragmentManager(), getLifecycle(), tabTitles);
        viewPager.setAdapter(mAdapter);
        viewPager.setOffscreenPageLimit(2);

        new TabLayoutMediator(tabLayout, viewPager, (tab, position) -> {
            tab.setText(tabTitles.get(position));
        }).attach();

        shareImg = findViewById(R.id.share_img);
        appBar = findViewById(R.id.app_bar);
        appBar.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                //verticalOffset  当前偏移量 appBarLayout.getTotalScrollRange() 最大高度 偏移值
                int Offset = Math.abs(verticalOffset); //目的是将负数转换为绝对正数；
                System.out.println("===============> verticalOffset: " + verticalOffset);
                System.out.println("===============> getTotalScrollRange: " + appBarLayout.getTotalScrollRange());
                //标题栏的渐变
//                toolbaretail.setBackgroundColor(changeAlpha(getResources().getColor(R.color.redcustom)
//                        , Math.abs(verticalOffset * 1.0f) / appBarLayout.getTotalScrollRange()));

                setTitle(Offset, appBarLayout.getTotalScrollRange());
                setHolder(Offset, appBarLayout.getTotalScrollRange());
                /**
                 * 当前最大高度便宜值除以2 在减去已偏移值 获取浮动 先显示在隐藏
                 */
//                if (Offset < appBarLayout.getTotalScrollRange() / 2) {
//                    toolbaretail.setTitle("");
//                    toolbaretail.setAlpha((appBarLayout.getTotalScrollRange() / 2 - Offset * 1.0f) / (appBarLayout.getTotalScrollRange() / 2));
//                    shareImg.setAlpha((appBarLayout.getTotalScrollRange() / 2 - Offset * 1.0f) / (appBarLayout.getTotalScrollRange() / 2));
//                    shareImg.setImageDrawable(getResources().getDrawable(R.drawable.share_shop));
////                    toolbaretail.setNavigationIcon(R.drawable.shop_details_2);
//                    /**
//                     * 从最低浮动开始渐显 当前 Offset就是  appBarLayout.getTotalScrollRange() / 2
//                     * 所以 Offset - appBarLayout.getTotalScrollRange() / 2
//                     */
//                } else if (Offset > appBarLayout.getTotalScrollRange() / 2) {
//                    float floate = (Offset - appBarLayout.getTotalScrollRange() / 2) * 1.0f / (appBarLayout.getTotalScrollRange() / 2);
//                    toolbaretail.setAlpha(floate);
//                    shareImg.setAlpha(floate);
////                    toolbaretail.setNavigationIcon(R.drawable.image_left);
//                    shareImg.setImageDrawable(getResources().getDrawable(R.drawable.img_share));
//                    toolbaretail.setTitle("禄福来精品翡翠");
//                    toolbaretail.setAlpha(floate);
//                }
            }
        });
        initBanner();
//        nbv.testBanner();
    }

    private void setTitle(int scrollY, int all) {
        if(tvTitle != null) {
            float radio = getAllRadio(scrollY, all);
            tvTitle.setBackgroundColor(Color.argb((int)(getTitleBackgroundAlpha(radio) * 255), 255, 255, 255));
        }
    }

    private void setHolder(int scrollY, int all) {
        if(vHolder != null) {
            float radio = getAllRadio(scrollY, all);
            System.out.println("===============> radio: " + radio);
			vHolder.setBackgroundColor(Color.argb((int)(getTitleBackgroundAlpha(radio) * 255), 0, 0, 0));
		}
    }

    private float getAllRadio(int scrollY, int all) {
        float alpha = 0;
        int start = Math.abs(all / 3); // 375
        if(scrollY <= start) {
            alpha = 0;
        } else if(scrollY > start && scrollY <= start * 3) {
            alpha = (float)(scrollY - start) / (float)(start * 2);
        } else if(scrollY > start * 3) {
            alpha = 1;
        }
        return alpha;
    }

    /**
     * 获取背景因子, 2/3开始显示
     * @param
     * @return
     */
    private float getTitleBackgroundAlpha(float radio) {
        return radio >= 0.5 ? (radio - 0.5f) * 2 : 0.0f;
    }

    /**
     * 初始化轮播图
     */
    private void initBanner() {
        List<Integer> integers = new ArrayList<>();
        integers.add(R.drawable.img1);
        integers.add(R.drawable.img2);
        integers.add(R.drawable.img3);
//        integers.add(R.drawable.img4);
//        integers.add(R.drawable.img5);
//        integers.add(R.drawable.img6);
//        integers.add(R.drawable.img7);

        nbv.startAutoPlay();
        nbv.setDelayTime(2000);
        nbv.setImages(integers).setImageLoader(new GlideImageLoader()).start();
    }

    /**
     * 根据百分比改变颜色透明度
     */
    public int changeAlpha(int color, float fraction) {
        int alpha = (int) (Color.alpha(color) * fraction);
        return Color.argb(alpha, 0, 128, 0);
    }
}

