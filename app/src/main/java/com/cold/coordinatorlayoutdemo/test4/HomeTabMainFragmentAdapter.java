package com.cold.coordinatorlayoutdemo.test4;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;

import com.cold.coordinatorlayoutdemo.nested3.MyFragment;

import java.util.List;

public class HomeTabMainFragmentAdapter extends CommonFragmentStateAdapter {

    private List<String> infoTabList;

    public HomeTabMainFragmentAdapter(FragmentActivity fragmentActivity, List<String> list) {
        super(fragmentActivity);
        infoTabList = list;
    }

    public HomeTabMainFragmentAdapter(FragmentManager fragmentManager, Lifecycle lifecycle, List<String> infoList) {
        super(fragmentManager, lifecycle);
        this.infoTabList = infoList;
    }

    @Override
    protected Fragment createMyFragment(int position) {
        Fragment fragment = null;
        if (infoTabList != null && position < infoTabList.size()) {
            String info = infoTabList.get(position);
            if (info != null) {
                if (position == 1) {
                    fragment = new TestSecondFragment4();
                } else {
                    fragment = new MyFragment();
                }
            }
        }
        return fragment;
    }

    @Override
    public int getItemCount() {
        return infoTabList != null ? infoTabList.size() : 0;
    }
}
