package com.example.demo.dto;

import com.example.demo.domain.Stock;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Data
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

    @JsonProperty("change")
    private String change;

    private Double profitPercent;

    @JsonProperty("chartImageUrl")
    private ChartImageUrl chartImageUrl;

    private String yearGraph;

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

    public double getChangeRate() {
        return formatDouble(changeRate * 100) * fallRise();
    }

    public double getSales() {
        return checkNaN(formatDouble((sales/1000000000)));
    }

    public double getOperatingProfit() {
        return checkNaN(formatDouble((operatingProfit/1000000000)));
    }

    public double getMarketCap() {
        return checkNaN(formatDouble((marketCap/1000000000)));
    }

    public Integer fallRise() {
        logger.info(getChange());
        if (getChange().equals("RISE"))
            return 1;
        return -1;
    }

    public Double getProfitPercent() {
        return checkNaN(formatDouble((getOperatingProfit() / getSales())*100));
    }

    public Double formatDouble(double number) {
        return Double.valueOf(String.format("%.2f", number));
    }

    public Double checkNaN(double number) {
        if (Double.isNaN(number) || Double.isInfinite(number))
            return 0.0;
        return number;
    }

}
