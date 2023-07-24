package com.vacash.android.models;

public class Item {
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
