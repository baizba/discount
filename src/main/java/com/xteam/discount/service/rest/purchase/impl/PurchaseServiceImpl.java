package com.xteam.discount.service.rest.purchase.impl;

import com.xteam.discount.model.rest.purchase.*;
import com.xteam.discount.service.rest.purchase.PurchaseService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.lang.reflect.Type;
import java.net.URI;
import java.nio.charset.Charset;

@Component
public class PurchaseServiceImpl implements PurchaseService {

    private static final Logger LOG = LoggerFactory.getLogger(PurchaseServiceImpl.class);

    @Value("${rest.purchase.url.purchase.user}")
    private String purchaseByUserUrl;

    @Value("${rest.purchase.url.purchase.product}")
    private String purchaseByProductUrl;

    @Value("${rest.purchase.url.product.productid}")
    private String productByProductIdUrl;

    @Value(("${rest.users.username}"))
    private String usersByUsernameUrl;

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

    @Override
    public boolean userExists(String username) {
        UsersByUsername usersByUsername = getRestResponse(usersByUsernameUrl, UsersByUsername.class);
        for(User user: usersByUsername.getUsers()) {
            if(user.getUsername() != null && user.getUsername().equals(username)) {
                return true;
            }
        }
        return false;
    }

    private <T> T getRestResponse(String url, Class<T> responseModelType) {
        RestTemplate restTemplate = new RestTemplate();
        T response = restTemplate.getForObject(url, responseModelType);
        LOG.debug("calling REST: " + url + " response is: " + response.toString());
        return response;
    }
}
