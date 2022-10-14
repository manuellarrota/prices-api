package com.zara.prices.application;

import com.zara.prices.domain.Price;
import com.zara.prices.domain.PriceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class PriceServiceImpl implements PriceService {

    private final PriceRepository priceRepository;

    @Autowired
    public PriceServiceImpl(PriceRepository priceRepository) {
        this.priceRepository = priceRepository;
    }

    @Override
    public Price findPriceByDateAndBrandAndProduct(Date applicationDate, Long productId, Integer brandId) {
        return priceRepository.findPriceByDateAndBrandAndProduct(applicationDate, productId, brandId);
    }
}
