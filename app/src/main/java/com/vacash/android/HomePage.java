package com.vacash.android;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import androidx.appcompat.widget.Toolbar;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.tabs.TabLayout;
import com.vacash.android.adapters.GamePlatformTabAdapter;
import com.vacash.android.models.User;

public class HomePage extends AppCompatActivity {

    User user;

    TabLayout gamePlatformTabLayout;
    ViewPager2 gamePlatformViewPager;
    GamePlatformTabAdapter gamePlatformTabAdapter;

    private RelativeLayout dropdownMenu;
    private LinearLayout dropdownList;
    private RelativeLayout ppHighlight;
    private Animation slideDownAnimation, slideUpAnimation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);

        dropdownMenu = findViewById(R.id.dropdownMenu);
        dropdownList = findViewById(R.id.dropdownList);
        ppHighlight = findViewById(R.id.ppHighlight);
        slideDownAnimation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.slidedown);
        slideUpAnimation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.slideup);

        gamePlatformTabLayout = findViewById(R.id.gamePlatformTabLayout);
        gamePlatformViewPager = findViewById(R.id.gamePlatformViewPager);
        gamePlatformTabAdapter = new GamePlatformTabAdapter(getSupportFragmentManager(), getLifecycle());

        gamePlatformViewPager.setUserInputEnabled(false);
        gamePlatformViewPager.setAdapter(gamePlatformTabAdapter);

        dropdownMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ppHighlight.getVisibility() == View.VISIBLE) {
                    ppHighlight.setVisibility(View.INVISIBLE);
                } else {
                    ppHighlight.setVisibility(View.VISIBLE);
                }
                if (dropdownList.getVisibility() == View.VISIBLE) {
                    dropdownList.startAnimation(slideUpAnimation);
                    dropdownList.setVisibility(View.INVISIBLE);
                } else {
                    dropdownList.setVisibility(View.VISIBLE);
                    dropdownList.startAnimation(slideDownAnimation);
                }
            }
        });
        gamePlatformTabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                gamePlatformViewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        gamePlatformViewPager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                gamePlatformTabLayout.selectTab(gamePlatformTabLayout.getTabAt(position));
            }
        });

        Intent loginActivity = getIntent();
        user = loginActivity.getParcelableExtra("userData");
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        Integer id = item.getItemId();

        if(id == R.id.profile){
            Intent profileActivity = new Intent(HomePage.this, ProfilePage.class);
            profileActivity.putExtra("userData", user);
            startActivity(profileActivity);
        } else if (id == R.id.logout) {
            Intent loginActivity = new Intent(HomePage.this, LoginPage.class);
            startActivity(loginActivity);
        }

        return true;
    }
}