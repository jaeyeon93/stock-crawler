package com.example.demo.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.LinkedHashMap;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class RealData extends LinkedHashMap<String, ChartImageUrl> {

    @JsonProperty("tradePrice")
    private String tradePrice;

    @JsonProperty("name")
    private String name;

    @JsonProperty("changePrice")
    private String changePrice;

    @JsonProperty("sales")
    private String sales;

    @JsonProperty("operatingProfit")
    private String operatingProfit;

    public RealData(String tradePrice, String name, String changePrice, String sales, String operatingProfit) {
        this.tradePrice = tradePrice;
        this.name = name;
        this.changePrice = changePrice;
        this.sales = sales;
        this.operatingProfit = operatingProfit;
    }

//    public String getTradePrice() {
//        return tradePrice;
//    }
//
//    public String getName() {
//        return name;
//    }
//
//    public String getChangePrice() {
//        return changePrice;
//    }
//
//    public String getSales() {
//        return sales;
//    }
//
//    public String getOperatingProfit() {
//        return operatingProfit;
//    }
//
//    public RealData setTradePrice(String tradePrice) {
//        this.tradePrice = tradePrice;
//        return this;
//    }
//
//    public RealData setName(String name) {
//        this.name = name;
//        return this;
//    }
//
//    public RealData setChangePrice(String changePrice) {
//        this.changePrice = changePrice;
//        return this;
//    }
//
//    public RealData setSales(String sales) {
//        this.sales = sales;
//        return this;
//    }
//
//    public RealData setOperatingProfit(String operatingProfit) {
//        this.operatingProfit = operatingProfit;
//        return this;
//    }
//
//    @Override
//    public String toString() {
//        return "RealData{" +
//                "tradePrice='" + tradePrice + '\'' +
//                ", name='" + name + '\'' +
//                ", changePrice='" + changePrice + '\'' +
//                ", sales='" + sales + '\'' +
//                ", operatingProfit='" + operatingProfit + '\'' +
//                '}';
//    }
}
