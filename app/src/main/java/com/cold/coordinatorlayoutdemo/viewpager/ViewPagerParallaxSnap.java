package com.cold.coordinatorlayoutdemo.viewpager;

import android.os.Bundle;

import com.cold.coordinatorlayoutdemo.R;
import com.google.android.material.tabs.TabLayout;

import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;
import java.util.List;

public class ViewPagerParallaxSnap extends AppCompatActivity {
    ViewPager mViewPager;
    List<Fragment> mFragments;
    Toolbar mToolbar;

    String[]  mTitles=new String[]{
            "主页","微博","相册"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_pager_parallax_snap);
        mViewPager=(ViewPager)findViewById(R.id.viewpager);
        mToolbar= (Toolbar) findViewById(R.id.toolbar);

        mToolbar.setTitle("唐嫣");
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        setupViewPager();
    }

    private void setupViewPager() {
        final ViewPager viewPager = (ViewPager) findViewById(R.id.viewpager);
        setupViewPager(viewPager);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);
    }

    private void setupViewPager(ViewPager viewPager) {
        mFragments=new ArrayList<>();
//        for(int i=0;i<mTitles.length;i++){
//            ListFragment listFragment = ListFragment.newInstance(mTitles[i]);
//            mFragments.add(listFragment);
//        }
        mFragments.add(ListFragment.newInstance(mTitles[0]));
        mFragments.add(ListFragment1.newInstance(mTitles[1]));
        mFragments.add(ListFragment.newInstance(mTitles[2]));
        BaseFragmentAdapter adapter =
                new BaseFragmentAdapter(getSupportFragmentManager(),mFragments,mTitles);



        viewPager.setAdapter(adapter);
    }
}
