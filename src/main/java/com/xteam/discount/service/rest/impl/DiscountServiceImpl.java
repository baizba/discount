package com.xteam.discount.service.rest.impl;

import com.xteam.discount.model.rest.PopularPurchase;
import com.xteam.discount.model.rest.purchase.PurchaseByProduct;
import com.xteam.discount.model.rest.purchase.PurchaseByUser;
import com.xteam.discount.service.rest.PurchaseService;
import com.xteam.discount.service.rest.purchase.DiscountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class DiscountServiceImpl implements DiscountService {

    @Autowired
    private PurchaseService purchaseService;

    @Override
    public List<PopularPurchase> getPopularPurchasesByUsername(String username) {
        PurchaseByUser purchaseByUser = purchaseService.getPurchaseByUser(username);

        //TODO need to complete mapping this is just a test run to make sure it works
        List<PopularPurchase> popularPurchases = purchaseByUser.getPurchases().stream().map(purchaseEntry -> {
            PopularPurchase popularPurchase = new PopularPurchase();
            popularPurchase.setId(purchaseEntry.getProductId());

            //get the purchases for this product
            PurchaseByProduct purchaseByProduct = purchaseService.getPurchaseByProduct(purchaseEntry.getProductId());
            purchaseByProduct.getPurchases().forEach(purchaseByProductEntry ->
                    popularPurchase.getRecent().add(purchaseByProductEntry.getUsername()));

            return popularPurchase;
        }).collect(Collectors.toList());
        return popularPurchases;
    }
}
