package com.cold.coordinatorlayoutdemo.appbarlayout;

import android.os.Bundle;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.cold.coordinatorlayoutdemo.R;
import com.cold.coordinatorlayoutdemo.nested3.BaseFragment;
import com.cold.coordinatorlayoutdemo.nested3.CustomViewPager;
import com.google.android.material.tabs.TabLayout;

import java.util.List;


/**
 * AppBarLayout 继承自LinearLayout，布局方向为垂直方向。所以你可以把它当成垂直布局的LinearLayout来使用。
 * AppBarLayout是在LinearLayou上加了一些材料设计的概念，它可以让你定制当某个可滚动View的滚动手势发生变化时，
 * 其内部的子View实现何种动作。
 * 当某个ScrollView发生滚动时，你可以定制你的“顶部栏”应该执行哪些动作（如跟着一起滚动、保持不动等等）。
 * app:layout_behavior="@string/appbar_scrolling_view_behavior"
 * 这是一个系统behavior, 从字面意思就可以看到, 是为appbar设置滚动动作的一个behavior. 没有这个属性的话,
 * Appbar就是死的, 有了它就有了灵魂.
 * 我们可以通过给Appbar下的子View添加app:layout_scrollFlags来设置各子View执行的动作. scrollFlags可以设置的动作如下:
 * (1) scroll: 值设为scroll的View会跟随滚动事件一起发生移动。就是当指定的ScrollView发生滚动时，该View也跟随一起滚动，就好像这个View也是属于这个ScrollView一样。
 * (2) enterAlways: 值设为enterAlways的View,当任何时候ScrollView往下滚动时，该View会直接往下滚动。而不用考虑ScrollView是否在滚动到最顶部还是哪里.
 * (3) exitUntilCollapsed：值设为exitUntilCollapsed的View，当这个View要往上逐渐“消逝”时，会一直往上滑动，直到剩下的的高度达到它的最小高度后，再响应ScrollView的内部滑动事件。
 *
 *
 *
 *
 *
 */
public class AppBarLayoutActivity extends AppCompatActivity {
    private TabLayout tabLayout;
    private CustomViewPager viewPager;
    private List<BaseFragment> bodyFragments;
    private ImageView mHeaderIcon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_app_bar_layout);

    }
}

