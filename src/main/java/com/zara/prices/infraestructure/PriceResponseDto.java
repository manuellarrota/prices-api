package com.zara.prices.infraestructure;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@NoArgsConstructor
@Data
public class PriceResponseDto {
    private Long productId;
    private Long priceList;
    private Double price;
    private Integer brandId;
    private Date applicationDate;

    public PriceResponseDto(Long productId, Long priceList, Double price, Integer brandId, Date applicationDate) {
        this.productId = productId;
        this.brandId = brandId;
        this.priceList = priceList;
        this.price = price;
        this.applicationDate = applicationDate;
    }
}
