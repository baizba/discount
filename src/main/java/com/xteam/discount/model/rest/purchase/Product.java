package com.xteam.discount.model.rest.purchase;

public class Product {

    private int id;
    private String face;
    private int price;
    private int size;

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

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Product[").append("id:").append(id).append(",face:").append(face).append(",price:").append(price)
                .append(",size:").append(size).append("]");
        return sb.toString();
    }
}
