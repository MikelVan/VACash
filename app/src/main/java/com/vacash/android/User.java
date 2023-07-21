package com.vacash.android;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

import java.util.ArrayList;

public class User implements Parcelable {

    private String username;
    private String email;
    private Integer balance;
    private ArrayList<PurchaseHistory> purchaseHistories;

    public User(String username, String email, Integer balance, ArrayList<PurchaseHistory> purchaseHistories) {
        this.username = username;
        this.email = email;
        this.balance = balance;
        this.purchaseHistories = purchaseHistories;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel dest, int flags) {
        dest.writeString(username);
        dest.writeString(email);
        dest.writeInt(balance);
        dest.writeList(purchaseHistories);
    }

    public static final Parcelable.Creator<User> CREATOR = new Parcelable.Creator<User>() {
        public User createFromParcel(Parcel in) {
            return new User(in);
        }

        public User[] newArray(int size) {
            return new User[size];
        }
    };

    private User(Parcel in) {
        username = in.readString();
        email = in.readString();
        balance = in.readInt();
        purchaseHistories = new ArrayList<>();
        in.readList(purchaseHistories, purchaseHistories.getClass().getClassLoader());
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getBalance() {
        return balance;
    }

    public void setBalance(Integer balance) {
        this.balance = balance;
    }

    public ArrayList<PurchaseHistory> getPurchaseHistories() {
        return purchaseHistories;
    }

    public void setPurchaseHistories(ArrayList<PurchaseHistory> purchaseHistories) {
        this.purchaseHistories = purchaseHistories;
    }
}
