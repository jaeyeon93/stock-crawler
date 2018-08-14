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
    private String price;

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
    private String changePercent;

    @Column
    @JsonProperty
    private String detailUrl;

    public Stock() {}

    public Stock(String name) {
        this.name = name;
    }

    public Stock(String name, String price, String changeMoney, String changePercent) {
        this.name = name.toUpperCase();
        this.price = price;
        this.changeMoney = changeMoney;
        this.changePercent = changePercent;
    }

    public Stock(String name, String price, String changeMoney, String changePercent, String detailUrl) {
        this.name = name.toUpperCase();
        this.price = price;
        this.changeMoney = changeMoney;
        this.changePercent = changePercent;
        this.detailUrl = detailUrl;
    }

//    public Stock(String name, String price, String profit, String totalCost) {
//        this.name = name.toUpperCase();
//        this.price = price;
//        this.profit = profit;
//        this.totalCost = totalCost.replaceAll(" ", "");
//    }

    public Stock(String name, String price, String salesMoney, String profit, String totalCost, String changeMoney, String changePercent, String detailUrl) {
        this.name = name.toUpperCase();
        this.price = price;
        this.salesMoney = salesMoney;
        this.profit = profit;
        this.totalCost = totalCost.replaceAll(" ", "");
        this.changeMoney = changeMoney;
        this.changePercent = changePercent;
        this.detailUrl = detailUrl;
    }

    public Stock(long id, String name, String price, String profit, String totalCost) {
        super(id);
        this.name = name.toUpperCase();
        this.price = price;
        this.profit = profit;
        this.totalCost = totalCost.replaceAll(" ", "");
    }

    public void update(String price, String changeMoney, String changePerent) {
        this.name = getName().toUpperCase();
        this.price = price;
        this.changeMoney = changeMoney;
        this.changePercent = changePerent;
    }

    public String getName() {
        return name;
    }

    public String getSalesMoney() {
        return salesMoney;
    }

    public String getPrice() {
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

    public String getChangePercent() {
        return changePercent;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Stock setPrice(String price) {
        this.price = price;
        return this;
    }

    public Stock setChangeMoney(String changeMoney) {
        this.changeMoney = changeMoney;
        return this;
    }

    public Stock setChangePercent(String changePercent) {
        this.changePercent = changePercent;
        return this;
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
