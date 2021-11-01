package com.priceservice.service;

import com.priceservice.model.dto.ApplicablePrice;
import com.priceservice.repository.PriceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class PriceService {

    @Autowired
    private PriceRepository priceRepository;

    public Optional<ApplicablePrice> getApplicablePriceByTimestamp(Long brandId, Long productId, LocalDateTime appTimestamp) {
        List<ApplicablePrice> resultPriceL = priceRepository.findApplicablePriceOrderByPriority(brandId, productId, appTimestamp);
        return resultPriceL.isEmpty()? Optional.empty() : Optional.of(resultPriceL.get(0));
    }

}
