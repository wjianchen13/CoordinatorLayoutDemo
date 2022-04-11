package com.cold.coordinatorlayoutdemo.test;

import android.os.Bundle;
import com.google.android.material.appbar.AppBarLayout;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.widget.LinearLayout;

import com.cold.coordinatorlayoutdemo.R;
import com.cold.coordinatorlayoutdemo.viewpager.ItemAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 *
 *
 */
public class TestActivity extends AppCompatActivity {
    RecyclerView mRecyclerView;
    List<String> mDatas;
    private ItemAdapter mAdapter;

//    Toolbar mToolbar;
    private LinearLayout linearLayout;
    private boolean canScroll = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerView);
//        /**
//         * 设置 toolBar
//         */
//        mToolbar = (Toolbar) findViewById(R.id.toolbar);
//        // 该属性必须在setSupportActionBar之前 调用
//        mToolbar.setTitle("toolBar");
//        setSupportActionBar(mToolbar);
        linearLayout = (LinearLayout) findViewById(R.id.rl_title);
        setScrollFlag(canScroll);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(layoutManager);
        DividerItemDecoration itemDecoration = new DividerItemDecoration(this,
                LinearLayoutManager.VERTICAL);
        mRecyclerView.addItemDecoration(itemDecoration);

        mDatas = new ArrayList<>();
        for (int i = 0; i < 50; i++) {
            String s = String.format("我是第%d个item", i);
            mDatas.add(s);
        }
        mAdapter = new ItemAdapter(this, mDatas);
        mRecyclerView.setAdapter(mAdapter);
    }

    public void setScrollFlag(boolean isCanScroll) {
        if(linearLayout != null) {
            AppBarLayout.LayoutParams params = (AppBarLayout.LayoutParams) linearLayout.getLayoutParams();
            if(isCanScroll) {
//                params.setScrollFlags(AppBarLayout.LayoutParams.SCROLL_FLAG_SCROLL | AppBarLayout.LayoutParams.SCROLL_FLAG_ENTER_ALWAYS);//0不能伸缩
                params.setScrollFlags(AppBarLayout.LayoutParams.SCROLL_FLAG_SCROLL | AppBarLayout.LayoutParams.SCROLL_FLAG_SNAP);//0不能伸缩
            } else {
//                params.setScrollFlags(0);//0不能伸缩
            }
            linearLayout.setLayoutParams(params);
        }
    }

}
