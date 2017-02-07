package com.xteam.discount.model.rest.purchase;

import org.springframework.util.CollectionUtils;

import java.util.*;

public class PurchaseByUser {

    private List<PurchaseByUserEntry> purchases;

    public List<PurchaseByUserEntry> getPurchases() {
        if(CollectionUtils.isEmpty(purchases)) {
            purchases = new ArrayList<>();
        }
        return purchases;
    }

    public void setPurchases(List<PurchaseByUserEntry> purchases) {
        this.purchases = purchases;
    }

    @Override
    public String toString() {
        return "purchases[" + getPurchases().toString() + "]";
    }
}
