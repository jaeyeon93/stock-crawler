package com.example.demo.bot;

import com.example.demo.domain.Stock;

import java.util.List;

public interface CommadMessage {
    List<Stock> runCommand();
}
