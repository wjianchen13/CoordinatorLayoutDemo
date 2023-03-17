package com.cold.coordinatorlayoutdemo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.cold.coordinatorlayoutdemo.appbarlayout.AppBarLayoutActivity;
import com.cold.coordinatorlayoutdemo.base.BaseActivity;
import com.cold.coordinatorlayoutdemo.basebehavior.BaseBehaviorActivity;
import com.cold.coordinatorlayoutdemo.collapse.CollapseActivity;
import com.cold.coordinatorlayoutdemo.depend.DependActivity;
import com.cold.coordinatorlayoutdemo.fanal.FinalActivity;
import com.cold.coordinatorlayoutdemo.mix.MixActivity;
import com.cold.coordinatorlayoutdemo.nested.NestedActivity;
import com.cold.coordinatorlayoutdemo.nested1.NestedActivity1;
import com.cold.coordinatorlayoutdemo.nested2.NestedActivity2;
import com.cold.coordinatorlayoutdemo.nested3.NestedActivity3;
import com.cold.coordinatorlayoutdemo.project.ProjectActivity;
import com.cold.coordinatorlayoutdemo.stickyviewpager.StickyActivity;
import com.cold.coordinatorlayoutdemo.test.TestActivity;
import com.cold.coordinatorlayoutdemo.userinfo.UserActivity;
import com.cold.coordinatorlayoutdemo.viewpager.ViewPagerParallaxSnap;

public class MainActivity extends AppCompatActivity {

    private View mView;
    private TextView tvTest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tvTest = findViewById(R.id.tv_test);
    }

    public void onMix(View v) {
        startActivity(new Intent(this, MixActivity.class));
    }

    public void onNested(View v) {
        startActivity(new Intent(this, NestedActivity.class));
    }

    public void onNested1(View v) {
        startActivity(new Intent(this, NestedActivity1.class));
    }

    public void onNested2(View v) {
        startActivity(new Intent(this, NestedActivity2.class));
    }

    public void onNested3(View v) {
        startActivity(new Intent(this, NestedActivity3.class));
    }

    public void onAppBar(View v) {
        startActivity(new Intent(this, AppBarLayoutActivity.class));
    }

    public void onFinal(View v) {
        startActivity(new Intent(this, FinalActivity.class));
    }

    /**
     * 基础使用
     * @param
     * @return
     */
    public void onTest(View v) {
        Intent it1 = new Intent();
        it1.setClass(MainActivity.this, ViewPagerParallaxSnap.class);
        startActivity(it1);
    }

    /**
     * 基础使用
     * @param
     * @return
     */
    public void onTest1(View v) {
        Intent it1 = new Intent();
        it1.setClass(MainActivity.this, TestActivity.class);
        startActivity(it1);
    }

    /**
     * base behavior
     * @param
     * @return
     */
    public void onBaseBehavior(View v) {
        Intent it1 = new Intent();
        it1.setClass(MainActivity.this, BaseBehaviorActivity.class);
        startActivity(it1);
    }

    /**
     * 粘性viewpager,使用的是系统提供的CollapsingToolbarLayout
     * @param
     * @return
     */
    public void onSticky(View v) {
        Intent it1 = new Intent();
        it1.setClass(MainActivity.this, StickyActivity.class);
        startActivity(it1);
    }

    /**
     * 基础测试
     * @param
     * @return
     */
    public void onBase(View v) {
        Intent it1 = new Intent();
        it1.setClass(MainActivity.this, BaseActivity.class);
        startActivity(it1);
    }

    /**
     * 基础测试
     * @param
     * @return
     */
    public void onProject(View v) {
        Intent it1 = new Intent();
        it1.setClass(MainActivity.this, ProjectActivity.class);
        startActivity(it1);
    }


    /**
     * 个人资料demo
     * @param
     * @return
     */
    public void onUser(View v) {
        Intent it1 = new Intent();
        it1.setClass(MainActivity.this, UserActivity.class);
        startActivity(it1);
    }

    /**
     * 通过CollapsingToolbarLayout实现
     * @param
     * @return
     */
    public void onCollapse(View v) {
        Intent it1 = new Intent();
        it1.setClass(MainActivity.this, CollapseActivity.class);
        startActivity(it1);
    }
    
    public void onGet(View v) {
        mView = LayoutInflater.from(this).inflate(R.layout.view_main_pager_title_view, null);
        getTextViewHeight(mView);
    }


    private int getTextViewHeight(View v) {
        int spec = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);
        v.measure(spec,spec);
        v.layout(0, 0, v.getMeasuredWidth(), v.getMeasuredHeight());
        tvTest.setText("width: " + v.getMeasuredWidth() + "  height: " + v.getMeasuredHeight());
        return v.getMeasuredHeight();
    }

    /**
     * View 依赖
     * @param
     * @return
     */
    public void onDepend(View v) {
        startActivity(new Intent(this, DependActivity.class));
    }
}
