package com.besteco.basketservice.promotionFactoryModel;

import com.besteco.basketservice.service.Promotion;

public class PromotionFactory {

    public Promotion createPromotion(PromotionType promotionType){
        Promotion promotion;
        switch (promotionType){
            case SHIPPING:
                promotion = new ShippingPromotion();
                break;
            case FIFTYPERCENT:
                promotion = new FiftyPercentPromotion();
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + promotionType);
        }
        return promotion;
    }
}
