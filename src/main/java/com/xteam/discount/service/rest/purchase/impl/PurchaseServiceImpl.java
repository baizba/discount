package com.xteam.discount.service.rest.purchase.impl;

import com.xteam.discount.model.rest.purchase.ProductByProductId;
import com.xteam.discount.model.rest.purchase.PurchaseByProduct;
import com.xteam.discount.model.rest.purchase.PurchaseByUser;
import com.xteam.discount.service.rest.purchase.PurchaseService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

@Component
public class PurchaseServiceImpl implements PurchaseService {

    private static final Logger LOG = LoggerFactory.getLogger(PurchaseServiceImpl.class);

    @Value("${rest.purchase.url.purchase.user}")
    private String purchaseByUserUrl;

    @Value("${rest.purchase.url.purchase.product}")
    private String purchaseByProductUrl;

    @Value("${rest.purchase.url.product.productid}")
    private String productByProductIdUrl;

    @Override
    public PurchaseByUser getPurchaseByUser(String username) {
        String url = purchaseByUserUrl.replace("{username}", username);
        return getRestResponse(url, PurchaseByUser.class);
    }

    @Override
    public PurchaseByProduct getPurchaseByProduct(int productId) {
        String url = purchaseByProductUrl.replace("{productId}", String.valueOf(productId));
        return getRestResponse(url, PurchaseByProduct.class);
    }

    @Override
    public ProductByProductId getProductByProductId(int productId) {
        String url = productByProductIdUrl.replace("{productId}", String.valueOf(productId));
        return getRestResponse(url, ProductByProductId.class);
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
