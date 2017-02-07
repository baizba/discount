package com.xteam.discount.controller;

import com.xteam.discount.model.rest.PopularPurchase;
import com.xteam.discount.service.rest.DiscountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;


@RestController
public class DiscountController {

    @Autowired
    private DiscountService discountService;

    @RequestMapping("/api/recent_purchases/{username:.+}")
    public List<PopularPurchase> greeting(@PathVariable String username) {
        List<PopularPurchase> popularPurchases = discountService.getPopularPurchasesByUsername(username);
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
