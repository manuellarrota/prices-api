package com.zara.prices.application;

import com.zara.prices.domain.Price;
import com.zara.prices.domain.PriceRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

@SpringBootTest
class PriceServiceImplTest {
    @Mock
    private PriceRepository priceRepository;
    private PriceService priceService;
    private final DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.ENGLISH);
    private final Integer brandId = 1;
    private final Long productId = 35455L;

    @BeforeEach
    public void setup(){
        MockitoAnnotations.openMocks(this);
        priceService = new PriceServiceImpl(priceRepository);
        Date startDatePrice;
        Date endDatePrice;
        String currency = "EUR";
        String applicationDateString = "2020-06-14 10:00:00";
        String startDatePriceString = "2020-06-14 10:00:00";
        String endDatePriceString = "2020-06-14 10:00:00";
        Date applicationDate;
        try {
            startDatePrice =  df.parse(startDatePriceString);
            endDatePrice =  df.parse(endDatePriceString);
            applicationDate =  df.parse(applicationDateString);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        Price price = Price.builder().id(1L).productId(productId).
                startDate(startDatePrice).
                endDate(endDatePrice).
                currency(currency).
                priority(0).
                price(35.50).
                brandId(brandId).
                build();
        Mockito.when(priceRepository.findPriceByDateAndBrandAndProduct(applicationDate, productId, brandId)).thenReturn(price);
    }

    @Test
    void when_all_arguments_are_ok_then_return_price_ok(){
        String applicationDateString = "2020-06-14 10:00:00";
        Date applicationDate;
        try {
            applicationDate =  df.parse(applicationDateString);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        Price price = priceService.findPriceByDateAndBrandAndProduct(applicationDate, productId, brandId);
        Assertions.assertThat(price.getPrice()).isEqualTo(35.50);
    }

    @Test
    void when_some_arguments_are_empty_date_then_return_price_ok(){
        String applicationDateString = "2020-06-14 10:00:00";
        Date applicationDate;
        try {
            applicationDate =  df.parse(applicationDateString);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        Price price = priceService.findPriceByDateAndBrandAndProduct(null, productId, brandId);
        Assertions.assertThat(price).isNull();

        price = priceService.findPriceByDateAndBrandAndProduct(applicationDate, null, brandId);
        Assertions.assertThat(price).isNull();

        price = priceService.findPriceByDateAndBrandAndProduct(applicationDate, productId, null);
        Assertions.assertThat(price).isNull();
    }

}
