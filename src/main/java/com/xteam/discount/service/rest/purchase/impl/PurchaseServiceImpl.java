package com.xteam.discount.service.rest.purchase.impl;

import com.xteam.discount.model.rest.purchase.Purchase;
import com.xteam.discount.service.rest.PurchaseService;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class PurchaseServiceImpl implements PurchaseService {

    @Override
    public Purchase getPurchaseByUser(String userName) {
        RestTemplate restTemplate = new RestTemplate();
        String url = "http://127.0.0.1:8000/api/purchases/by_user/" + userName + "?limit=5";
        Purchase purchase = restTemplate.getForObject(url, Purchase.class);
        return purchase;
    }
}
