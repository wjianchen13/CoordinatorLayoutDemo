package com.cold.coordinatorlayoutdemo.mix;

import android.os.Bundle;

import android.util.Log;


import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.cold.coordinatorlayoutdemo.R;
import com.cold.coordinatorlayoutdemo.mix.behavior.MainHeaderBehavior;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;

public class TestActivity3 extends AppCompatActivity implements MainHeaderBehavior.OnHeaderStateListener {

    private ViewPager mViewPager;

    private MainHeaderBehavior mHeaderBehavior;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test3);
//
//        mHeaderBehavior = (HeaderBehavior) ((CoordinatorLayout.LayoutParams) (findViewById(R.id.header)).getLayoutParams()).getBehavior();
//
//        if (mHeaderBehavior != null) {
////            mHeaderBehavior.setTabSuspension(true);
//            mHeaderBehavior.setHeaderStateListener(this);
//        }

        ArrayList<Fragment> fragments = new ArrayList<>();
        ArrayList<String> titles = new ArrayList<>();

        fragments.add(ScrollFragment.newInstance());
        fragments.add(TypeFragment.newInstance());
        fragments.add(TypeFragment.newInstance());

        titles.add("tab1");
        titles.add("tab2");
        titles.add("tab3");

        mViewPager = (ViewPager) findViewById(R.id.viewpager);
        TabLayout tableLayout = (TabLayout) findViewById(R.id.tablayout);

        TypePageAdapter mTypeAdapter = new TypePageAdapter(getSupportFragmentManager());
        mTypeAdapter.setData(fragments, titles);
        mViewPager.setAdapter(mTypeAdapter);
        mViewPager.setOffscreenPageLimit(titles.size() - 1);

        tableLayout.setupWithViewPager(mViewPager);
        tableLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                mViewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

    @Override
    public void onHeaderClosed() {
        Log.e("status", "closed");
    }

    @Override
    public void onHeaderOpened() {
        Log.e("status", "opened");
    }

    @Override
    public void onBackPressed() {
        if (mHeaderBehavior != null && mHeaderBehavior.isClosed()) {
            mHeaderBehavior.openHeader();
        } else {
            super.onBackPressed();
        }
    }
}
