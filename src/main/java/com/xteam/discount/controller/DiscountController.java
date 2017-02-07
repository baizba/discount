package com.xteam.discount.controller;

import com.xteam.discount.model.Product;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;


@RestController
public class DiscountController {

    @RequestMapping("/api/recent_purchases/{username}")
    public List<Product> greeting(@PathVariable String username) {
        return new ArrayList<Product>(){
            {
                add(new Product(){
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

                add(new Product(){
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
