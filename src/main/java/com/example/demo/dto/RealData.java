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

//    @JsonProperty("tradePrice")
//    private Integer tradePrice;

    @JsonProperty("name")
    private String name;

//    @JsonProperty("changePrice")
//    private Integer changePrice;
//
//    @JsonProperty("sales")
//    private Integer sales;
//
//    @JsonProperty("operatingProfit")
//    private Double operatingProfit;

    public RealData(String name) {
        this.name = name;
    }

//    public RealData(String name, String tradePrice) {
//        this.name = name;
//        this.tradePrice = tradePrice;
//    }
//
//    public RealData(String tradePrice, String name, String changePrice, String sales, String operatingProfit) {
//        this.tradePrice = tradePrice;
//        this.name = name;
//        this.changePrice = changePrice;
//        this.sales = sales;
//        this.operatingProfit = operatingProfit;
//    }

}
