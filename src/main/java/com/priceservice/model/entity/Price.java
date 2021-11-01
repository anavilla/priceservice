package com.priceservice.model.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.time.LocalDateTime;


@Data
@Entity
@Table(name = "prices")
public class Price {

    private @Id @GeneratedValue Long id;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private Long priceList;
    private Long brandId;
    private Long productId;
    private BigDecimal price;
    private Integer priority;
    private String curr;
}
