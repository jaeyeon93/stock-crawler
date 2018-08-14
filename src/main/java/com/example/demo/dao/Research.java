package com.example.demo.dao;

import com.example.demo.domain.Stock;
import com.example.demo.domain.StockRepository;
import com.example.demo.web.StockController;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class Research {
    public static final Logger logger = LoggerFactory.getLogger(Research.class);
    private WebDriver driver;
    private String stockName;
    private String price;
    private String changeMoney;
    private String changePercent;

    public Research() {}

    public Research(String stockName) {
        this.stockName = stockName.toUpperCase();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless");
        System.setProperty("webdriver.chrome.driver", "/Users/jaeyeonkim/Desktop/web-crawler/src/main/java/com/example/demo/chromedriver");
        driver = new ChromeDriver(options);
        String startUrl = "http://finance.daum.net/";
        driver.get(startUrl);
    }


    public String search() {
        driver.findElement(By.id("name")).sendKeys(getStockName());
        driver.findElement(By.id("daumBtnSearch")).click();
        WebElement element = driver.findElement(By.cssSelector("a[title="+getStockName()+"]"));
        String detailUrl  = element.getAttribute("href");
        return detailUrl;
    }

    public Stock update(Stock original, boolean check) {
        driver.get(search());
        price = driver.findElement(By.xpath("//*[@id=\"topWrap\"]/div[1]/ul[2]/li[1]/em")).getText();
        changeMoney = driver.findElement(By.xpath("//*[@id=\"topWrap\"]/div[1]/ul[2]/li[2]/span")).getText();
        changePercent = driver.findElement(By.xpath("//*[@id=\"topWrap\"]/div[1]/ul[2]/li[3]/span")).getText();
        if (!check)
            return make();
        original.update(price, changeMoney, changePercent);
        logger.info("update info : {}", original.toString());
        return original;
    }

    public Stock make() {
        String salesMoney = driver.findElement(By.xpath("//*[@id=\"performanceCorp\"]/table/tbody/tr[4]/td[9]")).getText();
        String totalCost = driver.findElement(By.xpath("//*[@id=\"stockContent\"]/ul[2]/li[2]/dl[2]/dd")).getText();
        String yearProfit = driver.findElement(By.xpath("//*[@id=\"performanceCorp\"]/table/tbody/tr[5]/td[9]")).getText();
        return new Stock(getStockName(), getPrice(), salesMoney, yearProfit, totalCost, getChangeMoney(), getChangePercent(), search());
    }

    public String getPrice() {
        return price;
    }

    public String getChangeMoney() {
        return changeMoney;
    }

    public String getChangePercent() {
        return changePercent;
    }

    public String getStockName() {
        return stockName;
    }

}