package com.vacash.android;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.tabs.TabLayout;
import com.vacash.android.adapters.GamePlatformTabAdapter;
import com.vacash.android.models.PurchaseHistory;
import com.vacash.android.models.User;

import java.util.ArrayList;

public class HomePage extends AppCompatActivity {

    User user;

    TabLayout gamePlatformTabLayout;
    ViewPager2 gamePlatformViewPager;
    GamePlatformTabAdapter gamePlatformTabAdapter;

    private RelativeLayout dropdownMenu;
    private LinearLayout dropdownList;
    private RelativeLayout ppHighlight;
    private RelativeLayout dark_overlay;
    private LinearLayout checkProfileButton;
    private LinearLayout logoutButton;
    private Animation slideDownAnimation, slideUpAnimation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);

        dropdownMenu = findViewById(R.id.dropdownMenu);
        dropdownList = findViewById(R.id.dropdownList);
        ppHighlight = findViewById(R.id.ppHighlight);
        dark_overlay = findViewById(R.id.dark_overlay);
        checkProfileButton = findViewById(R.id.checkProfileButton);
        logoutButton = findViewById(R.id.logoutButton);
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
                if (dark_overlay.getVisibility() == View.VISIBLE) {
                    dark_overlay.setVisibility(View.INVISIBLE);
                } else {
                    dark_overlay.setVisibility(View.VISIBLE);
                }
            }
        });

        checkProfileButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent profileActivity = new Intent(HomePage.this, ProfilePage.class);
//                profileActivity.putExtra("userData", user);
                startActivity(profileActivity);
            }
        });

        logoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent loginActivity = new Intent(HomePage.this, LoginPage.class);
                startActivity(loginActivity);
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