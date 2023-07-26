package com.vacash.android;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.text.Layout;
import android.view.View;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;

import com.vacash.android.adapters.ItemAdapter;
import com.vacash.android.models.Item;

import java.util.ArrayList;

import me.everything.android.ui.overscroll.OverScrollDecoratorHelper;

public class ItemPage extends AppCompatActivity {

    ImageView gameLogo;
    TextView gameNameView, gameDeveloperView, gameCategoryView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_page);

        ScrollView scrollView = findViewById(R.id.scrollableView);
        View actionBar = findViewById(R.id.actionBar);

        OverScrollDecoratorHelper.setUpOverScroll(scrollView);
        actionBar.bringToFront();

        gameLogo = findViewById(R.id.gameLogoView);
        gameNameView = findViewById(R.id.gameNameView);
        gameDeveloperView = findViewById(R.id.gameDeveloperView);
        gameCategoryView = findViewById(R.id.gameCategoryView);

        Intent previousPage = getIntent();
        String gameName = previousPage.getStringExtra("gameName");

        gameLogo.setImageResource(previousPage.getIntExtra("gameLogo", 0));
        gameNameView.setText(previousPage.getStringExtra("gameName"));
        gameDeveloperView.setText(previousPage.getStringExtra("gameDeveloper"));
        gameCategoryView.setText(previousPage.getStringExtra("gameCategory"));

        ArrayList<Item> listOfItems = new ArrayList<>();

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
        itemsRecycleView.setAdapter(new ItemAdapter(
                listOfItems));
    }
}