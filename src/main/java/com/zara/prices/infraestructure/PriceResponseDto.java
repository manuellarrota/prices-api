package com.zara.prices.infraestructure;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@NoArgsConstructor
@Data
public class PriceResponseDto {
    private Long productId;
    private Long priceId;
    private Double price;
    private Integer brandId;
    private Date applicationDate;

    public PriceResponseDto(Long productId, Long priceId, Double price, Integer brandId, Date applicationDate) {
        this.productId = productId;
        this.brandId = brandId;
        this.priceId = priceId;
        this.price = price;
        this.applicationDate = applicationDate;
    }
}
