package com.cold.coordinatorlayoutdemo.test3;

import androidx.fragment.app.Fragment;

public class BaseFragment extends Fragment {

    protected String mTitle;

    public void setmTitle(String mTitle) {
        this.mTitle = mTitle;
    }

    public String getmTitle() {
        return mTitle;
    }
}
