package com.xteam.discount.model.rest.purchase;

import java.util.Date;

public class PurchaseEntry {

    private int id;
    private String username;
    private int productId;
    private Date date;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[id:").append(id).append(", username:").append(username).append(",productId:").append(productId)
        .append(",date:").append(date).append("]");
        return sb.toString();
    }
}
