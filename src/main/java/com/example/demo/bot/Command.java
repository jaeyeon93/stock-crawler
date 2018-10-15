package com.example.demo.bot;

import com.example.demo.service.StockService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;

@Component
public class Command extends HashMap<String, CommadMessage> {
    private static final Logger logger =  LoggerFactory.getLogger(Command.class);

    @Autowired
    private StockService stockService;

    public Command() {
        put("상위종목", () -> { return  stockService.topRate(); });
        put("상위가격", () -> { return  stockService.topCost(); });
        put("하위종목", () -> { return  stockService.lowRate(); });
        put("하위가격", () -> { return  stockService.lowCost(); });
    }
}
