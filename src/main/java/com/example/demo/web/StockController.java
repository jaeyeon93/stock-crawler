package com.example.demo.web;

import com.example.demo.domain.Stock;
import com.example.demo.service.StockService;
import org.apache.xpath.operations.Mod;
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
    public String delete(@PathVariable long id) throws Exception {
        logger.info("delete method called");
        stockService.delete(id);
        return "/redirect:/stock";
    }

    @GetMapping("/all")
    public String allStock() throws Exception {
        stockService.addAll();
        return "redirect:/stock";
    }

    @GetMapping("/{stockName}")
    public String update(@PathVariable String stockName) throws Exception {
        logger.info("update method called on controller");
        stockService.update(stockName);
        return "redirect:/stock";
    }
}
