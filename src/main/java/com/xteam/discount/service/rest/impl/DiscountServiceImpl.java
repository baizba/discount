package com.xteam.discount.service.rest.impl;

import com.xteam.discount.model.rest.PopularPurchase;
import com.xteam.discount.model.rest.purchase.Product;
import com.xteam.discount.model.rest.purchase.ProductByProductId;
import com.xteam.discount.model.rest.purchase.PurchaseByProduct;
import com.xteam.discount.model.rest.purchase.PurchaseByUser;
import com.xteam.discount.service.rest.purchase.PurchaseService;
import com.xteam.discount.service.rest.DiscountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class DiscountServiceImpl implements DiscountService {

    @Autowired
    private PurchaseService purchaseService;

    /**
     * @see DiscountService#getPopularPurchasesByUsername(String)
     * @param username
     * @return
     */
    @Override
    public List<PopularPurchase> getPopularPurchasesByUsername(String username) {
        PurchaseByUser purchaseByUser = purchaseService.getPurchaseByUser(username);

        /*
        1. First get the purchases for the given user name
        and map it to our response model the way we want to. We can already use the product id which we get here.
         */
        List<PopularPurchase> popularPurchases = purchaseByUser.getPurchases().stream().map(purchaseByUserEntry -> {
            PopularPurchase popularPurchase = new PopularPurchase();
            int productId = purchaseByUserEntry.getProductId();
            popularPurchase.setId(productId);

            /*
             2. Get purchases by each product and obtain the list of users who bought the product and attach these
             users to our response
             */
            PurchaseByProduct purchaseByProduct = purchaseService.getPurchaseByProduct(productId);
            purchaseByProduct.getPurchases().forEach(purchaseByProductEntry ->
                    popularPurchase.getRecent().add(purchaseByProductEntry.getUsername()));

            //3. get the product specific info and attach it to our response
            ProductByProductId productByProductId = purchaseService.getProductByProductId(productId);
            Product product = productByProductId.getProduct();
            popularPurchase.setPrice(product.getPrice());
            popularPurchase.setSize(product.getSize());
            popularPurchase.setFace(product.getFace());

            return popularPurchase;
        }).collect(Collectors.toList());

        //sort the list so that the products whoe are purchased the most come to top
        Collections.sort(popularPurchases, (o1, o2) -> {
            return Integer.compare(o2.getRecent().size(), o1.getRecent().size());
        });

        return popularPurchases;
    }
}
