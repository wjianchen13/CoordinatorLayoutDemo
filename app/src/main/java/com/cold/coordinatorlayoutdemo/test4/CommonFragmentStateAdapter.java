package com.cold.coordinatorlayoutdemo.test4;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;

/**
 * FragmentStateAdapter 中 重写 getItemId 一定要重写 containsItem()
 * https://blog.csdn.net/richardli1228/article/details/124469192
 */
public abstract class CommonFragmentStateAdapter extends FragmentStateAdapter {
    private java.util.Set<Long> mBaseIds = new java.util.HashSet<>();

    public CommonFragmentStateAdapter(FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    public CommonFragmentStateAdapter(Fragment fragment) {
        super(fragment);
    }

    public CommonFragmentStateAdapter(FragmentManager fragmentManager, Lifecycle lifecycle) {
        super(fragmentManager, lifecycle);
    }

    @Override
    public boolean containsItem(long itemId) {
        return mBaseIds != null && mBaseIds.contains(itemId);
    }

    @Override
    public Fragment createFragment(int position) {
        if (mBaseIds == null)
            mBaseIds = new java.util.HashSet<>();
        mBaseIds.add((long) position);
        return createMyFragment(position);
    }

    protected abstract Fragment createMyFragment(int position);

    @Override
    public int getItemCount() {
        return 0;
    }

    /**
     * 这两个方法必须重写，作为数据改变刷新检测的工具
     *
     * @param position
     * @return
     */
    @Override
    public long getItemId(int position) {
        return (long) position;
    }
}
