package com.example.demo.bot;

import com.example.demo.domain.Stock;
import com.example.demo.service.StockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;

@Component
public class Command extends HashMap<String, CommadMessage> {

    @Autowired
    private StockService stockService;

    public Command() {
        put("상위종목", new CommadMessage() {
            @Override
            public List<Stock> runCommand() {
                return stockService.topRate();
            }
        });

        put("상위가격", new CommadMessage() {
            @Override
            public List<Stock> runCommand() {
                return stockService.topCost();
            }
        });

        put("하위종목", new CommadMessage() {
            @Override
            public List<Stock> runCommand() {
                return stockService.lowRate();
            }
        });

        put("하위가격", new CommadMessage() {
            @Override
            public List<Stock> runCommand() {
                return stockService.lowCost();
            }
        });
    }
}
