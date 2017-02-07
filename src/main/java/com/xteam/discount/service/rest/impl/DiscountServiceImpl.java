package com.xteam.discount.service.rest.impl;

import com.xteam.discount.model.rest.PopularPurchase;
import com.xteam.discount.model.rest.purchase.Purchase;
import com.xteam.discount.service.rest.PurchaseService;
import com.xteam.discount.service.rest.purchase.DiscountService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
        Purchase purchaseByUser = purchaseService.getPurchaseByUser(username);

        //TODO need to complete mapping this is just a test run to make sure it works
        List<PopularPurchase> popularPurchases = purchaseByUser.getPurchases().stream().map(purchaseEntry -> {
            PopularPurchase popularPurchase = new PopularPurchase();
            popularPurchase.setId(purchaseEntry.getId());
            return popularPurchase;
        }).collect(Collectors.toList());
        return popularPurchases;
    }
}
