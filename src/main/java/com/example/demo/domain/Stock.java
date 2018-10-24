package com.example.demo.domain;

import com.example.demo.dto.RealData;
import com.example.demo.dto.StockDto;
import com.example.demo.support.domain.AbstractEntity;
import com.example.demo.support.domain.UrlGeneratable;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.ToString;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.*;

@Entity
@Getter
@ToString
@JsonInclude(JsonInclude.Include.NON_NULL)
@Table(name = "stock", indexes = {@Index(name = "nameIndex", columnList = "name"), @Index(name = "rateIndex", columnList = "rate"), @Index(name = "costIndex", columnList = "cost")})
public class Stock extends AbstractEntity implements UrlGeneratable {
    public static final Logger logger = LoggerFactory.getLogger(Stock.class);

    @Column
    @JsonProperty
    private String name;

    @Column
    @JsonProperty
    private double salesMoney;

    @Column
    @JsonProperty
    private Integer cost;

    @Column
    @JsonProperty
    private Double profit;

    @Column
    @JsonProperty
    private Double profitPercent;

    @Column
    @JsonProperty
    private Double totalCost;

    @Column
    @JsonProperty
    private Integer updn;

    @Column
    @JsonProperty
    private Double rate;

    @Column
    @JsonProperty
    private String detailUrl;

    @JsonIgnore
    private String updateUrl;

    @JsonIgnore
    private String yearGraph;

    public Stock() {}

    public Stock(String name, Integer cost, Integer updn, Double rate, String detailUrl, String updateUrl) {
        this.name = name;
        this.cost = cost;
        this.updn = updn;
        this.rate = rate;
        this.detailUrl = detailUrl;
        this.updateUrl = updateUrl;
        logger.info("stock 생성 : {}", toString());
    }

//    public Stock update(RealData realData) {
//        this.name = getName();
//        this.cost = realData.getTradePrice();
//        this.updn = realData.getChangePrice();
//        this.rate = formatDouble(realData.getChangeRate()*100);
//        this.profit = formatDouble((realData.getOperatingProfit()/100000000));
//        this.profitPercent = formatDouble((realData.getOperatingProfit()/realData.getSales())*100);
//        this.salesMoney = formatDouble((realData.getSales()/100000000));
//        this.totalCost = formatDouble((realData.getMarketCap()/100000000));
//        this.yearGraph = realData.getChartImageUrl().getYear();
//        logger.info("stock to {}", toString());
//        logger.info("{} updated", getName());
//        return this;
//    }

    public Stock update(RealData realData) {
        this.name = getName();
        this.cost = realData.getTradePrice();
        this.updn = realData.getChangePrice();
        this.rate = checkNaN(formatDouble(realData.getChangeRate()*100));
        this.profit = checkNaN(formatDouble((realData.getOperatingProfit()/100000000)));
        this.profitPercent = checkNaN(formatDouble((realData.getOperatingProfit()/realData.getSales())*100));
        this.salesMoney = checkNaN(formatDouble((realData.getSales()/100000000)));
        this.totalCost = checkNaN(formatDouble((realData.getMarketCap()/100000000)));
        this.yearGraph = realData.getChartImageUrl().getYear();
        logger.info("stock to {}", toString());
        logger.info("{} updated", getName());
        return this;
    }

    public Stock realDataUpdate(StockDto stockDto) {
        this.name = stockDto.getName();
        this.cost = stockDto.getCost();
        this.updn = stockDto.getUpdn();
        this.rate = stockDto.getRate();
        logger.info("{} 실시간 정보 업데이트", name);
        return this;
    }

//    public Double formatDouble(double number) {
//        return Double.parseDouble(String.format("%.2f", number));
//    }

    public Double formatDouble(double number) {
        return Double.valueOf(String.format("%.2f", number));
    }

    public Double checkNaN(double number) {
        if (Double.isNaN(number))
            return 0.0;
        return number;
    }

    public StockDto toStockDto() {
        return new StockDto(this.name, String.valueOf(cost), String.valueOf(this.updn), String.valueOf(this.rate), this.detailUrl);
    }

    @Override
    public String generateUrl() {
        return String.format("/stock/%d", getId());
    }

}
