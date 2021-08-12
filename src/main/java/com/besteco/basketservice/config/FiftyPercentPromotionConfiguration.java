package com.besteco.basketservice.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FiftyPercentPromotionConfiguration {

    @Value("${promotion.fiftypercent.activation}")
    private boolean fiftyActivation;

    @Value("${promotion.fiftypercent.code}")
    private int fiftyCode;

    public boolean isFiftyActivation() {
        return fiftyActivation;
    }

    public int getFiftyCode() {
        return fiftyCode;
    }
}
