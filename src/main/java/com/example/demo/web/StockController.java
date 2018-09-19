package com.example.demo.web;

import com.example.demo.domain.Stock;
import com.example.demo.service.StockService;
import jdk.nashorn.internal.objects.annotations.Getter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@Controller
@RequestMapping("/stock")
public class StockController {
    public static final Logger logger = LoggerFactory.getLogger(StockController.class);

    @Resource(name = "stockService")
    private StockService stockService;

    @GetMapping("")
    public @ResponseBody List<Stock> list(Model model) {
        return stockService.findAll();
    }

    @DeleteMapping("/{id}")
    public String deleteStockById(@PathVariable long id) throws Exception {
        logger.info("deleteStockById method called");
        stockService.deleteStockById(id);
        return "/redirect:/stock";
    }

    @GetMapping("/all")
    public String getAllStock() throws Exception {
        stockService.getAllStock();
        return "redirect:/stock";
    }

    @GetMapping("/{stockName}")
    public @ResponseBody Stock getByStockName(@PathVariable String stockName) throws Exception {
        logger.info("getByStockName method called on controller");
        return stockService.updateByStockName(stockName);
    }

    @GetMapping("/search/{stockName}")
    public @ResponseBody List<Stock> searchByStockName(@PathVariable String stockName) {
        logger.info("전달받은 주식이름 : {}", stockName);
        return stockService.searchByStockName(stockName);
    }

    @GetMapping("/allstockupdate")
    public String detailWholeUpdate() throws Exception {
        stockService.detailWholeUpdate();
        return "redirect:/stock";
    }

    @GetMapping("/lowPrice")
    public @ResponseBody List<Stock> lowPrice() throws Exception {
        return stockService.lowPrice();
    }

    @GetMapping("/lowPercent")
    public @ResponseBody List<Stock> lowPercent() throws Exception {
        return stockService.lowPercent();
    }

    @GetMapping("/topPrice")
    public @ResponseBody List<Stock> topPrice() throws Exception {
        return stockService.topPrice();
    }

    @GetMapping("/topPercent")
    public @ResponseBody List<Stock> topPercent() throws Exception {
        return stockService.topPercent();
    }
}
