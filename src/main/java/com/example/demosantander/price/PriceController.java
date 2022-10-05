package com.example.demosantander.price;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
public class PriceController {

    private PriceService priceService;


    @PostMapping(value = "/new/prices", consumes = "text/plain")
    public String newPrices(@RequestBody String prices) {
        priceService.updatePrices(prices);

        return "Prices have been updated";
    }

    @GetMapping("/new/prices")
    public List<Price> newPrices() {
        return priceService.getLatestList();
    }
}
