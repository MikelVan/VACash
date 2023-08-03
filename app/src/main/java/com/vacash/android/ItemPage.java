package com.vacash.android;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.content.res.AppCompatResources;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.vacash.android.adapters.ItemAdapter;
import com.vacash.android.interfaces.RecyclerViewInterface;
import com.vacash.android.models.Item;
import com.vacash.android.models.User;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Locale;

import me.everything.android.ui.overscroll.OverScrollDecoratorHelper;

public class ItemPage extends AppCompatActivity implements RecyclerViewInterface {

    private User user;
    private String gameName, gameDeveloper, gameCategory, gameDescription;
    private Integer gameLogo;
    private ImageView gameLogoView;
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

        checkProfileButton.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if(event.getAction() == MotionEvent.ACTION_DOWN){
                    checkProfileButton.setBackground(AppCompatResources.getDrawable(getApplicationContext(), R.drawable.bg_dropdown1_touchdown));
                }
                else{
                    checkProfileButton.setBackground(AppCompatResources.getDrawable(getApplicationContext(), R.drawable.bg_dropdown1));
                }

                return false;
            }
        });

        logoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent loginActivity = new Intent(ItemPage.this, LoginPage.class);
                startActivity(loginActivity);
            }
        });

        logoutButton.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if(event.getAction() == MotionEvent.ACTION_DOWN){
                    logoutButton.setBackground(AppCompatResources.getDrawable(getApplicationContext(), R.drawable.bg_dropdown2_touchdown));
                }
                else{
                    logoutButton.setBackground(AppCompatResources.getDrawable(getApplicationContext(), R.drawable.bg_dropdown2));
                }

                return false;
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

        gameLogoView = findViewById(R.id.gameLogoView);
        gameNameView = findViewById(R.id.gameNameView);
        gameDeveloperView = findViewById(R.id.gameDeveloperView);
        gameCategoryView = findViewById(R.id.gameCategoryView);

        gameLogo = previousPage.getIntExtra("gameLogo", 0);
        gameName = previousPage.getStringExtra("gameName");
        gameDeveloper = previousPage.getStringExtra("gameDeveloper");
        gameCategory = previousPage.getStringExtra("gameCategory");

        gameLogoView.setImageResource(gameLogo);
        gameNameView.setText(gameName);
        gameDeveloperView.setText(gameDeveloper);
        gameCategoryView.setText(gameCategory);

        listOfItems = new ArrayList<>();

        if (gameName.equals("Genshin Impact")){
            gameDescription = "Genesis Crystal is a premium currency in Genshin Impact used to purchase various in-game items and rewards.";
            listOfItems.add(new Item("Codashop", "60 Genesis Crystals", 16500, R.drawable.item_genesis_crystal));
            listOfItems.add(new Item("Codashop", "330 Genesis Crystals", 81000, R.drawable.item_genesis_crystal));
            listOfItems.add(new Item("Codashop", "1090 Genesis Crystals", 255000, R.drawable.item_genesis_crystal));
            listOfItems.add(new Item("Tokovoucher", "2240 Genesis Crystals", 489000, R.drawable.item_genesis_crystal));
            listOfItems.add(new Item("Tokovoucher", "3880 Genesis Crystals", 815000, R.drawable.item_genesis_crystal));
        } else if (gameName.equals("Honkai Star Rail")) {
            gameDescription = "Oneiric Shards are special fragments in Honkai Star Rail that enhance characters and unlock powerful abilities.";
            listOfItems.add(new Item("Dunia Games", "60 Oneiric Shards", 16500, R.drawable.item_oneiric_shard));
            listOfItems.add(new Item("Dunia Games", "330 Oneiric Shards", 81000, R.drawable.item_oneiric_shard));
            listOfItems.add(new Item("UniPlay", "1090 Oneiric Shards", 255000, R.drawable.item_oneiric_shard));
            listOfItems.add(new Item("UniPlay", "2240 Oneiric Shards", 489000, R.drawable.item_oneiric_shard));
            listOfItems.add(new Item("UniPlay", "3880 Oneiric Shards", 815000, R.drawable.item_oneiric_shard));
        } else if (gameName.equals("Mobile Legends")) {
            gameDescription = "Diamonds are the premium currency in Mobile Legends used to purchase heroes, skins, and in-game items.";
            listOfItems.add(new Item("Dunia Games", "3 Diamonds", 1514, R.drawable.item_diamond));
            listOfItems.add(new Item("Coda Shop", "5 Diamonds", 1579, R.drawable.item_diamond));
            listOfItems.add(new Item("Coda Shop", "12 Diamonds", 3688, R.drawable.item_diamond));
            listOfItems.add(new Item("Coda Shop", "19 Diamonds", 5797, R.drawable.item_diamond));
            listOfItems.add(new Item("Tokovoucher", "28 Diamonds", 8436, R.drawable.item_diamond));
        } else if (gameName.equals("Growtopia")) {
            gameDescription = "Gems are valuable resources in Growtopia used for trading, crafting, and acquiring rare items and decorations.";
            listOfItems.add(new Item("Dunia Games", "Bag O' Gems", 14250, R.drawable.item_bog));
            listOfItems.add(new Item("Dunia Games", "Chest O' Gems", 28600, R.drawable.item_bog));
            listOfItems.add(new Item("UniPlay", "Gem Fountain", 72200, R.drawable.item_bog));
            listOfItems.add(new Item("Codashop", "It's Rainin' Gems", 1434500, R.drawable.item_bog));
            listOfItems.add(new Item("Tokovoucher", "Gem Bounty", 431300, R.drawable.item_bog));
        } else if (gameName.equals("Stumble Guys")) {
            gameDescription = "In Stumble Guys, gems are a premium currency used to unlock costumes, emotes, and other cosmetic items.";
            listOfItems.add(new Item("Tokovoucher", "250 Gems", 12000, R.drawable.item_gems));
            listOfItems.add(new Item("Tokovoucher", "800 Gems", 31500, R.drawable.item_gems));
            listOfItems.add(new Item("Codashop", "1675 Gems", 55295, R.drawable.item_gems));
            listOfItems.add(new Item("Codashop", "3275 Gems", 98250, R.drawable.item_gems));
            listOfItems.add(new Item("UniPlay", "5275 Gems", 125000, R.drawable.item_gems));
        } else if (gameName.equals("Hogwarts Legacy")) {
            gameDescription = "Items in Hogwarts Legacy are magical objects and artifacts that aid in spellcasting, exploration, and quest completion within the game.";
            listOfItems.add(new Item("UniPlay", "Avada Kedavra", 99000, R.drawable.item_spell));
            listOfItems.add(new Item("Tokovoucher", "Crucio", 59000, R.drawable.item_spell));
            listOfItems.add(new Item("Tokovoucher", "Imperio", 59000, R.drawable.item_spell));
            listOfItems.add(new Item("Codashop", "Sectumsempra", 49000, R.drawable.item_spell));
            listOfItems.add(new Item("Codashop", "Transformation", 79000, R.drawable.item_spell));
        } else if (gameName.equals("Call of Duty")) {
            gameDescription = "Call of Duty Points is the in-game currency used to purchase cosmetic items and battle passes in Call of Duty games.";
            listOfItems.add(new Item("UniPlay", "31 CP", 5000, R.drawable.item_cp));
            listOfItems.add(new Item("UniPlay", "62 CP", 10000, R.drawable.item_cp));
            listOfItems.add(new Item("Tokovoucher", "127 CP", 20000, R.drawable.item_cp));
            listOfItems.add(new Item("Tokovoucher", "320 CP", 50000, R.drawable.item_cp));
            listOfItems.add(new Item("Tokovoucher", "645 CP", 100000, R.drawable.item_cp));
        } else if (gameName.equals("Animal Crossing")) {
            gameDescription = "Miles Tickets are special travel vouchers in Animal Crossing used to visit mystery islands and discover new resources and villagers.";
            listOfItems.add(new Item("ACNH ITEMS", "100 Nook Miles Ticket", 4000, R.drawable.item_nook_miles_ticket));
            listOfItems.add(new Item("ACNH ITEMS", "200 Nook Miles Ticket", 7000, R.drawable.item_nook_miles_ticket));
            listOfItems.add(new Item("ACNH ITEMS", "300 Nook Miles Ticket", 10000, R.drawable.item_nook_miles_ticket));
            listOfItems.add(new Item("ACNH ITEMS", "400 Nook Miles Ticket", 13000, R.drawable.item_nook_miles_ticket));
            listOfItems.add(new Item("ACNH ITEMS", "500 Nook Miles Ticket", 15000, R.drawable.item_nook_miles_ticket));
        } else if (gameName.equals("Fortnite")) {
            gameDescription = "V-Bucks are the in-game currency in Fortnite used to purchase cosmetic items, battle passes, and other in-game content.";
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
        detailActivity.putExtra("gameDeveloper", gameDeveloper);
        detailActivity.putExtra("gameCategory", gameCategory);
        detailActivity.putExtra("gameLogo", gameLogo);
        detailActivity.putExtra("gameDescription", gameDescription);
        startActivity(detailActivity);
    }
}