package com.xteam.discount;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.xteam.discount.controller.DiscountController;
import com.xteam.discount.model.rest.PopularPurchase;
import com.xteam.discount.model.rest.purchase.*;
import com.xteam.discount.service.rest.DiscountService;
import com.xteam.discount.service.rest.purchase.PurchaseService;

import static org.junit.Assert.*;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.mockito.Mockito.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.util.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DiscountApplicationIntegrationTests {

    @MockBean
    private PurchaseService purchaseService;

    @Autowired
    private DiscountService discountService;

    @Autowired
    DiscountController discountController;

    @Before
    public void setup() {
        //mock the first call to outside interface
        when(purchaseService.getPurchaseByUser("Brano")).thenReturn(new PurchaseByUser() {
            {
                setPurchases(new ArrayList<PurchaseByUserEntry>() {
                    {
                        add(new PurchaseByUserEntry() {
                            {
                                setId(1);
                                setProductId(1234);
                                setUsername("Brano");
                                setDate(new Date());
                            }
                        });
                    }
                });
            }
        });

        //mock the second call to outside interface
        when(purchaseService.getPurchaseByProduct(1234)).thenReturn(new PurchaseByProduct() {
            {
                setPurchases(new ArrayList<PurchaseByProductEntry>() {
                    {
                        add(new PurchaseByProductEntry() {
                            {
                                setId(6);
                                setProductId(1234);
                                setUsername("Brano");
                                setDate(new Date());
                            }
                        });
                        add(new PurchaseByProductEntry() {
                            {
                                setId(4);
                                setProductId(5678);
                                setUsername("John");
                                setDate(new Date());
                            }
                        });
                    }
                });
            }
        });

        //mock the final calls for the products
        when(purchaseService.getProductByProductId(1234)).thenReturn(new ProductByProductId() {
            {
                setProduct(new Product() {
                    {
                        setId(1234);
                        setFace("xox");
                        setSize(10);
                        setPrice(100);
                    }
                });
            }
        });
        when(purchaseService.getProductByProductId(5678)).thenReturn(new ProductByProductId() {
            {
                setProduct(new Product() {
                    {
                        setId(5678);
                        setFace("yoy");
                        setSize(20);
                        setPrice(200);
                    }
                });
            }
        });
    }

    @Test
    public void testDiscountServiceSuccessfullCall() {
        List<PopularPurchase> popularPurchases = discountService.getPopularPurchasesByUsername("Brano");
        assertEquals(1, popularPurchases.size());
        assertPopularPurchaseResponse(popularPurchases);
    }

    @Test
    public void testDiscountControllerSuccessfullCall() {
        ResponseEntity<String> response = discountController.getPopularPurchasesByUsername("Brano");
        ObjectMapper mapper = new ObjectMapper();
        List<PopularPurchase> popularPurchases = null;
        //check if you can convert the object back to json
        //then you know that the json is valid
        try {
            popularPurchases = mapper.readValue(response.getBody(), new TypeReference<List<PopularPurchase>>() {});
        } catch (IOException e) {
            fail("test failed because json could not be converted to POJO " + e.getMessage());
        }
        //check the values again to be sure nothing has changed
        assertPopularPurchaseResponse(popularPurchases);
    }

    @Test
    public void testDiscountControllerNoUser() {
        //i used a non existing username, we should just get a message
        ResponseEntity<String> response = discountController.getPopularPurchasesByUsername("Sandra");
        assertEquals("User with username of 'Sandra' was not found", response.getBody());
    }

    private void assertPopularPurchaseResponse(List<PopularPurchase> popularPurchases) {
        PopularPurchase popularPurchase = popularPurchases.get(0);
        assertEquals(1234, popularPurchase.getId());
        assertEquals(100, popularPurchase.getPrice());
        assertEquals(10, popularPurchase.getSize());
        assertTrue(popularPurchase.getRecent().containsAll(Arrays.asList(new String[] {"Brano", "John"})));
    }

}
