package com.example.demo.domain;

import com.example.demo.dto.StockDto;
import com.example.demo.support.domain.AbstractEntity;
import com.example.demo.support.domain.UrlGeneratable;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.*;

@Entity
public class Stock extends AbstractEntity implements UrlGeneratable {
    public static final Logger logger = LoggerFactory.getLogger(Stock.class);

    @Column
    @JsonProperty
    private String name;

    @Column
    @JsonProperty
    private String salesMoney;

    @Column
    @JsonProperty
    private Integer price;

    @Column
    @JsonProperty
    private String profit;

    @Column
    @JsonProperty
    private String totalCost;

    @Column
    @JsonProperty
    private Integer changeMoney;

    @Column
    @JsonProperty
    private Double changePercent;

    @Column
    @JsonProperty
    private String detailUrl;

    public Stock() {}

    public Stock(String name, Integer price, Integer changeMoney, Double changePercent, String detailUrl) {
        this.name = name.toUpperCase();
        this.price = price;
        this.changeMoney = changeMoney;
        this.changePercent = changePercent;
        this.detailUrl = detailUrl;
        logger.info("stock 생성 : {}", toString());
    }

    public Stock(String name, Integer price, Integer changeMoney, Double changePercent, String profit, String salesMoney, String totalCost, String detailUrl) {
        this.name = name;
        this.price = price;
        this.changeMoney = changeMoney;
        this.changePercent = changePercent;
        this.profit = profit;
        this.salesMoney = salesMoney;
        this.totalCost = totalCost;
        this.detailUrl = detailUrl;
    }

    public StockDto toStockDto() {
        return new StockDto(this.name, this.price, this.changeMoney, this.changePercent, this.detailUrl);
    }

    public String getName() {
        return name;
    }

    public String getSalesMoney() {
        return salesMoney;
    }

    public Integer getPrice() {
        return price;
    }

    public String getProfit() {
        return profit;
    }

    public String getTotalCost() {
        return totalCost;
    }

    public Integer getChangeMoney() {
        return changeMoney;
    }

    public Double getChangePercent() {
        return changePercent;
    }

    public String getDetailUrl() {
        return detailUrl;
    }

    @Override
    public String generateUrl() {
        return String.format("/stock/%d", getId());
    }

    @Override
    public String toString() {
        return "Stock{" +
                "name='" + name + '\'' +
                ", salesMoney='" + salesMoney + '\'' +
                ", price='" + price + '\'' +
                ", profit='" + profit + '\'' +
                ", totalCost='" + totalCost + '\'' +
                ", changeMoney='" + changeMoney + '\'' +
                ", changePrice='" + changePercent + '\'' +
                ", detailUrl='" + detailUrl + '\'' +
                '}';
    }
}
