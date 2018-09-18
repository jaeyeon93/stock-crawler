package com.example.demo.dto;

import com.example.demo.domain.Stock;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.gson.JsonObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class StockDto {
    private static final Logger logger =  LoggerFactory.getLogger(StockDto.class);

    @JsonProperty("name")
    private String name;

    @JsonProperty
    private String salesMoney;

    @JsonProperty("cost")
    private Integer price;

    @JsonProperty
    private String profit;

    @JsonProperty
    private String totalCost;

    @JsonProperty("updn")
    private Integer changeMoney;

    @JsonProperty("rate")
    private Double changePercent;

    @JsonProperty("code")
    private String detailUrl;

    public StockDto() {}

//    public StockDto(String name, String price, String changeMoney, String changePercent, String detailUrl) {
//        this.name = name.toUpperCase();
//        this.price = changeInt(price);
//        this.changeMoney = changePrice(changeMoney);
//        this.changePercent = changeDouble(changePercent);
//        this.detailUrl = detailUrl;
//        logger.info("stock 생성 : {}", toString());
//    }

    public StockDto(String name, Integer price, Integer changeMoney, Double changePercent, String detailUrl) {
        this.name = name.toUpperCase();
        this.price = price;
        this.changeMoney = changeMoney;
        this.changePercent = changePercent;
        this.detailUrl = detailUrl;
        logger.info("stock 생성 : {}", toString());
    }

    public StockDto realDataUpdate(String name, String price, String changeMoney, String changePercent, String detailUrl) {
        this.name = name.toUpperCase();
        this.price = changeInt(price);
        this.changeMoney = changePrice(changeMoney);
        this.changePercent = changeDouble(changePercent);
        this.detailUrl = detailUrl;
        return this;
    }

    public StockDto update(String price, String changeMoney, Double changePercent, String profit, String salesMoney, String totalCost, String detailUrl) {
        this.name = getName().toUpperCase();
        this.price = changeInt(price);
        this.changeMoney = changePrice(changeMoney);
        this.changePercent = changePercent;
        this.profit = profit;
        this.salesMoney = salesMoney;
        this.totalCost = totalCost;
        this.detailUrl = detailUrl;
        logger.info("{} updated", name);
        return this;
    }

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
        return new Stock(this.name, this.price, this.changeMoney, this.changePercent, this.detailUrl);
    }

    public Stock toUpdate() {
        return new Stock(this.name, this.price, this.changeMoney, this.changePercent, this.profit, this.salesMoney, this.totalCost, this.detailUrl);
    }

//    public Stock toRealUpdate() {
//        return new Stock(this.name, this.price, this.changeMoney, this.changePercent, this.detailUrl);
//    }

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

    public Integer getPrice() {
        return price;
    }

    public StockDto setPrice(Integer price) {
        this.price = price;
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

    public Integer getChangeMoney() {
        return changeMoney;
    }

    public StockDto setChangeMoney(Integer changeMoney) {
        this.changeMoney = changeMoney;
        return this;
    }

    public Double getChangePercent() {
        return changePercent;
    }

    public StockDto setChangePercent(Double changePercent) {
        this.changePercent = changePercent;
        return this;
    }

    public String getDetailUrl() {
        return detailUrl;
    }

    public StockDto setDetailUrl(String detailUrl) {
        this.detailUrl = detailUrl;
        return this;
    }
}
