package com.vacash.android;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.vacash.android.adapters.ItemAdapter;
import com.vacash.android.adapters.PurchaseHistoryAdapter;
import com.vacash.android.models.Item;
import com.vacash.android.models.PurchaseHistory;

import java.util.ArrayList;

public class ItemPage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_page);

        ArrayList<Item> listOfItems = new ArrayList<>();
        listOfItems.add(new Item("Codashop", "60 Genesis Crystal", 16500, R.drawable.item_genesis_crystal));
        listOfItems.add(new Item("Codashop", "60 Genesis Crystal", 16500, R.drawable.item_genesis_crystal));
        listOfItems.add(new Item("Codashop", "60 Genesis Crystal", 16500, R.drawable.item_genesis_crystal));
        listOfItems.add(new Item("Codashop", "60 Genesis Crystal", 16500, R.drawable.item_genesis_crystal));
        listOfItems.add(new Item("Codashop", "60 Genesis Crystal", 16500, R.drawable.item_genesis_crystal));

        RecyclerView itemsRecycleView = findViewById(R.id.ItemRecycleView);
        itemsRecycleView.setLayoutManager(new LinearLayoutManager(this));
        itemsRecycleView.setAdapter(new ItemAdapter(
                listOfItems));
    }
}