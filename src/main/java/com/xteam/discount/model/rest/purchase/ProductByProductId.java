package com.xteam.discount.model.rest.purchase;

public class ProductByProductId {

    private Product product;

    public Product getProduct() {
        if(product == null) {
            product = new Product();
        }
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    @Override
    public String toString() {
        return "ProductByProductId[" + getProduct().toString() + "]";
    }
}
