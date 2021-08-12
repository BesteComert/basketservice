package com.besteco.basketservice.entity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

import com.besteco.basketservice.model.BasketInfo;
import com.besteco.basketservice.model.BasketProduct;
import org.springframework.data.annotation.Id;
import org.springframework.data.couchbase.core.mapping.Document;
import org.springframework.data.couchbase.core.mapping.Field;
import org.springframework.data.couchbase.core.mapping.id.GeneratedValue;
import org.springframework.data.couchbase.core.mapping.id.GenerationStrategy;
import org.springframework.stereotype.Indexed;


@Document
public class Basket {

    @Id
    @GeneratedValue(strategy = GenerationStrategy.UNIQUE)
    private String id;

    @Field
    private Long userId;

    @Field
    private ArrayList<BasketProduct> basketProducts;

    @Field
    private BasketInfo basketInfo;

    public void setBasketProductOneByOne(BasketProduct basketProduct) {
        basketProducts.add(basketProduct);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public List<BasketProduct> getBasketProducts() {
        return basketProducts;
    }

    public void setBasketProducts(BasketProduct basketProduct) {
        this.getBasketProducts().add(basketProduct);
    }

    public BasketInfo getBasketInfo() {
        return basketInfo;
    }

    public void setBasketInfo(BasketInfo basketInfo) {
        this.basketInfo = basketInfo;
    }

    public Basket() {
        super();
    }
    public Basket(Long userId) {
        super();
        this.userId = userId;
        this.basketProducts= new ArrayList<>();
    }
    @Override
    public String toString() {
        return "Basket [id=" + id + ", userId=" + userId + ", basketProducts=" + basketProducts + ", basketInfo="
                + basketInfo + "]";
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Basket basket = (Basket) o;
        return Objects.equals(id, basket.id) && Objects.equals(userId, basket.userId) && Objects.equals(basketProducts, basket.basketProducts) && Objects.equals(basketInfo, basket.basketInfo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, userId, basketProducts, basketInfo);
    }
}

