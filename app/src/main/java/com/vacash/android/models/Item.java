package com.vacash.android.models;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

public class Item implements Parcelable {
    private String ShopName;
    private String ItemsName;
    private Integer ItemsPrice;
    private Integer itemsImage;

    public Item(String ShopName, String ItemsName, Integer ItemsPrice, Integer itemsImage) {
        this.ShopName = ShopName;
        this.ItemsName = ItemsName;
        this.ItemsPrice = ItemsPrice;
        this.itemsImage = itemsImage;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel dest, int flags) {
        dest.writeString(ShopName);
        dest.writeString(ItemsName);
        dest.writeInt(ItemsPrice);
        dest.writeInt(itemsImage);
    }

    public static final Parcelable.Creator<Item> CREATOR = new Parcelable.Creator<Item>() {
        public Item createFromParcel(Parcel in) {
            return new Item(in);
        }

        public Item[] newArray(int size) {
            return new Item[size];
        }
    };

    private Item(Parcel in) {
        ShopName = in.readString();
        ItemsName = in.readString();
        ItemsPrice = in.readInt();
        itemsImage = in.readInt();
    }

    public String getShopName() {
        return ShopName;
    }

    public void setShopName(String shopName) {
        ShopName = shopName;
    }

    public String getItemsName() {
        return ItemsName;
    }

    public void setItemsName(String itemsName) {
        ItemsName = itemsName;
    }

    public Integer getItemsPrice() {
        return ItemsPrice;
    }

    public void setItemsPrice(Integer itemsPrice) {
        ItemsPrice = itemsPrice;
    }

    public Integer getItemsImage() {
        return itemsImage;
    }

    public void setItemsImage(Integer itemsImage) {
        this.itemsImage = itemsImage;
    }
}
