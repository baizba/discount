package com.xteam.discount.model.rest;

import java.util.ArrayList;
import java.util.List;

/**
 * This is a REST response which we use as the "exit" structure of our backend
 */
public class PopularPurchase {

    private int id;
    private String face;
    private int price;
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

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public List<String> getRecent() {
        if(recent == null) {
            recent = new ArrayList<>();
        }
        return recent;
    }

    public void setRecent(List<String> recent) {
        this.recent = recent;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("PopularPurchase:[id:").append(id).append(",face:").append(face).append(",price:").append(price)
                .append(",size:").append(size).append(",recent:").append(getRecent()).append("]");
        return sb.toString();
    }
}
