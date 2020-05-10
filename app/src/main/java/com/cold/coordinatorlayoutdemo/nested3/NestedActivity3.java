package com.cold.coordinatorlayoutdemo.nested3;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentPagerAdapter;

import com.cold.coordinatorlayoutdemo.R;
import com.cold.coordinatorlayoutdemo.mix.ScrollFragment;
import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

public class NestedActivity3  extends AppCompatActivity {
    private TabLayout tabLayout;
    private CustomViewPager viewPager;
    private List<BaseFragment> bodyFragments;
    private ImageView mHeaderIcon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_nested3);
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
                Toast.makeText(NestedActivity3.this, "click", Toast.LENGTH_SHORT).show();
            }
        });
        bodyFragments = new ArrayList<>();
        MyFragment myFragment1 = new MyFragment();
        myFragment1.setmTitle("ta回答的");
        ScrollFragment3 myFragment2 = new ScrollFragment3();
        myFragment2.setmTitle("ta得到的");
        bodyFragments.add(myFragment1);
        bodyFragments.add(myFragment2);

        FragmentPagerAdapter mAdapter = new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return bodyFragments.get(position);
            }

            @Override
            public int getCount() {
                return bodyFragments.size();
            }

//            ViewPager与TabLayout绑定后，这里获取到PageTitle就是Tab的Text
            @Override
            public CharSequence getPageTitle(int position) {
                return bodyFragments.get(position).getmTitle();
            }
        };
        viewPager.setAdapter(mAdapter);
        viewPager.setOffscreenPageLimit(2);
        tabLayout.setupWithViewPager(viewPager);//将TabLayout和ViewPager关联起来。

    }
}

