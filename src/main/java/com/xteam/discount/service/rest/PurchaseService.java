package com.xteam.discount.service.rest;

import com.xteam.discount.model.rest.purchase.Purchase;

/**
 * Interface for communication with the external REST services (purchase interface)
 */
public interface PurchaseService {

    /**
     * Gives back purchases made by the given user
     * @param userName
     * @return list of the users purchases
     */
    public Purchase getPurchaseByUser(String userName);
}
