package com.besteco.basketservice.model;


import java.util.Objects;

public class BasketProduct {

    private Product product;
    private Integer count;

    public BasketProduct(Product product) {
        super();
        this.product = product;
        count=0;
    }

    public void countPlusOne() {

        this.count++;
    }

    public void countMinusOne() {

        this.count--;
        if (this.count < 0)
            this.count=0;
    }

    public Long getProductId() {
        return product.getId();
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public BasketProduct() {
        super();
    }

    @Override
    public String toString() {
        return "BasketProduct [product=" + product + ", count=" + count + "]";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BasketProduct that = (BasketProduct) o;
        return Objects.equals(product, that.product) && Objects.equals(count, that.count);
    }

    @Override
    public int hashCode() {
        return Objects.hash(product, count);
    }
}


