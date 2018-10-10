package com.example.demo.dto;

import com.example.demo.domain.Stock;

public class Field {
    private String title;
    private String value;
    private boolean Short;

    public Field(String title, String value) {
        this.title = title;
        this.value = value;
        this.Short = true;
    }

    public String getTitle() {
        return title;
    }

    public String getValue() {
        return value;
    }

    public boolean isShort() {
        return Short;
    }

    @Override
    public String toString() {
        return "Field{" +
                "title='" + title + '\'' +
                ", value='" + value + '\'' +
                ", short=" + Short +
                '}';
    }
}
