package com.vacash.android.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.vacash.android.R;
import com.vacash.android.interfaces.RecyclerViewInterface;
import com.vacash.android.models.Item;

import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;

public class ItemAdapter  extends RecyclerView.Adapter<ItemAdapter.ViewHolder>{

    private final RecyclerViewInterface recyclerViewInterface;
    List<Item> items;

    public ItemAdapter(List<Item> items, RecyclerViewInterface recyclerViewInterface) {
        this.items = items;
        this.recyclerViewInterface = recyclerViewInterface;
    }

    @NonNull
    @Override
    public ItemAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ItemAdapter.ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_view, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ItemAdapter.ViewHolder holder, int position) {
        holder.itemsImage.setImageResource(items.get(position).getItemsImage());
        holder.shopName.setText(items.get(position).getShopName());
        holder.itemsName.setText(items.get(position).getItemsName());

        String totalPriceText = "IDR " + toCurrencyString(items.get(position).getItemsPrice());
        holder.itemsPrice.setText(totalPriceText);
    }

    private String toCurrencyString(Integer value){
        return NumberFormat.getCurrencyInstance(new Locale("id", "ID")).format(value).replace("Rp", "");
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private ImageView itemsImage;
        private TextView shopName, itemsName, itemsPrice;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            itemsImage = itemView.findViewById(R.id.itemsImage);
            shopName = itemView.findViewById(R.id.shopName);
            itemsName = itemView.findViewById(R.id.itemsName);
            itemsPrice = itemView.findViewById(R.id.itemsPrice);

            itemView.setOnClickListener(new View.OnClickListener() {
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
