package com.vacash.android;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.fragment.app.FragmentContainerView;

import com.vacash.android.models.User;

import java.text.NumberFormat;
import java.util.Locale;

public class HomePage extends AppCompatActivity {

    User user;

    private TextView mobileTab, pcTab, consoleTab, userBalance;
//    private final int selectedTabId = 1;
    Integer tab_id = 1;
    String tab_title = "Mobile";
    private FragmentContainerView gamePlatformFirstTabLayout, gamePlatformSecondTabLayout;
    Integer activatedFragment = 1;
    private RelativeLayout action_bar, dropdownMenu, ppHighlight, dark_overlay;
    private LinearLayout dropdownList, checkProfileButton, logoutButton;
    private ImageView appLogoActionBar;
    private Animation slideDownAnimation, slideUpAnimation, fadeInAnimation, fadeOutAnimation;
    private Boolean dropDownActivated;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);

        action_bar = findViewById(R.id.actionBar);
        dropdownMenu = findViewById(R.id.dropdownMenu);
        dropdownList = findViewById(R.id.dropdownList);
        ppHighlight = findViewById(R.id.ppHighlight);
        dark_overlay = findViewById(R.id.dark_overlay);
        checkProfileButton = findViewById(R.id.checkProfileButton);
        logoutButton = findViewById(R.id.logoutButton);
        appLogoActionBar = findViewById(R.id.appLogoActionBar);
        userBalance = findViewById(R.id.balance);
        slideDownAnimation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.slidedown);
        slideUpAnimation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.slideup);
        fadeInAnimation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fadein);
        fadeOutAnimation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fadeout);

        slideDownAnimation.setInterpolator(new AccelerateDecelerateInterpolator());
        slideUpAnimation.setInterpolator(new AccelerateDecelerateInterpolator());

        action_bar.bringToFront();

        Intent loginActivity = getIntent();
        user = loginActivity.getParcelableExtra("userData");

        userBalance.setText(toCurrencyString(user.getBalance()));

        dark_overlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dark_overlay.setEnabled(false);

                ppHighlight.startAnimation(fadeOutAnimation);
                ppHighlight.setVisibility(View.INVISIBLE);

                if (dropdownList.getVisibility() == View.VISIBLE) {
                    dropdownList.startAnimation(slideUpAnimation);
                    dropdownList.setVisibility(View.INVISIBLE);
                }

                dark_overlay.startAnimation(fadeOutAnimation);
                dark_overlay.setVisibility(View.INVISIBLE);

                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        dark_overlay.setEnabled(true);
                    }
                },550);
            }
        });

        dropdownMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ppHighlight.getVisibility() == View.VISIBLE) {
                    ppHighlight.startAnimation(fadeOutAnimation);
                    ppHighlight.setVisibility(View.INVISIBLE);
                } else {
                    ppHighlight.setVisibility(View.VISIBLE);
                    ppHighlight.startAnimation(fadeInAnimation);
                }

                if (dropdownList.getVisibility() == View.VISIBLE) {
                    dropdownList.startAnimation(slideUpAnimation);
                    dropdownList.setVisibility(View.INVISIBLE);
                } else {
                    dropdownList.setVisibility(View.VISIBLE);
                    dropdownList.startAnimation(slideDownAnimation);
                }

                if (dark_overlay.getVisibility() == View.VISIBLE) {
                    dark_overlay.startAnimation(fadeOutAnimation);
                    dark_overlay.setVisibility(View.INVISIBLE);
                } else {
                    dark_overlay.setVisibility(View.VISIBLE);
                    dark_overlay.startAnimation(fadeInAnimation);
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

        gamePlatformFirstTabLayout = findViewById(R.id.gamePlatformFirstFragment);
        gamePlatformSecondTabLayout = findViewById(R.id.gamePlatformSecondFragment);

        getSupportFragmentManager().beginTransaction()
                .setReorderingAllowed(true)
                .replace(R.id.gamePlatformFirstFragment, GameTab.newInstance(tab_id, tab_title), null)
                .commit();

        mobileTab = findViewById(R.id.mobile_tab);
        pcTab = findViewById(R.id.pc_tab);
        consoleTab = findViewById(R.id.console_tab);

        gamePlatformSecondTabLayout.setAlpha(0.0f);

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

    private void toggleDropDown(){

    }

    private String toCurrencyString(Integer value){
        return NumberFormat.getCurrencyInstance(new Locale("id", "ID")).format(value).replace("Rp", "");
    }

    private void selectTab(int position) {
        switch (position) {
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
        }

        switch (activatedFragment) {
            case 1:
                gamePlatformFirstTabLayout.animate().alpha(0.0f).setDuration(500);

                getSupportFragmentManager().beginTransaction()
                        .setReorderingAllowed(true)
                        .replace(R.id.gamePlatformSecondFragment, GameTab.newInstance(tab_id, tab_title), null)
                        .commit();

                gamePlatformSecondTabLayout.bringToFront();
                gamePlatformSecondTabLayout.animate().alpha(1.0f).setDuration(500);

                activatedFragment = 2;
                break;
            case 2:
                gamePlatformSecondTabLayout.animate().alpha(0.0f).setDuration(500);

                getSupportFragmentManager().beginTransaction()
                        .setReorderingAllowed(true)
                        .replace(R.id.gamePlatformFirstFragment, GameTab.newInstance(tab_id, tab_title), null)
                        .commit();

                gamePlatformFirstTabLayout.bringToFront();
                gamePlatformFirstTabLayout.animate().alpha(1.0f).setDuration(500);

                activatedFragment = 1;
        }

    }

    private void changeTabWidth(TextView tabView, int width) {
        LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) tabView.getLayoutParams();
        params.width = width;
        tabView.setLayoutParams(params);
    }

    private void changeTabStyle(TextView selectedTabView, TextView unselectedTabView1, TextView unselectedTabView2) {
        selectedTabView.setAlpha(1.0f);
        changeTabWidth(selectedTabView, dpToPx(150));

        unselectedTabView1.setAlpha(0.3f);
        changeTabWidth(unselectedTabView1, dpToPx(75));

        unselectedTabView2.setAlpha(0.3f);
        changeTabWidth(unselectedTabView2, dpToPx(75));
    }

    private int dpToPx(float dp) {
        float density = getApplicationContext().getResources().getDisplayMetrics().density;
        return Math.round(dp * density);
    }

}