package com.vacash.android.customviews;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;

import androidx.viewpager.widget.ViewPager;

public class CustomViewPager extends ViewPager {

    public CustomViewPager(Context context) {
        super(context);
    }

    public CustomViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        // Disable swiping by returning false
        return false;
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent event) {
        // Disable intercepting touch events by returning false
        return false;
    }

}
