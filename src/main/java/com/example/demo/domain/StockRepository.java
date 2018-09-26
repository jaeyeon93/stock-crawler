package com.example.demo.domain;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StockRepository extends JpaRepository<Stock, Long> {
    Stock findByName(String name);
    List<Stock> findTop20ByOrderByCostDesc();
    List<Stock> findTop20ByRateLessThanEqualOrderByRateDesc(Double rate);

    List<Stock> findTop20ByRateGreaterThanEqualOrderByRateAsc(Double rate);
    List<Stock> findTop20ByCostGreaterThanOrderByCostAsc(Integer minCost);

    // 이름으로 검색하기
    List<Stock> findByNameStartingWith(String name);

}
