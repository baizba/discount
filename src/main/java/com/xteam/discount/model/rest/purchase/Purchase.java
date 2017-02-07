package com.xteam.discount.model.rest.purchase;

import java.util.List;

public class Purchase {

    public List<PurchaseEntry> getPurchases() {
        return purchases;
    }

    public void setPurchases(List<PurchaseEntry> purchases) {
        this.purchases = purchases;
    }

    private List<PurchaseEntry> purchases;
}
