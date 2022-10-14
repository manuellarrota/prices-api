package com.zara.prices.infraestructure;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor @NoArgsConstructor
public class PriceRequestDto {
    @NotNull()
    //@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private String applicationDate;
    @NotNull()
    private Long productId;
    @NotNull()
    private Integer brandId;


}
