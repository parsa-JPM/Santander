package com.example.demosantander.price;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;

@Service
public class PricesSubscriberImpl implements PricesSubscriber {

    private static final double MARGIN_PERCENT = 0.001;

    private PriceListManager priceListManager;

    @Autowired
    public void setPriceList(PriceListManager priceList) {
        this.priceListManager = priceList;
    }

    @Override
    public void onMessage(String message) {

        String[] prices = message.trim().split("\\n");
        List<Price> updatedPrices = Arrays.stream(prices)
                .map(
                        price -> {
                            String[] splitPrice = price.split(",");

                            return Price.builder()
                                    .uid(Integer.parseInt(splitPrice[0]))
                                    .instrument(splitPrice[1].trim())
                                    .bid(applyBidMargin(splitPrice[2]))
                                    .ask(applyAskMargin(splitPrice[3]))
                                    .timestamp(stringToDate(splitPrice[4]))
                                    .build();
                        }).toList();

        priceListManager.setUpdatedPrices(updatedPrices);

    }


    private double applyBidMargin(String bid) {
        double sellPrice = Float.parseFloat(bid);

        return sellPrice - (sellPrice * MARGIN_PERCENT);
    }

    private double applyAskMargin(String ask) {
        double buyPrice = Float.parseFloat(ask);

        return buyPrice + (buyPrice * MARGIN_PERCENT);
    }

    private LocalDateTime stringToDate(String dateStr) {
        String pattern = "dd-MM-yyyy HH:mm:ss:SSS";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
        return LocalDateTime.from(formatter.parse(dateStr.trim()));
    }

}
