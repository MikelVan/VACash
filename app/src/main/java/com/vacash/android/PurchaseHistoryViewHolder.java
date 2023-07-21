package com.vacash.android;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class PurchaseHistoryViewHolder extends RecyclerView.ViewHolder {

    TextView gameNameView, itemNameView, itemQtyView, totalPriceView;

    public PurchaseHistoryViewHolder(@NonNull View purchaseHistoryView) {
        super(purchaseHistoryView);

        gameNameView = purchaseHistoryView.findViewById(R.id.gameName);
        itemNameView = purchaseHistoryView.findViewById(R.id.itemName);
        itemQtyView = purchaseHistoryView.findViewById(R.id.itemQty);
        totalPriceView = purchaseHistoryView.findViewById(R.id.totalPrice);
    }

}
