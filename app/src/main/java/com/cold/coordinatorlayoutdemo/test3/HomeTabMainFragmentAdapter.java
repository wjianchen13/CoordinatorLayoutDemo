package com.cold.coordinatorlayoutdemo.test3;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;

import java.util.List;

//public class HomeTabMainFragmentAdapter extends CommonFragmentStateAdapter {
//    private List<FrameCoreTabInnfo> infoTabList;
//
//    public HomeTabMainFragmentAdapter(FragmentActivity fragmentActivity, List<FrameCoreTabInnfo> list) {
//        super(fragmentActivity);
//        infoTabList = list;
//    }
//
//    public HomeTabMainFragmentAdapter(FragmentManager fragmentManager, Lifecycle lifecycle, List<FrameCoreTabInnfo> infoList) {
//        super(fragmentManager, lifecycle);
//        this.infoTabList = infoList;
//    }
//
//    @Override
//    protected Fragment createMyFragment(int position) {
//        Fragment fragment = null;
//        if (CommonObjectUtils.isBaseNotEmpty(infoTabList) && position < infoTabList.size()) {
//            FrameCoreTabInnfo info = infoTabList.get(position);
//            if (info != null) {
//                if (info.id == ApccoClientConstantConfig.HOME.CONSTANTS_CATEGORY_MY) {
//                    fragment = ApeomsHomeMyMvpFragmont.newInstance(info.id, position, getBaseMvpPresenter() != null ? getBaseMvpPresenter().mInitPosition : 0);
//                } else {
//                    fragment = ApeomsHomePopularMvpFragmont.newInstance(info.id, position, getBaseMvpPresenter() != null ? getBaseMvpPresenter().mInitPosition : 0);
//                }
//            }
//        }
//        return fragment;
//    }
//
//    @Override
//    public int getItemCount() {
//        return infoTabList != null ? infoTabList.size() : 0;
//    }
//}
