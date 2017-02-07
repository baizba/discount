package com.xteam.discount.service.rest.purchase;

import com.xteam.discount.model.rest.purchase.ProductByProductId;
import com.xteam.discount.model.rest.purchase.PurchaseByProduct;
import com.xteam.discount.model.rest.purchase.PurchaseByUser;

/**
 * Interface for communication with the external REST services (purchase interface)
 */
public interface PurchaseService {

    /**
     * Gives back purchases made by the given user
     * @param userName
     * @return list of the users purchases
     */
    public PurchaseByUser getPurchaseByUser(String userName);

    /**
     * Gives back purchases by product
     * @param productId
     * @return List of users and the dates when these users purchased the product
     */
    public PurchaseByProduct getPurchaseByProduct(int productId);

    /**
     * Gives back information about single product
     * @param productId
     * @return information about the product
     */
    public ProductByProductId getProductByProductId(int productId);

}
