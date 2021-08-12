package com.besteco.basketservice.promotionFactoryModel;

import java.util.Objects;

public class PromotionModel {

    private boolean promotionActivation;

    private int promotionCode;

    private double promotionPrice;

    private double promotionLimit;

    public PromotionModel(boolean promotionActivation, int promotionCode){
        this.promotionActivation = promotionActivation;
        this.promotionCode = promotionCode;
    }

    public PromotionModel(boolean promotionActivation, int promotionCode, double promotionPrice,
                          double promotionLimit) {
        this.promotionActivation = promotionActivation;
        this.promotionCode = promotionCode;
        this.promotionPrice = promotionPrice;
        this.promotionLimit = promotionLimit;
    }

    public boolean isPromotionActivation() {
        return promotionActivation;
    }

    public void setPromotionActivation(boolean promotionActivation) {
        this.promotionActivation = promotionActivation;
    }

    public int getPromotionCode() {
        return promotionCode;
    }

    public void setPromotionCode(int promotionCode) {
        this.promotionCode = promotionCode;
    }

    public double getPromotionPrice() {
        return promotionPrice;
    }

    public void setPromotionPrice(double promotionPrice) {
        this.promotionPrice = promotionPrice;
    }

    public double getPromotionLimit() {
        return promotionLimit;
    }

    public void setPromotionLimit(double promotionLimit) {
        this.promotionLimit = promotionLimit;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PromotionModel that = (PromotionModel) o;
        return promotionActivation == that.promotionActivation && promotionCode == that.promotionCode && Double.compare(that.promotionPrice, promotionPrice) == 0 && Double.compare(that.promotionLimit, promotionLimit) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(promotionActivation, promotionCode, promotionPrice, promotionLimit);
    }

    @Override
    public String toString() {
        return "PromotionModel{" +
                "promotionActivation=" + promotionActivation +
                ", promotionCode=" + promotionCode +
                ", promotionPrice=" + promotionPrice +
                ", promotionLimit=" + promotionLimit +
                '}';
    }
}
