package com.example.demo.dto;

import com.example.demo.domain.Stock;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class RealData {
    private static final Logger logger =  LoggerFactory.getLogger(RealData.class);

    @JsonProperty("name")
    private String name;

    @JsonProperty("tradePrice")
    private int tradePrice;

    @JsonProperty("changePrice")
    private int changePrice;

    @JsonProperty("changeRate")
    private double changeRate;

    @JsonProperty("sales")
    private double sales;

    @JsonProperty("operatingProfit")
    private double operatingProfit;

    @JsonProperty("chartImageUrl")
    private ChartImageUrl chartImageUrl;

    @JsonProperty("marketCap")
    private double marketCap;

    public RealData(Stock stock) {
        this.name = stock.getName();
        this.tradePrice = stock.getCost();
        this.changePrice = stock.getUpdn();
        this.changeRate = stock.getRate();
        this.sales = stock.getSalesMoney();
        this.operatingProfit = stock.getProfit();
        this.marketCap = stock.getTotalCost();
    }

    public RealData(String name, int tradePrice, int changePrice, double changeRate, double sales, double operatingProfit, int marketCap) {
        this.name = name;
        this.tradePrice = tradePrice;
        this.changePrice = changePrice;
        this.changeRate = changeRate;
        this.sales =sales;
        this.operatingProfit = operatingProfit;
        this.marketCap = marketCap;
    }
}
