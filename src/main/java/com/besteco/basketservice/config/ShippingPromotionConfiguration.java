package com.besteco.basketservice.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ShippingPromotionConfiguration {

    @Value("${promotion.shipping.activation}")
    public boolean shippingActivation;

    @Value("${promotion.shipping.code}")
    public int shippingCode;

    @Value("${promotion.shipping.price}")
    public double shippingPrice;

    @Value("${promotion.shipping.limit}")
    public double shippingLimit;

    public boolean getShippingActivation() {
        return shippingActivation;
    }

    public int getShippingCode() {
        return shippingCode;
    }

    public double getShippingPrice() {
        return shippingPrice;
    }

    public double getShippingLimit() {
        return shippingLimit;
    }
}
