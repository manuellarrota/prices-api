package com.zara.prices.domain;

import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;
import java.util.Objects;

@Entity
@ToString
@Setter @Getter
@AllArgsConstructor @NoArgsConstructor @Builder
public class Price {
    //price_List
    @Id
    private Long id;

    //Range dates to apply
    private Date startDate;
    private Date endDate;

    private Integer priority;
    private Integer brandId;

    private Long productId;
    private Double price;

    private String currency;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Price price = (Price) o;
        return id != null && Objects.equals(id, price.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
