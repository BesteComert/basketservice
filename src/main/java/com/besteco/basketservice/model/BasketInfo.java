package com.besteco.basketservice.model;


import java.util.List;

public class BasketInfo {

    private Double subTotal;

    private Double shipping;

    private List<Double> discount;

    private Double total;

    public Double getSubTotal() {
        return subTotal;
    }

    public BasketInfo() {
    }

    public Double getShipping() {
        return shipping;
    }

    public void setShipping(Double shipping) {
        this.shipping = shipping;
    }

    public void setSubTotal(Double subTotal) {
        this.subTotal = subTotal;
    }


    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public List<Double> getDiscount() {
        return discount;
    }

    public void setDiscount(List<Double> discount) {
        this.discount = discount;
    }

    public BasketInfo(Double subTotal, Double shipping, List<Double> discount, Double total) {
        this.subTotal = subTotal;
        this.shipping = shipping;
        this.discount = discount;
        this.total = total;
    }
}

