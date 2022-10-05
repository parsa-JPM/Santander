package com.example.demosantander.price;

import lombok.AllArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class PriceService {

    private PricesSubscriber pricesSubscriber;
    private PriceListManager priceList;

    private PriceRepo priceRepo;

    @CacheEvict(value = "price_list", allEntries = true)
    public List<Price> updatePrices(String priceCSV) {
        pricesSubscriber.onMessage(priceCSV);
        priceRepo.deleteAll();

        return priceRepo.saveAll(priceList.getUpdatedPrices());
    }

    @Cacheable("price_list")
    public List<Price> getLatestList() {
        return priceRepo.findAll();
    }
}
