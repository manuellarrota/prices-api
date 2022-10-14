package com.zara.prices.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;

@Repository
public interface PriceRepository extends JpaRepository<Price, Long> {

    @Query(nativeQuery = true,
    value = "Select p.* from price p " +
            " where :applicationDate between p.start_date and p.end_date" +
            " and p.product_id = :productId" +
            " and p.brand_id = :brandId" +
            " order by priority desc" +
            " limit 1")
    Price findPriceByDateAndBrandAndProduct(
            @Param("applicationDate") Date applicationDate,
            @Param("productId") Long productId,
            @Param("brandId") Integer brandId);

}
