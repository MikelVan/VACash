package com.vacash.android.models;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

import java.util.ArrayList;

public class PurchaseHistory implements Parcelable{

    private String gameName, itemName;
    private Integer itemQty, totalPrice;

    public PurchaseHistory(String gameName, String itemName, Integer itemQty, Integer totalPrice) {
        this.gameName = gameName;
        this.itemName = itemName;
        this.itemQty = itemQty;
        this.totalPrice = totalPrice;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel dest, int flags) {
        dest.writeString(gameName);
        dest.writeString(itemName);
        dest.writeInt(itemQty);
        dest.writeInt(totalPrice);
    }

    public static final Parcelable.Creator<PurchaseHistory> CREATOR = new Parcelable.Creator<PurchaseHistory>() {
        public PurchaseHistory createFromParcel(Parcel in) {
            return new PurchaseHistory(in);
        }

        public PurchaseHistory[] newArray(int size) {
            return new PurchaseHistory[size];
        }
    };

    private PurchaseHistory(Parcel in) {
        gameName = in.readString();
        itemName = in.readString();
        itemQty = in.readInt();
        totalPrice = in.readInt();
    }

    public String getGameName() {
        return gameName;
    }

    public void setGameName(String gameName) {
        this.gameName = gameName;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public Integer getItemQty() {
        return itemQty;
    }

    public void setItemQty(Integer itemQty) {
        this.itemQty = itemQty;
    }

    public Integer getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Integer totalPrice) {
        this.totalPrice = totalPrice;
    }
}
