package com.example.demo.domain;

import com.example.demo.dto.StockDto;
import com.example.demo.support.domain.AbstractEntity;
import com.example.demo.support.domain.UrlGeneratable;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.*;

@Entity
@Table(name = "stock", indexes = {@Index(name = "nameIndex", columnList = "name"), @Index(name = "nameRateIndex", columnList = "name, rate")})
public class Stock extends AbstractEntity implements UrlGeneratable {
    public static final Logger logger = LoggerFactory.getLogger(Stock.class);

    @Column
    @JsonProperty("name")
    private String name;

    @Column
    @JsonProperty
    private String salesMoney;

    @Column
    @JsonProperty("cost")
    private Integer cost;

    @Column
    @JsonProperty
    private String profit;

    @Column
    @JsonProperty
    private String totalCost;

    @Column
    @JsonProperty("updn")
    private Integer updn;

    @Column
    @JsonProperty("rate")
    private Double rate;

    @Column
    @JsonProperty
    private String detailUrl;

    public Stock() {}

    public Stock(String name, Integer cost, Integer updn, Double rate, String detailUrl) {
        this.name = name.toUpperCase();
        this.cost = cost;
        this.updn = updn;
        this.rate = rate;
        this.detailUrl = detailUrl;
        logger.info("stock 생성 : {}", toString());
    }

    public Stock update(String cost, String updn, String rate, String profit, String salesMoney, String totalCost, String detailUrl) {
        this.name = getName().toUpperCase();
        this.cost = updnToInteger(cost);
        this.updn = costToInteger(updn);
        this.rate = rateToDouble(rate);
        this.profit = profit;
        this.salesMoney = salesMoney;
        this.totalCost = totalCost;
        this.detailUrl = detailUrl;
        logger.info("{} updated", getName());
        return this;
    }

    public Stock realDataUpdate(String name, String cost, String updn, String rate) {
        this.name = name.toUpperCase();
        this.cost = costToInteger(cost);
        this.updn = updnToInteger(updn);
        this.rate = rateToDouble(rate);
        logger.info("{} 실시간 정보 업데이트", name);
        return this;
    }

    public StockDto toStockDto() {
        return new StockDto(this.name, String.valueOf(cost), String.valueOf(this.updn), String.valueOf(this.rate), this.detailUrl);
    }

    public String getName() {
        return name;
    }

    public String getSalesMoney() {
        return salesMoney;
    }

    public Integer getCost() {
        return cost;
    }

    public String getProfit() {
        return profit;
    }

    public String getTotalCost() {
        return totalCost;
    }

    public Integer getUpdn() {
        return updn;
    }

    public Double getRate() {
        return rate;
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
                ", cost='" + cost + '\'' +
                ", profit='" + profit + '\'' +
                ", totalCost='" + totalCost + '\'' +
                ", updn='" + updn + '\'' +
                ", updnToInteger='" + rate + '\'' +
                ", detailUrl='" + detailUrl + '\'' +
                '}';
    }
}
