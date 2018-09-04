package com.example.demo.domain;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StockRepository extends JpaRepository<Stock, Long> {
    Stock findByName(String name);
    List<Stock> findAllByOrderByPriceDesc();
    List<Stock> findAllByOrderByChangePercentDesc();
    List<Stock> findAllByOrderByChangePercentAsc();
    List<Stock> findAllByOrderByPriceAsc();
}
