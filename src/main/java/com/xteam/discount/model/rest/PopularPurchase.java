package com.xteam.discount.model.rest;

import java.util.List;

/**
 * Created by baizb on 07.02.2017.
 */
public class PopularPurchase {

    private int id;
    private String face;
    private double price;
    private int size;
    private List<String> recent;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFace() {
        return face;
    }

    public void setFace(String face) {
        this.face = face;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public List<String> getRecent() {
        return recent;
    }

    public void setRecent(List<String> recent) {
        this.recent = recent;
    }
}
