package com.vacash.android.page_transformers;

import android.view.View;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.ViewPager;
import androidx.viewpager2.widget.ViewPager2;

public class FadePageTransform implements ViewPager2.PageTransformer {

    @Override
    public void transformPage(@NonNull View view, float position) {
        if (position < -1) {
            view.setAlpha(0f);

        } else if (position <= 0) {
            view.setAlpha(1f);

        } else if (position <= 1) {
            view.setAlpha(1 - position);
        } else {
            view.setAlpha(0f);
        }
    }

}
