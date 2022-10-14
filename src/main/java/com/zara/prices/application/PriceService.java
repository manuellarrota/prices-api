package com.zara.prices.application;

import com.zara.prices.domain.Price;

import java.util.Date;


public interface PriceService {

    Price findPriceByDateAndBrandAndProduct(
            Date applicationDate,
            Long productId,
            Integer brandId);
}
