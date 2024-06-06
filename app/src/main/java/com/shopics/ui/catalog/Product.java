package com.shopics.ui.catalog;

import android.net.Uri;

import java.io.Serializable;

public class Product implements Serializable {
    private String name;
    private String price;
    private String description;
    private String picture;

    public Product() {
        // Обязательный пустой конструктор для Firebase
    }

    public Product(String name, String price, String description, String picture) {
        this.name = name;
        this.price = price;
        this.description = description;
        this.picture = picture;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }
}
