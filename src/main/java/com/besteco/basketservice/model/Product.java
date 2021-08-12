package com.besteco.basketservice.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class Product {
    private Long id;
    private String name;
    private Double unitPrice;
    private Integer unitInStock;
    private List<Integer> promotions = new ArrayList<>(Arrays.asList(101));

    public Product(Long id, String name, Double unitPrice, Integer unitInStock, List<Integer> promotions) {
        this.id = id;
        this.name = name;
        this.unitPrice = unitPrice;
        this.unitInStock = unitInStock;
        this.promotions = promotions;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(Double unitPrice) {
        this.unitPrice = unitPrice;
    }

    public Integer getUnitInStock() {
        return unitInStock;
    }

    public void setUnitInStock(Integer unitInStock) {
        this.unitInStock = unitInStock;
    }

    public List<Integer> getPromotions() {
        return promotions;
    }

    public void setPromotions(List<Integer> promotions) {
        this.promotions = promotions;
    }

    public Product() {
        super();
    }

    @Override
    public String toString() {
        return "Product [id=" + id + ", name=" + name + ", unitPrice=" + unitPrice + ", unitInStock=" + unitInStock
                + ", promotions=" + promotions + "]";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return Objects.equals(id, product.id) && Objects.equals(name, product.name) && Objects.equals(unitPrice,
                product.unitPrice) && Objects.equals(unitInStock, product.unitInStock) && Objects.equals(promotions,
                product.promotions);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, unitPrice, unitInStock, promotions);
    }
}

