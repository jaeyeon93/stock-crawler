package com.example.demo.dto;

import com.example.demo.domain.Stock;
import com.example.demo.support.domain.AbstractEntity;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Data
public class StockDto extends AbstractEntity {
    private static final Logger logger =  LoggerFactory.getLogger(StockDto.class);
    private static final String UP = "▲";
    private static final String DOWN1 = "▼";
    private static final String DOWN2 = "↓";
    private static final String COMMA = ",";
    private static final String EMPTY = "";

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
        this.name = name;
        this.cost = cost;
        this.updn = updn;
        this.rate = rate;
        this.code = code;
    }

    public Stock toStock() {
        return new Stock(this.name, getCost(), getUpdn(), getRate(), getUrl(this.code), getUpdateUrl(this.code));
    }

    public Stock toRealDataUpdate(Stock original) {
        return original.realDataUpdate(this);
    }

    public String getUrl(String code) {
        return "http://finance.daum.net/quotes/A"+code+"#home";
    }

    public String getUpdateUrl(String code) {
        return "http://finance.daum.net/api/quotes/A" + code + "?summary=false&changeStatistics=true";
    }

    public Integer getCost() {
        return Integer.parseInt(cost.replace(",", ""));
    }

    public StockDto setCost(String cost) {
        this.cost = cost;
        return this;
    }

    public Integer getUpdn() {
        if (updn.contains(DOWN1))
            return (Integer.parseInt(updn.replace(DOWN1, EMPTY).replace(COMMA, EMPTY).trim()) * -1);
        if (updn.contains(DOWN2))
            return (Integer.parseInt(updn.replace(DOWN2, EMPTY).replace(COMMA, EMPTY).trim()) * -1);
        return Integer.parseInt(updn.replace(UP, EMPTY).replace(COMMA,EMPTY).trim());
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
}
