package com.besteco.basketservice.promotionFactoryModel;

import com.besteco.basketservice.model.BasketProduct;
import com.besteco.basketservice.service.Promotion;

import java.util.List;

public class FiftyPercentPromotion implements Promotion {

    @Override
    public Double checkPromotions(PromotionModel promotionModel, List<BasketProduct> basketProducts)
    {
        double discount = 0d;

        if (promotionModel.isPromotionActivation()) {
            discount = basketProducts.stream()
                    .filter(x -> x.getProduct().getPromotions().contains(promotionModel.getPromotionCode()))
                    .map( x -> x.getProduct().getUnitPrice()*0.5*x.getCount())
                    .reduce(0d, Double::sum);
        }

        return discount*(-1);
    }
}
