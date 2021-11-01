package com.priceservice.rest;

import com.priceservice.model.dto.ApplicablePrice;
import com.priceservice.service.PriceService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


import java.time.LocalDateTime;
import java.util.Optional;

@RestController
@RequestMapping("/prices")
public class PriceRestController {

    @Autowired
    private PriceService priceService;

    @ApiOperation(value = "The applicable price of a product on a specified timestamp")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved price", response = ApplicablePrice.class),
            @ApiResponse(code = 204, message = "The price you were trying to reach is not found", response = Void.class)
    })
    @GetMapping("applicable_price")
    public ResponseEntity<ApplicablePrice> getApplicablePriceByDate(@RequestParam Long brandId,
                                                                    @RequestParam Long productId,
                                                                    @ApiParam(value = "Application timestamp in UTC. ISO DATE_TIME Format",
                                                                              name = "appTimestamp",
                                                                              example = "2020-06-14T00:00:00",
                                                                              required = true)
                                                                    @RequestParam
                                                                    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime appTimestamp) {
        Optional<ApplicablePrice> resPrice = priceService.getApplicablePriceByTimestamp(brandId, productId, appTimestamp);
        if (resPrice.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.of(resPrice);
    }

}
