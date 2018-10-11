package com.example.demo.bot;

import com.example.demo.domain.Stock;
import com.example.demo.service.StockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class Command {

    @Autowired
    private StockService stockService;

    private Map<String, CommadMessage> commands = new HashMap<>();

    public Command() {
        commands.put("상위종목", new CommadMessage() {
            @Override
            public List<Stock> runCommand() {
                return stockService.topRate();
            }
        });

        commands.put("상위가격", new CommadMessage() {
            @Override
            public List<Stock> runCommand() {
                return stockService.topCost();
            }
        });

        commands.put("하위종목", new CommadMessage() {
            @Override
            public List<Stock> runCommand() {
                return stockService.lowRate();
            }
        });

        commands.put("하위가격", new CommadMessage() {
            @Override
            public List<Stock> runCommand() {
                return stockService.lowCost();
            }
        });
    }
    
    public Map<String, CommadMessage> getCommands() {
        return commands;
    }
}
