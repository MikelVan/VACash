package com.vacash.android;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;

import com.google.android.material.tabs.TabLayout;
import com.vacash.android.adapters.CarouselAdapter;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class Carousel extends AppCompatActivity {

    ViewPager viewPager;
    TabLayout tabLayout;
    List<List<String>> stringList;
    List<String> imageTop;
    List<String> imageMiddle;
    List<String> imageBottom;


    //test
    CarouselAdapter carouselAdapter;
    ViewPager carouselContainer;
    int[] testimages = {R.drawable.animal_crossing};
    //test


//    List<String> string2List;

//    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_carousel);

        viewPager = findViewById(R.id.viewPager);
        tabLayout = findViewById(R.id.tabs);

        tabLayout.setupWithViewPager(viewPager);

        stringList = new ArrayList<>();
//        stringList.add("https://images.pexels.com/photos/257360/pexels-photo-257360.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=1");
//        stringList.add("https://images.pexels.com/photos/397096/pexels-photo-397096.jpeg?auto=compress&cs=tinysrgb&w=1600");
//        stringList.add("https://images.pexels.com/photos/547116/pexels-photo-547116.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=1");

        imageBottom = new ArrayList<>();
        imageBottom.add("https://images.pexels.com/photos/257360/pexels-photo-257360.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=1");
        imageBottom.add("https://images.pexels.com/photos/397096/pexels-photo-397096.jpeg?auto=compress&cs=tinysrgb&w=1600");
        imageBottom.add("https://images.pexels.com/photos/547116/pexels-photo-547116.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=1");

        imageMiddle = new ArrayList<>();
        imageMiddle.add("https://w7.pngwing.com/pngs/114/579/png-transparent-pink-cross-stroke-ink-brush-pen-red-ink-brush-ink-leave-the-material-text.png");
        imageMiddle.add("https://www.pngmart.com/files/22/Background-PNG-HD-Isolated.png");
        imageMiddle.add("https://static.vecteezy.com/system/resources/thumbnails/020/047/437/small/black-grunge-brush-isolated-png.png");

        imageTop = new ArrayList<>();
        imageTop.add("https://storage.googleapis.com/proudcity/mebanenc/uploads/2021/03/placeholder-image.png");
        imageTop.add("https://susos.com/wp-content/uploads/2020/04/placeholder-image-png-1.png");
        imageTop.add("https://static.vecteezy.com/system/resources/thumbnails/020/047/437/small/black-grunge-brush-isolated-png.png");

//        string2List = new ArrayList<>();
//        string2List.add("https://w7.pngwing.com/pngs/114/579/png-transparent-pink-cross-stroke-ink-brush-pen-red-ink-brush-ink-leave-the-material-text.png");
//        string2List.add("https://www.pngmart.com/files/22/Background-PNG-HD-Isolated.png");
//        string2List.add("https://static.vecteezy.com/system/resources/thumbnails/020/047/437/small/black-grunge-brush-isolated-png.png");

        stringList.add(imageTop);
        stringList.add(imageMiddle);
        stringList.add(imageBottom);

        // tuk liat string list ada apa kaga
//        for (List<String> imageUrls : stringList) {
//            for (String url : imageUrls) {
//                Log.d("CarouselActivity", "ImageUrl: " + url);
//            }
//        }
//
//        Log.d("CarouselActivity", "Total Items in stringList: " + stringList.size());

        viewPager.setAdapter(new CarouselAdapter(this, stringList));

//        carouselAdapter = new CarouselAdapter(this, stringList);
//        carouselContainer.setAdapter(carouselAdapter);


//        viewPager.setAdapter(new CarouselAdapter.CarouselAdapter2(this, string2List));

        autoImageSlide();
    }

    private void autoImageSlide() {
        final long DELAY_MS = 5000;
        final long PERIOD_MS = 5000;

        final Handler handler = new Handler();
        final Runnable runnable = new Runnable() {
            @Override
            public void run() {
                if(viewPager.getCurrentItem() == stringList.size()-1) {
                    viewPager.setCurrentItem(0);
                } else {
                    viewPager.setCurrentItem(viewPager.getCurrentItem() + 1, true);
                }
            }
        };

        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                handler.post(runnable);
            }
        }, DELAY_MS, PERIOD_MS);
    }
}