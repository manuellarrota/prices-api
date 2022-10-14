package com.zara.prices.domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

@DataJpaTest
public class PriceRepositoryTest {
    @Autowired
    private PriceRepository priceRepository;
    private final DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.ENGLISH);

    @Test
    public void when_all_arguments_are_ok_then_return_price_case1(){
        Integer brandId = 1;
        Long productId = 35455L;
        String applicationDateString = "2020-06-14 10:00:00";
        Date applicationDate;

        try {
            applicationDate =  df.parse(applicationDateString);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        Price price = priceRepository.findPriceByDateAndBrandAndProduct(applicationDate, productId, brandId);
        Assertions.assertThat(price.getPrice()).isEqualTo(35.50);
    }

    @Test
    public void when_all_arguments_are_ok_then_return_price_case2(){
        Integer brandId = 1;
        Long productId = 35455L;
        String target = "2020-06-14 16:00:00";
        Date applicationDate;
        try {
            applicationDate =  df.parse(target);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        Price price = priceRepository.findPriceByDateAndBrandAndProduct(applicationDate, productId, brandId);
        Assertions.assertThat(price.getPrice()).isEqualTo(25.45);
    }

    @Test
    public void when_all_arguments_are_ok_then_return_price_case3(){
        Integer brandId = 1;
        Long productId = 35455L;
        String target = "2020-06-14 21:00:00";
        Date applicationDate;
        try {
            applicationDate =  df.parse(target);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        Price price = priceRepository.findPriceByDateAndBrandAndProduct(applicationDate, productId, brandId);
        Assertions.assertThat(price.getPrice()).isEqualTo(35.50);
    }

    @Test
    public void when_all_arguments_are_ok_then_return_price_case4(){
        Integer brandId = 1;
        Long productId = 35455L;
        String target = "2020-06-15 10:00:00";
        Date applicationDate;
        try {
            applicationDate =  df.parse(target);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        Price price = priceRepository.findPriceByDateAndBrandAndProduct(applicationDate, productId, brandId);
        Assertions.assertThat(price.getPrice()).isEqualTo(30.50);
    }

    @Test
    public void when_all_arguments_are_ok_then_return_price_case5(){
        Integer brandId = 1;
        Long productId = 35455L;
        String target = "2020-06-16 21:00:00";
        Date applicationDate;
        try {
            applicationDate =  df.parse(target);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        Price price = priceRepository.findPriceByDateAndBrandAndProduct(applicationDate, productId, brandId);
        Assertions.assertThat(price.getPrice()).isEqualTo(38.95);
    }
}