package com.priceservice.model.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Value;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Value
public class ApplicablePrice {
    Long brandId;
    Long productId;
    Long priceList;
    LocalDateTime startDate;
    LocalDateTime endDate;
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    BigDecimal price;
}
