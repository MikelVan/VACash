package com.vacash.android;

import android.content.Context;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class PurchaseHistoryAdapter extends RecyclerView.Adapter<PurchaseHistoryViewHolder> {

    Context context;
    List<PurchaseHistory> purchaseHistories;

    public PurchaseHistoryAdapter(Context context, List<PurchaseHistory> purchaseHistories) {
        this.context = context;
        this.purchaseHistories = purchaseHistories;
    }

    @NonNull
    @Override
    public PurchaseHistoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new PurchaseHistoryViewHolder(LayoutInflater.from(context).inflate(R.layout.purchase_history_view, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull PurchaseHistoryViewHolder holder, int position) {
        holder.gameNameView.setText(purchaseHistories.get(position).getGameName());
        holder.itemNameView.setText(purchaseHistories.get(position).getItemName());

        String itemQtyText = "x" + purchaseHistories.get(position).getItemQty().toString();
        holder.itemQtyView.setText(itemQtyText);

        String totalPriceText = "Rp. " + purchaseHistories.get(position).getTotalPrice().toString();
        holder.totalPriceView.setText(totalPriceText);
    }

    @Override
    public int getItemCount() {
        return purchaseHistories.size();
    }

}
