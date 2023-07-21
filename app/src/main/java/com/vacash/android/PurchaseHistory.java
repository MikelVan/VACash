package com.vacash.android;

public class PurchaseHistory {

    private String gameName;
    private String itemName;
    private Integer itemQty;
    private Integer totalPrice;

    public PurchaseHistory(String gameName, String itemName, Integer itemQty, Integer totalPrice) {
        this.gameName = gameName;
        this.itemName = itemName;
        this.itemQty = itemQty;
        this.totalPrice = totalPrice;
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
