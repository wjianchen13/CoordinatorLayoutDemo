package com.cold.coordinatorlayoutdemo.nested3;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class BaseFragment extends Fragment {

    protected String mTitle;

    public void setmTitle(String mTitle) {
        this.mTitle = mTitle;
    }

    public String getmTitle() {
        return mTitle;
    }
}
