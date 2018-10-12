package com.example.demo.dto;

import com.example.demo.domain.Stock;
import com.example.demo.support.domain.AbstractEntity;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class StockDto extends AbstractEntity {
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
        this.name = name.toUpperCase().replace(" ","");
        this.cost = cost;
        this.updn = updn;
        this.rate = rate;
        this.code = code;
    }

    public Stock toStock() {
        return new Stock(this.name, getCost(), getUpdn(), getRate(), getUrl(this.code));
    }

    public Stock toRealDataUpdate(Stock original) {
        return original.realDataUpdate(this);
    }

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

    public Integer getCost() {
        return Integer.parseInt(cost.replace(",", ""));
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

    public Integer getUpdn() {
        if (updn.contains("▼"))
            return (Integer.parseInt(updn.replace("▼", "").replace(",", "").trim()) * -1);
        if (updn.contains("↓"))
            return (Integer.parseInt(updn.replace("↓", "").replace(",", "").trim()) * -1);
        return Integer.parseInt(updn.replace("▲", "").replace(",","").trim());
    }

    public StockDto setUpdn(String updn) {
        this.updn = updn;
        return this;
    }

    public Double getRate() {
        return Double.parseDouble(rate.replace("%",""));
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
