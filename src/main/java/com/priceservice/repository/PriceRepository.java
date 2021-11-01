package com.priceservice.repository;

import com.priceservice.model.dto.ApplicablePrice;
import com.priceservice.model.entity.Price;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface PriceRepository extends JpaRepository<Price, Long> {

    //Find applicable prices taking account brandId, productId, timestamp and order by priority
    @Query("SELECT new com.priceservice.model.dto.ApplicablePrice(brandId, productId, priceList, startDate, endDate, price) " +
            "FROM Price p " +
            "WHERE p.brandId = :brandId " +
            "AND p.productId= :productId " +
            "AND :appTimestamp >= p.startDate AND (p.endDate IS NULL OR :appTimestamp <= p.endDate) " +
            "ORDER BY p.priority desc")
    List<ApplicablePrice> findApplicablePriceOrderByPriority(@Param("brandId")Long brandId, @Param("productId")Long productId,
                                                             @Param("appTimestamp") LocalDateTime appTimestamp,
                                                             Pageable pageable);


    //Find applicable price taking account brandId, productId, timestamp and order by priority.
    //Get the priority price
    default List<ApplicablePrice> findApplicablePriceOrderByPriority(@Param("brandId")Long brandId, @Param("productId")Long productId,
                                                                     @Param("appTimestamp") LocalDateTime appTimestamp) {
        return findApplicablePriceOrderByPriority(brandId, productId, appTimestamp, PageRequest.of(0, 1));
    }

}