package com.cold.coordinatorlayoutdemo.test4;

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
 * 上下拉刷新的例子,项目徽章
 * 改成自己项目需要的方式
 */
public class TestActivity4 extends AppCompatActivity {

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
    private TabLayoutMediator tabMediator;
    private ViewPager2.OnPageChangeCallback pageChangeCallback;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_test4);

        // Optional activity-level refresh layout if present in layout
        activityRefreshLayout = findViewById(R.id.activity_refresh_layout);
        if (activityRefreshLayout != null) {
            activityRefreshLayout.setEnableLoadMore(false);
            activityRefreshLayout.setOnRefreshListener(new OnRefreshListener() {
                @Override
                public void onRefresh(RefreshLayout refreshLayout) {
                    int currentItem = viewPager.getCurrentItem();
                    Fragment fragment = getSupportFragmentManager()
                            .findFragmentByTag("f" + mAdapter.getItemId(currentItem));
                    if (fragment instanceof BaseFragment) {
                        ((BaseFragment) fragment).onActivityRefresh(activityRefreshLayout);
                    } else {
                        if (activityRefreshLayout != null) activityRefreshLayout.finishRefresh();
                    }
                }
            });
        }

        llytContent = findViewById(R.id.llyt_content);
        tvTitle = findViewById(R.id.rlyt_title);
//        if (llytContent != null) llytContent.setVisibility(View.GONE);
        vHolder = findViewById(R.id.v_holder);
        nbv = findViewById(R.id.banner);
        llytTab = findViewById(R.id.llyt_tab);
//        llytTab.setVisibility(View.GONE);
        tabLayout = findViewById(R.id.community_container_tab_layout);
        viewPager = findViewById(R.id.viewPager);
        mHeaderIcon = findViewById(R.id.person_user_head_icon);
        if (mHeaderIcon != null) {
            mHeaderIcon.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(TestActivity4.this, "click", Toast.LENGTH_SHORT).show();
                }
            });
        }


        toolbaretail = findViewById(R.id.toolbaretail);
        if (toolbaretail != null) toolbaretail.setTitle("");

        tabTitles = new ArrayList<>();
        tabTitles.add("ta回答的");
        tabTitles.add("ta得到的");

        // use FragmentActivity constructor for adapter
        mAdapter = new HomeTabMainFragmentAdapter(this, tabTitles);
        viewPager.setAdapter(mAdapter);
        int offscreen = Math.max(1, tabTitles.size() - 1);
        viewPager.setOffscreenPageLimit(offscreen);

        tabMediator = new TabLayoutMediator(tabLayout, viewPager, (tab, position) -> {
            tab.setText(tabTitles.get(position));
        });
        tabMediator.attach();

        // force tab visual settings
        if (tabLayout != null) {
            tabLayout.setTabMode(TabLayout.MODE_FIXED);
            tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
            tabLayout.setSelectedTabIndicatorColor(android.graphics.Color.YELLOW);
            try {
                int normalColor = getResources().getColor(R.color.white40);
                int selectedColor = getResources().getColor(R.color.white);
                tabLayout.setTabTextColors(normalColor, selectedColor);
            } catch (Exception ignored) {
                tabLayout.setTabTextColors(android.graphics.Color.LTGRAY, android.graphics.Color.WHITE);
            }
            // ensure text set
            for (int i = 0; i < tabTitles.size(); i++) {
                TabLayout.Tab tab = tabLayout.getTabAt(i);
                if (tab != null) tab.setText(tabTitles.get(i));
            }
        }

        // page callback placeholder
        pageChangeCallback = new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                // custom page change logic
            }
        };
        viewPager.registerOnPageChangeCallback(pageChangeCallback);

        shareImg = findViewById(R.id.share_img);
        appBar = findViewById(R.id.app_bar);
        if (appBar != null) {
            appBar.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
                @Override
                public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                    int Offset = Math.abs(verticalOffset);
                    setTitle(Offset, appBarLayout.getTotalScrollRange());
                    setHolder(Offset, appBarLayout.getTotalScrollRange());
                }
            });
        }
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

        if (nbv != null) {
            nbv.startAutoPlay();
            nbv.setDelayTime(2000);
            nbv.setImages(integers).setImageLoader(new GlideImageLoader()).start();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (tabMediator != null) {
            tabMediator.detach();
            tabMediator = null;
        }
        if (viewPager != null && pageChangeCallback != null) {
            viewPager.unregisterOnPageChangeCallback(pageChangeCallback);
            pageChangeCallback = null;
        }
        if (nbv != null) {
            try {
                nbv.stopAutoPlay();
            } catch (Exception ignored) {
            }
        }
    }

    /**
     * 根据百分比改变颜色透明度
     */
    public int changeAlpha(int color, float fraction) {
        int alpha = (int) (Color.alpha(color) * fraction);
        return Color.argb(alpha, 0, 128, 0);
    }
}
