package com.example.demo.domain;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StockRepository extends JpaRepository<Stock, Long> {
    Stock findByName(String name);
    List<Stock> findAllByOrderByCostDesc();
    List<Stock> findAllByOrderByRateDesc();
    List<Stock> findAllByOrderByRateAsc();
    List<Stock> findAllByOrderByCostAsc();
    List<Stock> findByNameStartingWith(String name);
}
