package com.cold.coordinatorlayoutdemo.test3;

import androidx.fragment.app.Fragment;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;

public class BaseFragment extends Fragment {

    protected String mTitle;

    public void setmTitle(String mTitle) {
        this.mTitle = mTitle;
    }

    public String getmTitle() {
        return mTitle;
    }

    /**
     * Activity 级别下拉刷新时回调给当前 Fragment。
     * 子类重写此方法处理自己的数据刷新逻辑，完成后必须调用 activityRefreshLayout.finishRefresh()
     * 结束 Activity 层的刷新动画。
     *
     * @param activityRefreshLayout Activity 层的 SmartRefreshLayout，用于结束刷新动画
     */
    public void onActivityRefresh(SmartRefreshLayout activityRefreshLayout) {
        // 默认实现：直接结束刷新动画，子类按需重写
        if (activityRefreshLayout != null) {
            activityRefreshLayout.finishRefresh();
        }
    }
}
