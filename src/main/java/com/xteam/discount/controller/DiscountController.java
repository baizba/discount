package com.xteam.discount.controller;

import com.xteam.discount.model.rest.PopularPurchase;
import com.xteam.discount.service.rest.DiscountService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;


/**
 * Main entrypoint to our backend application
 */
@RestController
public class DiscountController {

    private static final Logger LOG = LoggerFactory.getLogger(DiscountController.class);

    @Autowired
    private DiscountService discountService;

    @RequestMapping("/api/recent_purchases/{username:.+}")
    public List<PopularPurchase> getPopularPurchasesByUsername(@PathVariable String username) {
        LOG.debug("retrieving popular purchases for user {}", username);
        List<PopularPurchase> popularPurchases = discountService.getPopularPurchasesByUsername(username);
        LOG.debug("popular purchases REST call result: {}", popularPurchases);
        return popularPurchases;
    }

    //TODO this should be extracted for integration test
    private List<PopularPurchase> getProductsForTest() {
        return new ArrayList<PopularPurchase>(){
            {
                add(new PopularPurchase(){
                    {
                        setId(123);
                        setFace("o.o");
                        setPrice(123.56);
                        setSize(23);
                        setRecent(new ArrayList<String>(3){
                            {
                                add("yoko");
                                add("meli");
                                add("igo");
                            }
                        });
                    }
                });

                add(new PopularPurchase(){
                    {
                        setId(456);
                        setFace("x.x");
                        setPrice(145.57);
                        setSize(28);
                        setRecent(new ArrayList<String>(3){
                            {
                                add("rale");
                                add("zloki");
                                add("gagi");
                            }
                        });
                    }
                });
            }
        } ;
    }
}
