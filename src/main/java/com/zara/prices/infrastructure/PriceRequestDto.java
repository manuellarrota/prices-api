package com.zara.prices.infrastructure;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor @NoArgsConstructor
public class PriceRequestDto {
    @NotBlank
    private String applicationDate;
    @NotNull
    private Long productId;
    @NotNull
    private Integer brandId;
}
