package com.vacash.android.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.bumptech.glide.Glide;
import com.vacash.android.R;

import java.util.List;

public class CarouselAdapter extends PagerAdapter {

    Context context;
    List<List<String>> imageListCarousel;
//    List<String> imageListCarousel;

    public CarouselAdapter(Context context, List<List<String>> imageListCarousel) {
        this.context = context;
        this.imageListCarousel = imageListCarousel;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View view = layoutInflater.inflate(R.layout.image_list_carousel, container, false);
//        final ImageView imageInCarousel = view.findViewById(R.id.imageCarousel);
        final ImageView imageInCarouselBottom = view.findViewById(R.id.imageViewBottom);
        final ImageView imageInCarouselMiddle = view.findViewById(R.id.imageViewMiddle);
        final ImageView imageInCarouselTop = view.findViewById(R.id.imageViewTop);

        List<String> imagesForCurrentItem = imageListCarousel.get(position);

//        Glide.with(context)
//                .load(imageListCarousel .get(position))
//                .into(imageInCarousel);

        Glide.with(context)
                .load(imagesForCurrentItem.get(0))
                .into(imageInCarouselBottom);

        Glide.with(context)
                .load(imagesForCurrentItem.get(1))
                .into(imageInCarouselMiddle);

        Glide.with(context)
                .load(imagesForCurrentItem.get(2))
                .into(imageInCarouselTop);

        container.addView(view);
        return view;
    }
    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }
    @Override
    public int getCount() {
        return imageListCarousel.size();
//        return imageListCarousel.length;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }


}

