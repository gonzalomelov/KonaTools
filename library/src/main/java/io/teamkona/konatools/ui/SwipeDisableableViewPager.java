package io.teamkona.konatools.ui;

import android.content.Context;
import android.content.res.TypedArray;
import android.databinding.BindingAdapter;
import android.databinding.BindingMethod;
import android.databinding.BindingMethods;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import io.teamkona.konatools.R;

/**
 * Created by gonzalomelov on 11/2/15.
 **/
@BindingMethods({
    @BindingMethod(type = SwipeDisableableViewPager.class,
        attribute = "app:swipePageChangeEnabled",
        method = "setSwipePageChangeEnabled")
}) public class SwipeDisableableViewPager extends ViewPager {

  private boolean swipePageChangeEnabled;

  public SwipeDisableableViewPager(Context context, AttributeSet attrs) {
    super(context, attrs);

    TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.SwipeDisableableViewPagerOptions, 0, 0);
    this.swipePageChangeEnabled = a.getBoolean(R.styleable.SwipeDisableableViewPagerOptions_swipePageChangeEnabled, true);
    a.recycle();
  }

  @BindingAdapter("app:swipePageChangeEnabled") public static void setSwipePageChangeEnabled(View view, boolean swipePageChangeEnabled) {
    ((SwipeDisableableViewPager) view).swipePageChangeEnabled = swipePageChangeEnabled;
  }

  @Override public boolean onTouchEvent(MotionEvent event) {
    return this.swipePageChangeEnabled && super.onTouchEvent(event);
  }

  @Override public boolean onInterceptTouchEvent(MotionEvent event) {
    return this.swipePageChangeEnabled && super.onInterceptTouchEvent(event);
  }

  public void setPagingEnabled(boolean swipePageChangeEnabled) {
    this.swipePageChangeEnabled = swipePageChangeEnabled;
  }
}
