package com.cold.coordinatorlayoutdemo.depend;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.TextView;

import androidx.coordinatorlayout.widget.CoordinatorLayout;

public class MyBehavior extends CoordinatorLayout.Behavior <View>{

    public MyBehavior() {
        super();
    }

    public MyBehavior(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean layoutDependsOn(CoordinatorLayout parent, View child, View dependency) {
        return dependency instanceof DependencyView;
    }

    @Override
    public boolean onDependentViewChanged(CoordinatorLayout parent, View child, View dependency) {
        float x = child.getX();
        float y = child.getY();

        int dependTop= dependency.getTop();
        int dependBottom = dependency.getBottom();

        x = dependency.getX();

        if ( child instanceof TextView) {
            y = dependTop - child.getHeight() - 20;
        } else {
            y = dependBottom + 50;
        }


        child.setX(x);
        child.setY(y);

        return true;
    }

}
