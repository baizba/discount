package com.xteam.discount.service.rest.purchase.impl;

import com.xteam.discount.model.rest.purchase.PurchaseByProduct;
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
        String url = "http://127.0.0.1:8000/api/purchases/by_user/" + userName + "?limit=5";
        return getRestResponse(url, PurchaseByUser.class);
    }

    @Override
    public PurchaseByProduct getPurchaseByProduct(int productId) {
        String url = "http://127.0.0.1:8000/api/purchases/by_product/" + productId;
        return getRestResponse(url, PurchaseByProduct.class);
    }

    private <T> T getRestResponse(String url, Class<T> responseModelType) {
        RestTemplate restTemplate = new RestTemplate();
        try {
            T response = restTemplate.getForObject(url, responseModelType);
            LOG.debug("calling REST: " + url + " response is: " + response.toString());
            return response;
        } catch (RestClientException ex) {
            LOG.error("error making REST call " + url, ex);
            return null;
        }
    }
}
