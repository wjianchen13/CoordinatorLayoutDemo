package com.cold.coordinatorlayoutdemo.test4;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.cold.coordinatorlayoutdemo.R;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;

import java.util.ArrayList;

public class TestFirstFragment4 extends BaseFragment {

    private View mRootView;
    private RecyclerView recyclerView;
    private SmartRefreshLayout smartRefreshLayout;
    private RecyclerAdapter recyclerAdapter;

    private ArrayList<String> mData;


    @Override

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mRootView = inflater.inflate(R.layout.fragment_test_first4,container,false);
        recyclerView= (RecyclerView) mRootView.findViewById(R.id.recyclerView);
        smartRefreshLayout = mRootView.findViewById(R.id.smart_refresh_layout);
        // 禁用 Fragment 层的下拉刷新，由 Activity 统一负责；只开启上拉加载更多
        smartRefreshLayout.setEnableRefresh(false);
        smartRefreshLayout.setEnableLoadMore(true);
        smartRefreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull final RefreshLayout refreshLayout) {
                mRootView.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        mData.add("222222222222222222");
                        mData.add("222222222222222222");
                        mData.add("222222222222222222");
                        mData.add("222222222222222222");
                        mData.add("222222222222222222");
                        recyclerAdapter.notifyItemRangeInserted(mData.size() - 5,5);
                        smartRefreshLayout.finishLoadMore();
                    }
                }, 2000);
            }
        });

        smartRefreshLayout.setDisableContentWhenRefresh(true);
        smartRefreshLayout.setDisableContentWhenLoading(true);
        mData = new ArrayList<>();
        for (int i = 0; i < 12; i++) {
            mData.add("111111111111111111111");
        }
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerAdapter=new RecyclerAdapter(getActivity(),mData);
        recyclerView.setAdapter(recyclerAdapter);
        return mRootView;
    }

    /**
     * Activity 触发下拉刷新时调用此方法，Fragment 在这里执行自己的数据刷新逻辑。
     * 完成后必须调用 activityRefreshLayout.finishRefresh() 来结束 Activity 层的刷新动画。
     */
    @Override
    public void onActivityRefresh(final SmartRefreshLayout activityRefreshLayout) {
        if (mRootView == null) {
            if (activityRefreshLayout != null) activityRefreshLayout.finishRefresh();
            return;
        }
        mRootView.postDelayed(new Runnable() {
            @Override
            public void run() {
                mData.clear();
                for (int i = 0; i < 8; i++) {
                    mData.add("3333333333333333333333333");
                }
                recyclerAdapter.notifyDataSetChanged();
                // 刷新后重置上拉加载更多的状态
                if (smartRefreshLayout != null) smartRefreshLayout.resetNoMoreData();
                // 通知 Activity 层刷新动画结束
                if (activityRefreshLayout != null) {
                    activityRefreshLayout.finishRefresh();
                }
            }
        }, 1200);
    }
}
