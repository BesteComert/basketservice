package com.besteco.basketservice.promotionFactoryModel;

import com.besteco.basketservice.model.BasketInfo;
import com.besteco.basketservice.model.BasketProduct;
import com.besteco.basketservice.service.Promotion;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import java.util.List;

public class ShippingPromotion implements Promotion {

    @Override
    public Double checkPromotions(PromotionModel promotionModel, List<BasketProduct> basketProducts)
    {
        Double discount = 0d;

        double subTotal = basketProducts.stream()
                .filter(x -> x.getProduct().getPromotions().contains(promotionModel.getPromotionCode()))
                .map(x -> x.getProduct().getUnitPrice() * x.getCount())
                .reduce(0d, Double::sum);

        if (promotionModel.isPromotionActivation() && promotionModel.getPromotionLimit() <= subTotal) {
            discount += promotionModel.getPromotionPrice();
        }

        return discount*(-1);
    }
}
