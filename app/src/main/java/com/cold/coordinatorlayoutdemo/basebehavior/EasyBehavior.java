package com.cold.coordinatorlayoutdemo.basebehavior;

import android.content.Context;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/**
 * name: EasyBehavior
 * desc: 简单的实现TextView跟随Button的移动而移动
 * author:
 * date: 2018-07-10 10:00
 * remark:
 * 如果想还原，可以考虑从coordinatorlayout里面获取到被依赖的view，然后恢复view的位置，看看行不行
 */
public class EasyBehavior extends CoordinatorLayout.Behavior<TextView> {//这里的泛型是child的类型，也就是观察者View

  /**
   * 必须重写带双参的构造器，因为从xml反射需要调用
   * @param
   * @return
   */
  public EasyBehavior(Context context, AttributeSet attrs) {
    super(context, attrs);
  }

  /**
   *告知监听的dependency是Button
   * @param
   * @return
   */
  @Override
  public boolean layoutDependsOn(CoordinatorLayout parent, TextView child, View dependency) {
    return dependency instanceof Button;
  }

  /**
   * 当 dependency(Button)变化的时候，可以对child(TextView)进行操作
   * @param
   * @return
   */
  @Override
  public boolean onDependentViewChanged(CoordinatorLayout parent, TextView child, View dependency) {
    child.setX(dependency.getX()+200);
    child.setY(dependency.getY()+200);
    child.setText(dependency.getX()+","+dependency.getY());

    return true;
  }
}
