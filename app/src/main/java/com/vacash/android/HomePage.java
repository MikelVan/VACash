package com.vacash.android;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.DragEvent;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.content.res.AppCompatResources;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.FragmentContainerView;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;
import com.vacash.android.adapters.CarouselAdapter;
import com.vacash.android.listeners.OnSwipeListener;
import com.vacash.android.models.User;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Timer;
import java.util.TimerTask;

public class HomePage extends AppCompatActivity {

    private User user;
    ArrayList<Integer> listOfBg, listOfText, listOfCharacter;
    private TextView mobileTab, pcTab, consoleTab, userBalance;
    Integer tab_id = 1;
    String tab_title = "Mobile";
    private FragmentContainerView gamePlatformFirstTabLayout, gamePlatformSecondTabLayout;
    Integer activatedFragment = 1;
    private View carouselBtn1, carouselBtn2, carouselBtn3;
    private ViewPager bgCarousel, textCarousel;
    private RelativeLayout action_bar, dropdownMenu, ppHighlight, dark_overlay;
    private LinearLayout dropdownList, checkProfileButton, logoutButton;
    private ConstraintLayout carouselLayout;
    private ImageView appLogoActionBar, characterCarousel;
    private Animation slideDownAnimation, slideUpAnimation, fadeInAnimation, fadeOutAnimation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);

        // setting action bar
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
                }, 550);
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

        // setting carousel
        carouselLayout = findViewById(R.id.carousel);
        bgCarousel = findViewById(R.id.backgroundCarousel);
        textCarousel = findViewById(R.id.textCarousel);
        characterCarousel = findViewById(R.id.characterCarousel);

        listOfBg = new ArrayList<>();
        listOfBg.add(R.drawable.carousel1_bg);
        listOfBg.add(R.drawable.carousel2_bg);
        listOfBg.add(R.drawable.carousel3_bg);

        bgCarousel.setAdapter(new CarouselAdapter(this, listOfBg));

        listOfText = new ArrayList<>();
        listOfText.add(R.drawable.carousel1_text);
        listOfText.add(R.drawable.carousel2_text);
        listOfText.add(R.drawable.carousel3_text);

        textCarousel.setAdapter(new CarouselAdapter(this, listOfText));

        listOfCharacter = new ArrayList<>();
        listOfCharacter.add(R.drawable.carousel1_character);
        listOfCharacter.add(R.drawable.carousel2_character);
        listOfCharacter.add(R.drawable.carousel3_character);

        final Handler handler = new Handler();
        final Runnable runnable = new Runnable() {
            @Override
            public void run() {
                int nextPosition = (bgCarousel.getCurrentItem() + 1) % listOfBg.size();

                bgCarousel.setCurrentItem(nextPosition);
                textCarousel.setCurrentItem(nextPosition);

                changeCharacter(nextPosition);
                changeActiveBullet(nextPosition);
            }
        };

        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                handler.post(runnable);
            }
        }, 5000, 5000);

        carouselLayout.setOnTouchListener(new OnSwipeListener(HomePage.this) {
            public void onSwipeLeft() {
                int nextPosition = (bgCarousel.getCurrentItem() + 1) % listOfBg.size();

                bgCarousel.setCurrentItem(nextPosition);
                textCarousel.setCurrentItem(nextPosition);

                changeCharacter(nextPosition);
                changeActiveBullet(nextPosition);

                timer.schedule(new TimerTask() {
                    @Override
                    public void run() {
                        handler.post(runnable);
                    }
                }, 5000, 5000);
            }

            public void onSwipeRight() {
                int nextPosition = bgCarousel.getCurrentItem() - 1;

                if (nextPosition < 0) {
                    nextPosition = listOfBg.size() + nextPosition;
                }

                bgCarousel.setCurrentItem(nextPosition);
                textCarousel.setCurrentItem(nextPosition);

                changeCharacter(nextPosition);
                changeCharacter(nextPosition);

                timer.schedule(new TimerTask() {
                    @Override
                    public void run() {
                        handler.post(runnable);
                    }
                }, 5000, 5000);
            }
        });

        carouselBtn1 = findViewById(R.id.bullet1);
        carouselBtn2 = findViewById(R.id.bullet2);
        carouselBtn3 = findViewById(R.id.bullet3);

        carouselBtn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bgCarousel.setCurrentItem(0);
                textCarousel.setCurrentItem(0);

                changeCharacter(0);
                changeActiveBullet(0);

                timer.schedule(new TimerTask() {
                    @Override
                    public void run() {
                        handler.post(runnable);
                    }
                }, 5000, 5000);
            }
        });

        carouselBtn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bgCarousel.setCurrentItem(1);
                textCarousel.setCurrentItem(1);

                changeCharacter(1);
                changeActiveBullet(1);

                timer.schedule(new TimerTask() {
                    @Override
                    public void run() {
                        handler.post(runnable);
                    }
                }, 5000, 5000);
            }
        });

        carouselBtn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bgCarousel.setCurrentItem(2);
                textCarousel.setCurrentItem(2);

                changeCharacter(2);
                changeActiveBullet(2);

                timer.schedule(new TimerTask() {
                    @Override
                    public void run() {
                        handler.post(runnable);
                    }
                }, 5000, 5000);
            }
        });

        // setting tab view
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

    private String toCurrencyString(Integer value) {
        return NumberFormat.getCurrencyInstance(new Locale("id", "ID")).format(value).replace("Rp", "");
    }

    private void changeCharacter(int position) {
        Animation slideUpFromBottomAnimation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.slideup_frombottom);

        characterCarousel.startAnimation(fadeOutAnimation);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                characterCarousel.setImageResource(listOfCharacter.get(position));
                characterCarousel.startAnimation(slideUpFromBottomAnimation);
            }
        }, 400);
    }

    private void changeActiveBullet(int position){
        switch (position){
            case 0:
                carouselBtn1.setBackground(AppCompatResources.getDrawable(getApplicationContext() ,R.drawable.carousel_bullet_active));
                carouselBtn2.setBackground(AppCompatResources.getDrawable(getApplicationContext() ,R.drawable.carousel_bullet));
                carouselBtn3.setBackground(AppCompatResources.getDrawable(getApplicationContext() ,R.drawable.carousel_bullet));
                break;
            case 1:
                carouselBtn2.setBackground(AppCompatResources.getDrawable(getApplicationContext() ,R.drawable.carousel_bullet_active));
                carouselBtn1.setBackground(AppCompatResources.getDrawable(getApplicationContext() ,R.drawable.carousel_bullet));
                carouselBtn3.setBackground(AppCompatResources.getDrawable(getApplicationContext() ,R.drawable.carousel_bullet));
                break;
            case 2:
                carouselBtn3.setBackground(AppCompatResources.getDrawable(getApplicationContext() ,R.drawable.carousel_bullet_active));
                carouselBtn1.setBackground(AppCompatResources.getDrawable(getApplicationContext() ,R.drawable.carousel_bullet));
                carouselBtn2.setBackground(AppCompatResources.getDrawable(getApplicationContext() ,R.drawable.carousel_bullet));
        }
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