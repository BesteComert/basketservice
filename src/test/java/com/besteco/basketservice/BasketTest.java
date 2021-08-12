package com.besteco.basketservice;

import com.besteco.basketservice.config.FiftyPercentPromotionConfiguration;
import com.besteco.basketservice.config.ShippingPromotionConfiguration;
import com.besteco.basketservice.entity.Basket;
import com.besteco.basketservice.model.BasketProduct;
import com.besteco.basketservice.model.Product;
import com.besteco.basketservice.repository.BasketRepository;
import com.besteco.basketservice.service.BasketService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;

@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
class BasketTest {

    @InjectMocks
    BasketService basketService;

    @Mock
    BasketRepository mockRepository;

    @Mock
    ShippingPromotionConfiguration shippingPromotionConfiguration;

    @Mock
    FiftyPercentPromotionConfiguration fiftyPercentPromotionConfiguration;

    Product product1;
    Product product2;
    Product product3;
    @BeforeEach
    public void setUp(){
        List<Integer> promotions = Arrays.asList(101,102);
        List<Integer> shippingPromotions = Arrays.asList(101);
        product1 = new Product(1L, "Tv", 989.99, 67, promotions);
        product2 = new Product(2L, "Mug", 89.99, 29, promotions);
        product3= new Product(3L, "Bag", 59.99, 61, shippingPromotions);
        given(shippingPromotionConfiguration.getShippingCode()).willReturn(101);
        given(shippingPromotionConfiguration.getShippingActivation()).willReturn(true);
        given(shippingPromotionConfiguration.getShippingLimit()).willReturn(100d);
        given(shippingPromotionConfiguration.getShippingPrice()).willReturn(4.99);
        given(fiftyPercentPromotionConfiguration.getFiftyCode()).willReturn(102);
        given(fiftyPercentPromotionConfiguration.isFiftyActivation()).willReturn(true);
    }
    @Test
    @Order(1)
     void addProductToBasket() {

        // GIVEN
        Basket basket = new Basket(201L);
        basket.setId("1111");
        given(mockRepository.findByUserId(201L)).willReturn(new ArrayList<Basket>(Arrays.asList(basket)));
        given(mockRepository.save(new Basket(201L))).willReturn(basket);

        // WHEN
        basketService.addProductWithProduct(201L, product1);

        // THEN
        assertTrue(basketService.findBasketByUserId(201L).getBasketProducts().stream()
                .map(BasketProduct::getProduct)
                .collect(Collectors.toList())
                .contains(product1));
    }

    @Test
    @Order(2)
    void deleteProductFromBasket() {

        // GIVEN
        Basket basket = new Basket(202L);
        basket.setId("1112");
        given(mockRepository.findByUserId(202L)).willReturn(new ArrayList<Basket>(Arrays.asList(basket)));
        given(mockRepository.save(new Basket(202L))).willReturn(basket);

        // WHEN
        basketService.addProductWithProduct(202L, product2);
        basketService.deleteProductWithProduct(202L, product2);

        // THEN

        assertFalse(basketService.findBasketByUserId(202L).getBasketProducts().stream()
                .map(BasketProduct::getProduct)
                .collect(Collectors.toList())
                .contains(product2));
    }

    @Test
    @Order(3)
    void addProductFromBasketCheckCount() {

        // GIVEN
        Basket basket = new Basket(203L);
        basket.setId("1113");
        given(mockRepository.findByUserId(203L)).willReturn(new ArrayList<Basket>(Arrays.asList(basket)));
        given(mockRepository.save(new Basket(203L))).willReturn(basket);

        // WHEN
        basketService.addProductWithProduct(203L, product2);
        basketService.addProductWithProduct(203L, product2);
        BasketProduct basketProduct =
                basketService.findBasketByUserId(203L).getBasketProducts().stream().filter(x -> x.getProduct().equals(product2)).findFirst().orElse(null);

        // THEN
        assertEquals(2, basketProduct.getCount());
    }

    @Test
    @Order(4)
    void deleteProductFromBasketCheckCount() {
        // GIVEN
        Basket basket = new Basket(204L);
        basket.setId("1114");
        given(mockRepository.findByUserId(204L)).willReturn(new ArrayList<Basket>(Arrays.asList(basket)));
        given(mockRepository.save(new Basket(204L))).willReturn(basket);
        // WHEN
        basketService.addProductWithProduct(204L, product1);
        basketService.addProductWithProduct(204L, product1);
        basketService.deleteProductWithProduct(204L, product1);
        BasketProduct basketProduct =
                basketService.findBasketByUserId(204L).getBasketProducts().stream().filter(x -> x.getProduct().equals(product1)).findFirst().orElse(null);
        // THEN
        assertEquals(1, basketProduct.getCount());
    }

    @Test
    @Order(5)
     void checkFiftyPromotion() {

        // GIVEN
        Basket basket = new Basket(205L);
        basket.setId("1115");
        given(mockRepository.findByUserId(205L)).willReturn(new ArrayList<Basket>(Arrays.asList(basket)));
        given(mockRepository.save(new Basket(205L))).willReturn(basket);

        // WHEN
        basketService.addProductWithProduct(205L, product2);

        // THEN
        assertEquals( Arrays.asList(-44.995), basketService.findBasketByUserId(205L).getBasketInfo().getDiscount());
    }

    @Test
    @Order(6)
    void checkShippingPromotion(){

        //GIVEN
        Basket basket2 = new Basket(206L);
        basket2.setId("1116");
        given(mockRepository.findByUserId(206L)).willReturn(new ArrayList<Basket>(Arrays.asList(basket2)));
        given(mockRepository.save(new Basket(206L))).willReturn(basket2);

        //WHEN
        basketService.addProductWithProduct(206L, product3);

        //THEN
        assertEquals( new ArrayList<>(), basketService.findBasketByUserId(206L).getBasketInfo().getDiscount());
    }

    @Test
    @Order(7)
    void checkBothPromotions(){

        //GIVEN
        Basket basket2 = new Basket(207L);
        basket2.setId("1117");
        given(mockRepository.findByUserId(207L)).willReturn(new ArrayList<Basket>(Arrays.asList(basket2)));
        given(mockRepository.save(new Basket(207L))).willReturn(basket2);

        //WHEN
        basketService.addProductWithProduct(207L, product1);

        //THEN
        assertEquals( new ArrayList<>(Arrays.asList(-494.995,-4.99)),
                basketService.findBasketByUserId(207L).getBasketInfo().getDiscount());
    }

    @Test
    @Order(8)
    void checkSubTotal(){

        //GIVEN
        Basket basket2 = new Basket(207L);
        basket2.setId("1117");
        given(mockRepository.findByUserId(207L)).willReturn(new ArrayList<Basket>(Arrays.asList(basket2)));
        given(mockRepository.save(new Basket(207L))).willReturn(basket2);

        //WHEN
        basketService.addProductWithProduct(207L, product1);

        //THEN
        assertEquals( 989.99, basketService.findBasketByUserId(207L).getBasketInfo().getSubTotal());
    }

    @Test
    @Order(8)
    void checkTotal(){

        //GIVEN
        Basket basket2 = new Basket(207L);
        basket2.setId("1117");
        given(mockRepository.findByUserId(207L)).willReturn(new ArrayList<Basket>(Arrays.asList(basket2)));
        given(mockRepository.save(new Basket(207L))).willReturn(basket2);

        //WHEN
        basketService.addProductWithProduct(207L, product1);

        //THEN
        assertEquals( 490.005, basketService.findBasketByUserId(207L).getBasketInfo().getTotal());
    }
}

