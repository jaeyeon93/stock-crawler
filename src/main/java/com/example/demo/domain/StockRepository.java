package com.example.demo.domain;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StockRepository extends JpaRepository<Stock, Long> {
    Stock findByName(String name);
    List<Stock> findTop20ByOrderByCostDesc();
    List<Stock> findTop20ByOrderByRateDesc();
    List<Stock> findTop20ByOrderByRateAsc();
    List<Stock> findTop20ByOrderByCostAsc();
    List<Stock> findByNameStartingWith(String name);
}
