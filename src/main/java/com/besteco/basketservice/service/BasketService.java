package com.besteco.basketservice.service;

import com.besteco.basketservice.config.FiftyPercentPromotionConfiguration;
import com.besteco.basketservice.config.ShippingPromotionConfiguration;
import com.besteco.basketservice.entity.Basket;
import com.besteco.basketservice.model.BasketInfo;
import com.besteco.basketservice.model.BasketProduct;
import com.besteco.basketservice.model.Product;
import com.besteco.basketservice.promotionFactoryModel.FiftyPercentPromotion;
import com.besteco.basketservice.promotionFactoryModel.PromotionFactory;
import com.besteco.basketservice.promotionFactoryModel.PromotionModel;
import com.besteco.basketservice.promotionFactoryModel.PromotionType;
import com.besteco.basketservice.repository.BasketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BasketService {

    @Autowired
    ShippingPromotionConfiguration shippingPromotionConfiguration;

    @Autowired
    FiftyPercentPromotionConfiguration fiftyPercentPromotionConfiguration;

    @Autowired
    BasketRepository basketRepository;

    public List<Long> findAllUsersWithProductId(Long id) {

        List<Long> userIds = new ArrayList<>();

        List<Basket> baskets = basketRepository.findAll();

        for (Basket basket : baskets) {
            List<BasketProduct> basketProducts = basket.getBasketProducts();

            for (BasketProduct basketProduct : basketProducts) {
                if (basketProduct.getProductId().equals(id))
                    userIds.add(basket.getUserId());
            }
        }
        return userIds;
    }

    public List<Basket> findAllBaskets() {

        return basketRepository.findAll();
    }

    public Basket findBasketByUserId(Long id) {

        return basketRepository.findByUserId(id).stream()
                .findFirst()
                .orElse(null);
    }

    public Basket createBasket(Long userId) {

        Basket basket = findBasketByUserId(userId);
        if (basket != null)
            return null;
        return basketRepository.save(new Basket(userId));
    }

    public BasketInfo calculateBasketInfo(List<BasketProduct> basketProducts) {

        double subTotal = basketProducts.stream()
                .map(x -> x.getProduct().getUnitPrice() * x.getCount())
                .reduce(0d, Double::sum);

        double discount = 0;

        PromotionModel fiftyPromotionModel = new PromotionModel(fiftyPercentPromotionConfiguration.isFiftyActivation(),
                fiftyPercentPromotionConfiguration.getFiftyCode());

        PromotionModel shippingPromotionModel= new PromotionModel(shippingPromotionConfiguration.getShippingActivation(),
                shippingPromotionConfiguration.getShippingCode(), shippingPromotionConfiguration.getShippingPrice(),
                shippingPromotionConfiguration.getShippingLimit());

        Promotion fiftyPromotion = new PromotionFactory().createPromotion(PromotionType.FIFTYPERCENT);
        Promotion shippingPromotion = new PromotionFactory().createPromotion(PromotionType.SHIPPING);

        List<Double> discounts= new ArrayList<>();
        if(fiftyPromotion.checkPromotions(fiftyPromotionModel,basketProducts) != 0)
            discounts.add(fiftyPromotion.checkPromotions(fiftyPromotionModel,basketProducts));
        if(shippingPromotion.checkPromotions(shippingPromotionModel,basketProducts) != 0)
            discounts.add(shippingPromotion.checkPromotions(shippingPromotionModel,basketProducts));

        double totalPrice = subTotal + discounts.stream().reduce(0d,Double::sum);

        return new BasketInfo(subTotal, shippingPromotionConfiguration.getShippingPrice(), discounts, totalPrice);
    }

    public Basket addProductWithProduct(Long userId, Product product) {

        Basket basket = basketRepository.findByUserId(userId).stream()
                .filter(x -> x.getUserId().equals(userId))
                .findFirst()
                .orElse(createBasket(userId));

        boolean productExists = basket.getBasketProducts().stream()
                .map(BasketProduct::getProduct)
                .anyMatch(x -> x.getId().equals(product.getId()));

        if (!productExists)
            basket.setBasketProducts(new BasketProduct(product));

        basket.getBasketProducts().stream()
                .filter(x -> x.getProduct().equals(product))
                .forEach(BasketProduct::countPlusOne);

        try {
            basketRepository.deleteById(basket.getId());
        } finally {
            basket.setBasketInfo(calculateBasketInfo(basket.getBasketProducts()));
        }
        return basketRepository.save(basket);
    }

    public Basket deleteProductWithProduct(Long userId, Product product) {

        Basket basket = basketRepository.findByUserId(userId).stream()
                .filter(x -> x.getUserId().equals(userId))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Olmayan bir sepetten ürün silinemez."));

        basket.getBasketProducts().stream()
                .filter(x -> x.getProduct().equals(product))
                .collect(Collectors.toList())
                .forEach(BasketProduct::countMinusOne);

        basket.getBasketProducts().stream()
                .filter(x -> x.getCount().equals(0))
                .collect(Collectors.toList())
                .forEach(x -> basket.getBasketProducts().remove(x));

        try {
            basketRepository.deleteById(basket.getId());
        } finally {
            basket.setBasketInfo(calculateBasketInfo(basket.getBasketProducts()));
        }
        return basketRepository.save(basket);
    }
}
