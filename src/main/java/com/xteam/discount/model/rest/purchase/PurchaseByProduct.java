package com.xteam.discount.model.rest.purchase;

import java.util.ArrayList;
import java.util.List;

public class PurchaseByProduct {

    private List<PurchaseByProductEntry> purchases;

    public List<PurchaseByProductEntry> getPurchases() {
        if(purchases == null) {
            purchases = new ArrayList<>();
        }
        return purchases;
    }

    public void setPurchases(List<PurchaseByProductEntry> purchases) {
        this.purchases = purchases;
    }
}
