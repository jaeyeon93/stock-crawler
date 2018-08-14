package com.example.demo.web;

import com.example.demo.dto.KeyboardDto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class ApiHomeControoler {
    @GetMapping("/keyboard")
    public KeyboardDto keyboard(@Valid @RequestBody String stockName) {
        KeyboardDto keyboard = new KeyboardDto(stockName);
        return keyboard;
    }
}
