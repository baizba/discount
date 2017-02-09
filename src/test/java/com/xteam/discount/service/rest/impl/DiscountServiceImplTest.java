package com.xteam.discount.service.rest.impl;

import com.xteam.discount.model.rest.PopularPurchase;
import com.xteam.discount.model.rest.purchase.*;
import com.xteam.discount.service.rest.DiscountService;
import com.xteam.discount.service.rest.purchase.PurchaseService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DiscountServiceImplTest {

    @MockBean
    private PurchaseService purchaseService;

    @Autowired
    private DiscountService discountService;

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
    public void contextLoads() {
        List<PopularPurchase> response = discountService.getPopularPurchasesByUsername("Brano");
        assertEquals(1, response.size());

        PopularPurchase popularPurchase = response.get(0);
        assertEquals(1234, popularPurchase.getId());
        assertEquals(100, popularPurchase.getPrice());
        assertEquals(10, popularPurchase.getSize());

        assertTrue(popularPurchase.getRecent().containsAll(Arrays.asList(new String[] {"Brano", "John"})));
    }

}
