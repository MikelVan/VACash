package com.vacash.android;

import java.util.List;

public class User {

    private String username;
    private String email;
    private Integer balance;
    private List<PurchaseHistory> purchaseHistories;

    public User(String username, String email, Integer balance, List<PurchaseHistory> purchaseHistories) {
        this.username = username;
        this.email = email;
        this.balance = balance;
        this.purchaseHistories = purchaseHistories;
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

    public List<PurchaseHistory> getPurchaseHistories() {
        return purchaseHistories;
    }

    public void setPurchaseHistories(List<PurchaseHistory> purchaseHistories) {
        this.purchaseHistories = purchaseHistories;
    }
}
