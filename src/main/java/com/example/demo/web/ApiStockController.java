package com.example.demo.web;

import com.example.demo.domain.Stock;
import com.example.demo.service.StockService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/stock")
public class ApiStockController {
    public static final Logger logger = LoggerFactory.getLogger(ApiStockController.class);
    private Stock stock;
    private List<Stock> stocks = new ArrayList<>();

    @Resource(name = "stockService")
    private StockService stockService;

    @GetMapping("")
    public List<Stock> list() {
        return stockService.findAll();
    }

    @GetMapping("/{id}")
    public Stock show(@PathVariable long id) {
        logger.info("show method called, id is {}", id);
        return stockService.getStockById(id);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable long id) throws Exception {
        stockService.deleteStockById(id);
    }

    public List<Stock> getStocks() {
        return stocks;
    }
}
