package com.xteam.discount.model.rest.purchase;

import org.springframework.util.CollectionUtils;

import java.util.*;

public class Purchase {

    private List<PurchaseEntry> purchases;

    public List<PurchaseEntry> getPurchases() {
        if(CollectionUtils.isEmpty(purchases)) {
            purchases = new ArrayList<>();
        }
        return purchases;
    }

    public void setPurchases(List<PurchaseEntry> purchases) {
        this.purchases = purchases;
    }

    @Override
    public String toString() {
        return "purchases[" + getPurchases().toString() + "]";
    }
}
