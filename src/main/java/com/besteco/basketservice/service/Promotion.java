package com.besteco.basketservice.service;

import com.besteco.basketservice.model.BasketInfo;
import com.besteco.basketservice.model.BasketProduct;
import com.besteco.basketservice.promotionFactoryModel.PromotionModel;

import java.util.List;

public interface Promotion {

    public Double checkPromotions(PromotionModel promotionModel, List<BasketProduct> basketProducts);
}
