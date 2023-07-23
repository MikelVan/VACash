package com.vacash.android;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.animation.Animator;
import android.content.Intent;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;

import com.vacash.android.models.User;

public class HomePage extends AppCompatActivity {

    User user;

    private TextView mobileTab, pcTab, consoleTab;
    private int selectedTabId = 1;
    Integer tab_id = 1;
    String tab_title = "Mobile";
    private ConstraintLayout gamePlatformTabLayout;
    private RelativeLayout action_bar, dropdownMenu, ppHighlight, dark_overlay;
    private LinearLayout dropdownList, checkProfileButton, logoutButton;
    private ImageView appLogoActionBar;
    private Animation slideDownAnimation, slideUpAnimation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);

        action_bar = findViewById(R.id.action_bar);
        dropdownMenu = findViewById(R.id.dropdownMenu);
        dropdownList = findViewById(R.id.dropdownList);
        ppHighlight = findViewById(R.id.ppHighlight);
        dark_overlay = findViewById(R.id.dark_overlay);
        checkProfileButton = findViewById(R.id.checkProfileButton);
        logoutButton = findViewById(R.id.logoutButton);
        appLogoActionBar = findViewById(R.id.appLogoActionBar);
        slideDownAnimation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.slidedown);
        slideUpAnimation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.slideup);

        Intent loginActivity = getIntent();
        user = loginActivity.getParcelableExtra("userData");

        dropdownMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                action_bar.bringToFront();
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
                profileActivity.putExtra("userData", user);
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

        appLogoActionBar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent homeActivity = new Intent(HomePage.this, HomePage.class);
                homeActivity.putExtra("userData", user);
                startActivity(homeActivity);
            }
        });

        mobileTab = findViewById(R.id.mobile_tab);
        pcTab = findViewById(R.id.pc_tab);
        consoleTab = findViewById(R.id.console_tab);
        gamePlatformTabLayout = findViewById(R.id.gamePlatformTabLayout);

        getSupportFragmentManager().beginTransaction()
                .setReorderingAllowed(true)
                .replace(R.id.gamePlatformFragmentView, GameTab.newInstance(tab_id, tab_title), null)
                .commit();

        mobileTab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectTab(1);
            }
        });

        pcTab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectTab(2);
            }
        });

        consoleTab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectTab(3);
            }
        });
    }

    private void selectTab(int position){

        gamePlatformTabLayout.animate().alpha(0.0f).setDuration(500).setListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(@NonNull Animator animation) {

            }

            @Override
            public void onAnimationEnd(@NonNull Animator animation) {
                switch (position){
                    case 1:
                        tab_id = 1;
                        tab_title = "Mobile";
                        changeTabStyle(mobileTab, pcTab, consoleTab);
                        break;
                    case 2:
                        tab_id = 2;
                        tab_title = "PC";
                        changeTabStyle(pcTab, mobileTab, consoleTab);
                        break;
                    case 3:
                        tab_id = 3;
                        tab_title = "Console";
                        changeTabStyle(consoleTab, pcTab, mobileTab);
                        break;
                }

                getSupportFragmentManager().beginTransaction()
                        .setReorderingAllowed(true)
                        .replace(R.id.gamePlatformFragmentView, GameTab.newInstance(tab_id, tab_title), null)
                        .commit();

                gamePlatformTabLayout.animate().alpha(1.0f).setDuration(500);
            }

            @Override
            public void onAnimationCancel(@NonNull Animator animation) {

            }

            @Override
            public void onAnimationRepeat(@NonNull Animator animation) {

            }
        });

    }

    private void changeTabWidth(TextView tabView, int width){
        LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) tabView.getLayoutParams();
        params.width = width;
        tabView.setLayoutParams(params);
    }

    private void changeTabStyle(TextView selectedTabView, TextView unselectedTabView1, TextView unselectedTabView2){
        selectedTabView.setAlpha(1.0f);
        changeTabWidth(selectedTabView, dpToPx(160));

        unselectedTabView1.setAlpha(0.3f);
        changeTabWidth(unselectedTabView1, dpToPx(85));

        unselectedTabView2.setAlpha(0.3f);
        changeTabWidth(unselectedTabView2, dpToPx(85));
    }

    private int dpToPx(float dp) {
        float density = getApplicationContext().getResources().getDisplayMetrics().density;
        return Math.round(dp * density);
    }

}