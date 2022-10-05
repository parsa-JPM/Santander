package com.example.demosantander.price;

import lombok.Data;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.RequestScope;

import java.util.ArrayList;
import java.util.List;


@Component
@RequestScope
@Data
public class PriceListManager {
    private List<Price> updatedPrices = new ArrayList<>();
}
