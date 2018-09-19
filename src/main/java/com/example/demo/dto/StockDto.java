package com.example.demo.dto;

import com.example.demo.domain.Stock;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class StockDto {
    private static final Logger logger =  LoggerFactory.getLogger(StockDto.class);

    @JsonProperty("name")
    private String name;

    @JsonProperty
    private String salesMoney;

    @JsonProperty("cost")
    private String cost;

    @JsonProperty
    private String profit;

    @JsonProperty
    private String totalCost;

    @JsonProperty("updn")
    private String updn;

    @JsonProperty("rate")
    private String rate;

    @JsonProperty("code")
    private String code;

    public StockDto() {}

    public StockDto(String name, String cost, String updn, String rate, String code) {
        this.name = name.toUpperCase();
        this.cost = cost;
        this.updn = updn;
        this.rate = rate;
        this.code = code;
    }

    public StockDto realDataUpdate(String name, String price, String changeMoney, String changePercent, String detailUrl) {
        this.name = name.toUpperCase();
        this.cost = price;
        this.updn = changeMoney;
        this.rate = changePercent;
        this.code = detailUrl;
        return this;
    }

//    public StockDto update(String price, String changeMoney, String changePercent, String profit, String salesMoney, String totalCost, String detailUrl) {
//        this.name = getName().toUpperCase();
//        this.cost = price;
//        this.updn = changeMoney;
//        this.rate = changePercent;
//        this.profit = profit;
//        this.salesMoney = salesMoney;
//        this.totalCost = totalCost;
//        this.code = detailUrl;
//        logger.info("{} updated", toString());
//        return this;
//    }

    public Integer changeInt(String number) {
        return Integer.parseInt(number.replace(",", ""));
    }

    public Double changeDouble(String percent) {
        return Double.parseDouble(percent.replace("%",""));
    }

    public Integer changePrice(String changeMoney) {
        if (changeMoney.contains("▼"))
            return (Integer.parseInt(changeMoney.replace("▼", "").replace(",", "").trim()) * -1);
        return Integer.parseInt(changeMoney.replace("▲", "").replace(",","").trim());
    }

    public Stock toStock() {
        return new Stock(this.name, changeInt(this.cost), changePrice(this.updn), changeDouble(this.rate), getUrl(this.code));
    }

//    public Stock toUpdate() {
//        return new Stock(this.name, changeInt(this.cost), changePrice(this.updn), changeDouble(this.rate), this.profit, this.salesMoney, this.totalCost, this.code);
//    }

    public String getUrl(String code) {
        return "http://finance.daum.net/item/main.daum?code="+code;
    }


    public String getName() {
        return name;
    }

    public StockDto setName(String name) {
        this.name = name;
        return this;
    }

    public String getSalesMoney() {
        return salesMoney;
    }

    public StockDto setSalesMoney(String salesMoney) {
        this.salesMoney = salesMoney;
        return this;
    }

    public String getCost() {
        return cost;
    }

    public StockDto setCost(String cost) {
        this.cost = cost;
        return this;
    }

    public String getProfit() {
        return profit;
    }

    public StockDto setProfit(String profit) {
        this.profit = profit;
        return this;
    }

    public String getTotalCost() {
        return totalCost;
    }

    public StockDto setTotalCost(String totalCost) {
        this.totalCost = totalCost;
        return this;
    }

    public String getUpdn() {
        return updn;
    }

    public StockDto setUpdn(String updn) {
        this.updn = updn;
        return this;
    }

    public String getRate() {
        return rate;
    }

    public StockDto setRate(String rate) {
        this.rate = rate;
        return this;
    }

    public String getCode() {
        return code;
    }

    public StockDto setCode(String code) {
        this.code = code;
        return this;
    }

    @Override
    public String toString() {
        return "StockDto{" +
                "name='" + name + '\'' +
                ", salesMoney='" + salesMoney + '\'' +
                ", cost='" + cost + '\'' +
                ", profit='" + profit + '\'' +
                ", totalCost='" + totalCost + '\'' +
                ", updn='" + updn + '\'' +
                ", rate='" + rate + '\'' +
                ", code='" + code + '\'' +
                '}';
    }
}
