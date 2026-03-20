package com.cold.coordinatorlayoutdemo.test3;

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

public class MyFragment extends BaseFragment {

    private View mRootView;
    private RecyclerView recyclerView;
    private SmartRefreshLayout smartRefreshLayout;
    private RecyclerAdapter recyclerAdapter;
    private ArrayList<String> mData;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mRootView = inflater.inflate(R.layout.fragment, container, false);
        recyclerView = mRootView.findViewById(R.id.recyclerView);
        smartRefreshLayout = mRootView.findViewById(R.id.smart_refresh_layout);

        // 禁用下拉刷新，由 Activity 统一负责；只开启上拉加载更多
        smartRefreshLayout.setEnableRefresh(false);
        smartRefreshLayout.setEnableLoadMore(true);
        smartRefreshLayout.setDisableContentWhenLoading(true);

        smartRefreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                mRootView.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        mData.add("222222222222222222");
                        mData.add("222222222222222222");
                        mData.add("222222222222222222");
                        mData.add("222222222222222222");
                        mData.add("222222222222222222");
                        recyclerAdapter.notifyItemRangeInserted(mData.size() - 6, 5);
                        smartRefreshLayout.finishLoadMore();
                    }
                }, 2000);
            }
        });

        mData = new ArrayList<>();
        mData.add("111111111111111111111");
        mData.add("111111111111111111111");
        mData.add("111111111111111111111");
        mData.add("111111111111111111111");
        mData.add("111111111111111111111");
        mData.add("111111111111111111111");
        mData.add("111111111111111111111");
        mData.add("111111111111111111111");
        mData.add("111111111111111111111");
        mData.add("111111111111111111111");
        mData.add("111111111111111111111");
        mData.add("111111111111111111111");
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerAdapter = new RecyclerAdapter(getActivity(), mData);
        recyclerView.setAdapter(recyclerAdapter);

        return mRootView;
    }

    /**
     * Activity 下拉刷新时回调此方法，在这里执行数据刷新逻辑。
     * 完成后调用 activityRefreshLayout.finishRefresh() 结束 Activity 层的刷新动画。
     */
    @Override
    public void onActivityRefresh(SmartRefreshLayout activityRefreshLayout) {
        if (mRootView == null) {
            if (activityRefreshLayout != null) activityRefreshLayout.finishRefresh();
            return;
        }
        mRootView.postDelayed(new Runnable() {
            @Override
            public void run() {
                mData.clear();
                mData.add("3333333333333333333333333");
                mData.add("3333333333333333333333333");
                mData.add("3333333333333333333333333");
                mData.add("3333333333333333333333333");
                mData.add("3333333333333333333333333");
                mData.add("3333333333333333333333333");
                mData.add("3333333333333333333333333");
                mData.add("3333333333333333333333333");
                recyclerAdapter.notifyDataSetChanged();
                // 刷新后重置上拉加载更多的状态，避免显示"没有更多数据"
                smartRefreshLayout.resetNoMoreData();
                // 通知 Activity 层刷新动画结束
                if (activityRefreshLayout != null) {
                    activityRefreshLayout.finishRefresh();
                }
            }
        }, 2000);
    }
}
