package com.vacash.android.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.vacash.android.R;
import com.vacash.android.interfaces.RecyclerViewInterface;
import com.vacash.android.models.PurchaseHistory;

import java.util.List;

public class PurchaseHistoryAdapter extends RecyclerView.Adapter<PurchaseHistoryAdapter.ViewHolder> {

    private final RecyclerViewInterface recyclerViewInterface;
    List<PurchaseHistory> purchaseHistories;

    public PurchaseHistoryAdapter(List<PurchaseHistory> purchaseHistories, RecyclerViewInterface recyclerViewInterface) {
        this.purchaseHistories = purchaseHistories;
        this.recyclerViewInterface = recyclerViewInterface;
    }

    @NonNull
    @Override
    public PurchaseHistoryAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.purchase_history_view, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull PurchaseHistoryAdapter.ViewHolder holder, int position) {
        holder.gameNameView.setText(purchaseHistories.get(position).getGameName());
        holder.itemNameView.setText(purchaseHistories.get(position).getItemName());

        String itemQtyText = "x" + purchaseHistories.get(position).getItemQty().toString();
        holder.itemQtyView.setText(itemQtyText);

        String totalPriceText = "IDR " + purchaseHistories.get(position).getTotalPrice().toString();
        holder.totalPriceView.setText(totalPriceText);
    }

    @Override
    public int getItemCount() {
        return purchaseHistories.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        TextView gameNameView, itemNameView, itemQtyView, totalPriceView;

        public ViewHolder(@NonNull View purchaseHistoryView) {
            super(purchaseHistoryView);

            gameNameView = purchaseHistoryView.findViewById(R.id.gameName);
            itemNameView = purchaseHistoryView.findViewById(R.id.itemName);
            itemQtyView = purchaseHistoryView.findViewById(R.id.itemQty);
            totalPriceView = purchaseHistoryView.findViewById(R.id.totalPrice);

            purchaseHistoryView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(recyclerViewInterface != null){
                        int pos = getBindingAdapterPosition();

                        if(pos != RecyclerView.NO_POSITION){
                            recyclerViewInterface.onItemClick(pos);
                        }
                    }
                }
            });
        }

    }

}
