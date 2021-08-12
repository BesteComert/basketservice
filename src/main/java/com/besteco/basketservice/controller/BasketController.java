package com.besteco.basketservice.controller;

import java.util.List;

import com.besteco.basketservice.model.Product;
import com.besteco.basketservice.service.BasketService;
import org.springframework.web.bind.annotation.*;

import com.besteco.basketservice.entity.Basket;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/basket")
public class BasketController {

    private RestTemplate restTemplate;
    private final BasketService basketService;

    public BasketController(BasketService basketService) {
        this.basketService = basketService;
    }

    @GetMapping("/baskets")
    public List<Basket> getAllBaskets() {

        return basketService.findAllBaskets();
    }

    @GetMapping("/product/{id}")
    public List<Long> getUsersByProductId(@RequestParam Long id) {

return basketService.findAllUsersWithProductId(id);
    }

    @GetMapping("/{id}")
    public Basket getBasketByUserId(@RequestParam Long id) {

    return basketService.findBasketByUserId(id);
    }

    @PostMapping("/add/{userId}")
    public Basket createBasket(@PathVariable("userId") Long userId) {

        return basketService.createBasket(userId);
    }

    //Request to Product-Service
    @PostMapping("/add/{userId}/{productId}")
    public Basket addProduct(@PathVariable("userId") Long userId, @PathVariable("productId") Long productId) {
        Product product = restTemplate.getForObject("http://localhost:9092/product/"
                + productId, Product.class);

        return basketService.addProductWithProduct(userId,product);
    }

    @PostMapping("/delete/{userId}/{productId}")
    public Basket deleteProduct(@PathVariable("userId") Long userId, @PathVariable("productId") Long productId) {
        Product product = restTemplate.getForObject("http://localhost:9092/product/"
                + productId, Product.class);

        return basketService.deleteProductWithProduct(userId,product);
    }
}

