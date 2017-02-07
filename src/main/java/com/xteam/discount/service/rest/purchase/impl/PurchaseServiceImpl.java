package com.xteam.discount.service.rest.purchase.impl;

import com.xteam.discount.model.rest.purchase.PurchaseByUser;
import com.xteam.discount.service.rest.PurchaseService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

@Component
public class PurchaseServiceImpl implements PurchaseService {

    private static final Logger LOG = LoggerFactory.getLogger(PurchaseServiceImpl.class);

    @Override
    public PurchaseByUser getPurchaseByUser(String userName) {
        RestTemplate restTemplate = new RestTemplate();
        String url = "http://127.0.0.1:8000/api/purchases/by_user/" + userName + "?limit=5";

        try{
            PurchaseByUser purchaseByUser = restTemplate.getForObject(url, PurchaseByUser.class);
            LOG.debug("calling REST: " + url + " response is: " + purchaseByUser.toString());
            return purchaseByUser;
        } catch (RestClientException ex) {
            LOG.error("error making REST call to " + url, ex);
            return new PurchaseByUser();
        }

    }
}
