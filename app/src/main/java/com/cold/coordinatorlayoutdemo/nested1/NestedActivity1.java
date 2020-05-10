package com.cold.coordinatorlayoutdemo.nested1;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.core.widget.NestedScrollView;

import com.cold.coordinatorlayoutdemo.R;
import com.google.android.material.appbar.AppBarLayout;

public class NestedActivity1 extends AppCompatActivity implements View.OnClickListener{

    private AppBarLayout appBarLayout;

    private NestedScrollView scrollView;

//    MapView mapView;

    private TextView tvTest;

    private CoordinatorLayout.LayoutParams layoutParams;
//    private AMap aMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nested1);
//        ButterKnife.bind(this);
//        mapView.onCreate(savedInstanceState);
//        if (aMap == null) {
//            aMap = mapView.getMap();
//        }
        appBarLayout = findViewById(R.id.appBar);
        scrollView = findViewById(R.id.nestScrollView);
        tvTest = findViewById(R.id.jumpUrl);
        tvTest.setOnClickListener(this);

        layoutParams = (CoordinatorLayout.LayoutParams) scrollView.getLayoutParams();
        layoutParams.setMargins(30, 0, 30, 0);
        scrollView.setLayoutParams(layoutParams);
        appBarLayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int i) {
                float a = (float) 30 / appBarLayout.getTotalScrollRange();
                int side = (int) Math.rint(a * i + 30);
                layoutParams.setMargins(side, 0, side, 0);
                scrollView.setLayoutParams(layoutParams);
                if (Math.abs(i) > 0) {
                    float alpha = (float) Math.abs(i) / appBarLayout.getTotalScrollRange();
                    appBarLayout.setAlpha(alpha);
                    scrollView.getBackground().mutate().setAlpha(Math.round(alpha * 255));
                } else {
                    appBarLayout.setAlpha(0);
                    scrollView.getBackground().mutate().setAlpha(0);
                }
            }
        });
    }


    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.jumpUrl:
                Intent intent = new Intent();
                intent.setAction("android.intent.action.VIEW");
                Uri content_url = Uri.parse("https://shop504682254.taobao.com/shop/view_shop.htm?tracelog=twddp&amp;user_number_id=2541121532");
                intent.setData(content_url);
                startActivity(intent);
                break;
            default:
                break;
        }

    }
}
