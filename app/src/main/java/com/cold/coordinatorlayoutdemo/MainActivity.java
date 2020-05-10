package com.cold.coordinatorlayoutdemo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.cold.coordinatorlayoutdemo.appbarlayout.AppBarLayoutActivity;
import com.cold.coordinatorlayoutdemo.fanal.FinalActivity;
import com.cold.coordinatorlayoutdemo.mix.TestActivity3;
import com.cold.coordinatorlayoutdemo.nested.NestedActivity;
import com.cold.coordinatorlayoutdemo.nested1.NestedActivity1;
import com.cold.coordinatorlayoutdemo.nested2.NestedActivity2;
import com.cold.coordinatorlayoutdemo.nested3.NestedActivity3;

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
        startActivity(new Intent(this, TestActivity3.class));
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
}
