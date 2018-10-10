package com.example.demo.dto;

import com.example.demo.domain.Stock;

import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.function.BiConsumer;

public class Attachment extends HashMap<String, Field> {
    private String color;
    private String title;
    private Field [] fields;
    private String footer;
    private String [] titles = {"주가", "변동률", "변동가격"};
    private HashMap<String, Object> map;

    public Attachment(String color, Stock stock) {
        this.color = color;
        this.title = "<" + stock.getDetailUrl() + " |" + stock.getName();
        this.footer = "Made by Jimmy";
        map = new HashMap();
        map.put("주가", stock.getCost());
        map.put("변동가격", stock.getRate());
        map.put("변동률", stock.getUpdn());
    }

    public String getColor() {
        return color;
    }

    public String getTitle() {
        return title;
    }

    public Field[] getFields() {
        return fields;
    }

    public String getFooter() {
        return footer;
    }

    public HashMap<String, Object> getMap() {
        return map;
    }
    
    @Override
    public String toString() {
        return "Attachment{" +
                "color='" + color + '\'' +
                ", title='" + title + '\'' +
                ", fields=" + Arrays.toString(fields) +
                ", footer='" + footer + '\'' +
                ", titles=" + Arrays.toString(titles) +
                ", map=" + map +
                '}';
    }
}
