package com.xteam.discount.service.rest.purchase;

import com.xteam.discount.model.rest.PopularPurchase;

import java.util.List;

/**
 * This Service is suposed to combine all three calls to the "external" REST interface (purchase interface)
 * and gives us back the form we need it
 */
public interface DiscountService {

    /**
     * Gives back the popular purchases for the given user
     * @param username
     * @return the list of popular purchases
     */
    public List<PopularPurchase> getPopularPurchasesByUsername(String username);
}
