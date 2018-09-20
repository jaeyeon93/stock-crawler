package com.example.demo.dao;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class Research  {
    private static final Logger logger =  LoggerFactory.getLogger(Research.class);
    private Document doc;

    public Research(String url) throws IOException {
        doc = Jsoup.connect(url).get();
    }

    public List<String> getElements() {
        List<String> elements = Arrays.asList(doc.getElementsByClass("list_stockrate").get(0).text().split(" "));
        return elements;
    }

    public String getTotalCost() {
        return doc.select("#stockContent > ul.list_descstock > li:nth-child(2) > dl.second > dd").text();
    }

    public String getSalesMoney() {
        return doc.select("#performanceCorp > table > tbody > tr:nth-child(4) > td:nth-child(9)").text();
    }

    public String getProfit() {
        return doc.select("#performanceCorp > table > tbody > tr:nth-child(5) > td:nth-child(9)").text();
    }

    public String getBody() {
        int start = doc.body().text().indexOf("[");
        int end = doc.body().text().indexOf("]");
        return doc.body().text().substring(start+1, end).trim();
    }

    public String html() {
        return doc.html();
    }
}
