package com.vacash.android;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.vacash.android.adapters.ItemAdapter;
import com.vacash.android.interfaces.ItemInterface;
import com.vacash.android.models.Item;
import com.vacash.android.models.User;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Locale;

import me.everything.android.ui.overscroll.OverScrollDecoratorHelper;

public class ItemPage extends AppCompatActivity implements ItemInterface {

    private User user;
    private String gameName;
    private ImageView gameLogo;
    private TextView gameNameView, gameDeveloperView, gameCategoryView, userBalance;
    private RelativeLayout action_bar, dropdownMenu, ppHighlight, dark_overlay;
    private LinearLayout dropdownList, checkProfileButton, logoutButton;
    private ImageView homeIconActionBar;
    private Animation slideDownAnimation, slideUpAnimation, fadeInAnimation, fadeOutAnimation;

    private ArrayList<Item> listOfItems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_page);

        action_bar = findViewById(R.id.actionBar);
        dropdownMenu = findViewById(R.id.dropdownMenu);
        dropdownList = findViewById(R.id.dropdownList);
        ppHighlight = findViewById(R.id.ppHighlight);
        dark_overlay = findViewById(R.id.dark_overlay);
        checkProfileButton = findViewById(R.id.checkProfileButton);
        logoutButton = findViewById(R.id.logoutButton);
        homeIconActionBar = findViewById(R.id.homeIconActionBar);
        userBalance = findViewById(R.id.balance);
        slideDownAnimation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.slidedown);
        slideUpAnimation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.slideup);
        fadeInAnimation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fadein);
        fadeOutAnimation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fadeout);

        slideDownAnimation.setInterpolator(new AccelerateDecelerateInterpolator());
        slideUpAnimation.setInterpolator(new AccelerateDecelerateInterpolator());

        action_bar.bringToFront();

        Intent previousPage = getIntent();
        user = previousPage.getParcelableExtra("userData");

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
                Intent profileActivity = new Intent(ItemPage.this, ProfilePage.class);
                profileActivity.putExtra("userData", user);
                startActivity(profileActivity);
            }
        });

        logoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent loginActivity = new Intent(ItemPage.this, LoginPage.class);
                startActivity(loginActivity);
            }
        });

        homeIconActionBar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent homeActivity = new Intent(ItemPage.this, HomePage.class);
                homeActivity.putExtra("userData", user);
                startActivity(homeActivity);
            }
        });

        ScrollView scrollView = findViewById(R.id.scrollableView);

        OverScrollDecoratorHelper.setUpOverScroll(scrollView);

        gameLogo = findViewById(R.id.gameLogoView);
        gameNameView = findViewById(R.id.gameNameView);
        gameDeveloperView = findViewById(R.id.gameDeveloperView);
        gameCategoryView = findViewById(R.id.gameCategoryView);

        gameName = previousPage.getStringExtra("gameName");

        gameLogo.setImageResource(previousPage.getIntExtra("gameLogo", 0));
        gameNameView.setText(previousPage.getStringExtra("gameName"));
        gameDeveloperView.setText(previousPage.getStringExtra("gameDeveloper"));
        gameCategoryView.setText(previousPage.getStringExtra("gameCategory"));

        listOfItems = new ArrayList<>();

        if (gameName.equals("Genshin Impact")){
            listOfItems.add(new Item("Codashop", "60 Genesis Crystals", 16500, R.drawable.item_genesis_crystal));
            listOfItems.add(new Item("Codashop", "330 Genesis Crystals", 81000, R.drawable.item_genesis_crystal));
            listOfItems.add(new Item("Codashop", "1090 Genesis Crystals", 255000, R.drawable.item_genesis_crystal));
            listOfItems.add(new Item("Tokovoucher", "2240 Genesis Crystals", 489000, R.drawable.item_genesis_crystal));
            listOfItems.add(new Item("Tokovoucher", "3880 Genesis Crystals", 815000, R.drawable.item_genesis_crystal));
        } else if (gameName.equals("Honkai Star Rail")) {
            listOfItems.add(new Item("Dunia Games", "60 Oneiric Shards", 16500, R.drawable.item_oneiric_shard));
            listOfItems.add(new Item("Dunia Games", "330 Oneiric Shards", 81000, R.drawable.item_oneiric_shard));
            listOfItems.add(new Item("UniPlay", "1090 Oneiric Shards", 255000, R.drawable.item_oneiric_shard));
            listOfItems.add(new Item("UniPlay", "2240 Oneiric Shards", 489000, R.drawable.item_oneiric_shard));
            listOfItems.add(new Item("UniPlay", "3880 Oneiric Shards", 815000, R.drawable.item_oneiric_shard));
        } else if (gameName.equals("Mobile Legends")) {
            listOfItems.add(new Item("Dunia Games", "3 Diamonds", 1514, R.drawable.item_diamond));
            listOfItems.add(new Item("Coda Shop", "5 Diamonds", 1579, R.drawable.item_diamond));
            listOfItems.add(new Item("Coda Shop", "12 Diamonds", 3688, R.drawable.item_diamond));
            listOfItems.add(new Item("Coda Shop", "19 Diamonds", 5797, R.drawable.item_diamond));
            listOfItems.add(new Item("Tokovoucher", "28 Diamonds", 8436, R.drawable.item_diamond));
        } else if (gameName.equals("Growtopia")) {
            listOfItems.add(new Item("Dunia Games", "Bag O' Gems", 14250, R.drawable.item_bog));
            listOfItems.add(new Item("Dunia Games", "Chest O' Gems", 28600, R.drawable.item_bog));
            listOfItems.add(new Item("UniPlay", "Gem Fountain", 72200, R.drawable.item_bog));
            listOfItems.add(new Item("Codashop", "It's Rainin' Gems", 1434500, R.drawable.item_bog));
            listOfItems.add(new Item("Tokovoucher", "Gem Bounty", 431300, R.drawable.item_bog));
        } else if (gameName.equals("Stumble Guys")) {
            listOfItems.add(new Item("Tokovoucher", "250 Gems", 12000, R.drawable.item_gems));
            listOfItems.add(new Item("Tokovoucher", "800 Gems", 31500, R.drawable.item_gems));
            listOfItems.add(new Item("Codashop", "1675 Gems", 55295, R.drawable.item_gems));
            listOfItems.add(new Item("Codashop", "3275 Gems", 98250, R.drawable.item_gems));
            listOfItems.add(new Item("UniPlay", "5275 Gems", 125000, R.drawable.item_gems));
        } else if (gameName.equals("Hogwarts Legacy")) {
            listOfItems.add(new Item("UniPlay", "Avada Kedavra", 99000, R.drawable.item_spell));
            listOfItems.add(new Item("Tokovoucher", "Crucio", 59000, R.drawable.item_spell));
            listOfItems.add(new Item("Tokovoucher", "Imperio", 59000, R.drawable.item_spell));
            listOfItems.add(new Item("Codashop", "Sectumsempra", 49000, R.drawable.item_spell));
            listOfItems.add(new Item("Codashop", "Transformation", 79000, R.drawable.item_spell));
        } else if (gameName.equals("Call of Duty")) {
            listOfItems.add(new Item("UniPlay", "31 CP", 5000, R.drawable.item_cp));
            listOfItems.add(new Item("UniPlay", "62 CP", 10000, R.drawable.item_cp));
            listOfItems.add(new Item("Tokovoucher", "127 CP", 20000, R.drawable.item_cp));
            listOfItems.add(new Item("Tokovoucher", "320 CP", 50000, R.drawable.item_cp));
            listOfItems.add(new Item("Tokovoucher", "645 CP", 100000, R.drawable.item_cp));
        } else if (gameName.equals("Animal Crossing")) {
            listOfItems.add(new Item("ACNH ITEMS", "100 Nook Miles Ticket", 4000, R.drawable.item_nook_miles_ticket));
            listOfItems.add(new Item("ACNH ITEMS", "200 Nook Miles Ticket", 7000, R.drawable.item_nook_miles_ticket));
            listOfItems.add(new Item("ACNH ITEMS", "300 Nook Miles Ticket", 10000, R.drawable.item_nook_miles_ticket));
            listOfItems.add(new Item("ACNH ITEMS", "400 Nook Miles Ticket", 13000, R.drawable.item_nook_miles_ticket));
            listOfItems.add(new Item("ACNH ITEMS", "500 Nook Miles Ticket", 15000, R.drawable.item_nook_miles_ticket));
        } else if (gameName.equals("Fortnite")) {
            listOfItems.add(new Item("Digicodes", "1000 V-Bucks", 209000, R.drawable.item_vbucks));
            listOfItems.add(new Item("Digicodes", "2800 V-Bucks", 469000, R.drawable.item_vbucks));
            listOfItems.add(new Item("Digicodes", "5000 V-Bucks", 756000, R.drawable.item_vbucks));
            listOfItems.add(new Item("Digicodes", "13500 V-Bucks", 1419000, R.drawable.item_vbucks));
            listOfItems.add(new Item("Digicodes", "18000 V-Bucks", 1819000, R.drawable.item_vbucks));
        }

        RecyclerView itemsRecycleView = findViewById(R.id.ItemRecycleView);
        itemsRecycleView.setLayoutManager(new LinearLayoutManager(this));
        itemsRecycleView.setAdapter(new ItemAdapter(listOfItems, this));
    }

    private String toCurrencyString(Integer value){
        return NumberFormat.getCurrencyInstance(new Locale("id", "ID")).format(value).replace("Rp", "");
    }

    @Override
    public void onItemClick(int position) {
        Intent detailActivity = new Intent(ItemPage.this, DetailPage.class);
        detailActivity.putExtra("userData", user);
        detailActivity.putExtra("itemData", listOfItems.get(position));
        detailActivity.putExtra("gameName", gameName);
        startActivity(detailActivity);
    }
}