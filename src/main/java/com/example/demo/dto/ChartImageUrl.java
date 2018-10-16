package com.example.demo.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ChartImageUrl {

    @JsonProperty("day")
    private String day;

    @JsonProperty("month")
    private String month;

    @JsonProperty("month3")
    private String month3;

    @JsonProperty("year")
    private String year;

    public ChartImageUrl(String day, String month, String month3, String year) {
        this.day = day;
        this.month = month;
        this.month3 = month3;
        this.year = year;
    }
}
