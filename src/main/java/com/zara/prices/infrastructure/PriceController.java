package com.zara.prices.infrastructure;

import com.zara.prices.application.PriceService;
import com.zara.prices.domain.Price;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

@RestController
@RequestMapping("prices")
@Slf4j
public class PriceController {

    private final PriceService priceService;
    private final DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.ENGLISH);

    @Autowired
    public PriceController(PriceService priceService) {
        this.priceService = priceService;
    }

    @ApiOperation(value = "Get prices by date, product id and brand id")
    @PostMapping
    public ResponseEntity<PriceResponseDto> getPriceByDateAndProductIdAndBrandId(@Valid @RequestBody PriceRequestDto priceRequestDto ) {
        if(priceRequestDto.getBrandId() == null || priceRequestDto.getBrandId() == 0 ){
            log.error("brand is empty. ");
            return new ResponseEntity<>(new PriceResponseDto(), HttpStatus.BAD_REQUEST);
        }
        if(priceRequestDto.getProductId() == null || priceRequestDto.getProductId() == 0 ){
            log.error("product is empty. ");
            return new ResponseEntity<>(new PriceResponseDto(), HttpStatus.BAD_REQUEST);
        }
        Date applicationDate;
        try {
            applicationDate =  df.parse(priceRequestDto.getApplicationDate());
        } catch (ParseException e) {
            log.error("Error in date " + e.getMessage());
            return new ResponseEntity<>(new PriceResponseDto(), HttpStatus.BAD_REQUEST);
        }
        Price price = priceService.findPriceByDateAndBrandAndProduct(applicationDate, priceRequestDto.getProductId(),
                priceRequestDto.getBrandId());
        if (price == null){
            log.info("Not found by date: " + applicationDate);
            return new ResponseEntity<>(new PriceResponseDto(), HttpStatus.OK);
        }
        PriceResponseDto priceResponseDto = new PriceResponseDto(
                price.getProductId(),
                price.getId(),
                price.getPrice(),
                price.getBrandId(),
                applicationDate
        );
        log.info("Price response found : " + priceResponseDto);
        return new ResponseEntity<>(
                priceResponseDto
                , HttpStatus.OK);
    }

}
