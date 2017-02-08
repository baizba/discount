package com.xteam.discount.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.xteam.discount.model.rest.PopularPurchase;
import com.xteam.discount.service.rest.DiscountService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
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

    @RequestMapping(value = "/api/recent_purchases/{username:.+}", produces = MediaType.APPLICATION_JSON_VALUE)
    @Cacheable(cacheNames = "popularPurchasesByUser")
    public ResponseEntity<String> getPopularPurchasesByUsername(@PathVariable String username) {
        LOG.debug("retrieving popular purchases for user {}", username);
        List<PopularPurchase> popularPurchases = discountService.getPopularPurchasesByUsername(username);
        LOG.debug("popular purchases REST call result: {}", popularPurchases);

        //when nothing found for username show only error text
        //write manually to the response, flush and close so that the spring MVC does not add something
        if (CollectionUtils.isEmpty(popularPurchases)) {
            return ResponseEntity.ok("User with username of '"+ username + "' was not found");
        }

        //otherwise just unmarshal the JSON objects to string and return it
        try {
            ObjectMapper mapper = new ObjectMapper();
            String jsonResponse = mapper.writeValueAsString(popularPurchases);
            return ResponseEntity.ok(jsonResponse);
        } catch (JsonProcessingException e) {
            LOG.error("catastrophic error while sending response ", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("error");
        }
    }

    //TODO this should be extracted for integration test
    private List<PopularPurchase> getProductsForTest() {
        return new ArrayList<PopularPurchase>() {
            {
                add(new PopularPurchase() {
                    {
                        setId(123);
                        setFace("o.o");
                        setPrice(123.56);
                        setSize(23);
                        setRecent(new ArrayList<String>(3) {
                            {
                                add("yoko");
                                add("meli");
                                add("igo");
                            }
                        });
                    }
                });

                add(new PopularPurchase() {
                    {
                        setId(456);
                        setFace("x.x");
                        setPrice(145.57);
                        setSize(28);
                        setRecent(new ArrayList<String>(3) {
                            {
                                add("rale");
                                add("zloki");
                                add("gagi");
                            }
                        });
                    }
                });
            }
        };
    }
}
