package com.example.demo.domain;

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
    private String changeMoney;

    @Column
    @JsonProperty
    private Double changePercent;

    @Column
    @JsonProperty
    private String detailUrl;

    public Stock() {}

    public Stock(String name, String price, String changeMoney, String changePercent, String detailUrl) {
        this.name = name.toUpperCase();
        this.price = changeInt(price);
        this.changeMoney = changeMoney;
        this.changePercent = changeDouble(changePercent);
        this.detailUrl = detailUrl;
        logger.info("stock 생성 : {}", toString());
    }

    public Stock(long id, String name, String price, String profit, String totalCost) {
        super(id);
        this.name = name.toUpperCase();
        this.price = changeInt(price);
        this.profit = profit;
        this.totalCost = totalCost.replaceAll(" ", "");
    }

    public Stock realDataUpdate(String name, String price, String changeMoney, String changePercent, String detailUrl) {
        this.name = name.toUpperCase();
        this.price = changeInt(price);
        this.changeMoney = changeMoney;
        this.changePercent = changeDouble(changePercent);
        this.detailUrl = detailUrl;
        return this;
    }

    public Stock update(String price, String changeMoney, Double changePercent, String profit, String salesMoney, String totalCost) {
        this.name = getName().toUpperCase();
        this.price = changeInt(price);
        this.changeMoney = changeMoney;
        this.changePercent = changePercent;
        this.profit = profit;
        this.salesMoney = salesMoney;
        this.totalCost = totalCost;
        logger.info("{} updated", name);
        return this;
    }

    public Integer changeInt(String number) {
        return Integer.parseInt(number.replace(",", ""));
    }

    public Double changeDouble(String percent) {
        return Double.parseDouble(percent.replace("%",""));
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

    public String getChangeMoney() {
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
                ", changePercent='" + changePercent + '\'' +
                ", detailUrl='" + detailUrl + '\'' +
                '}';
    }
}
