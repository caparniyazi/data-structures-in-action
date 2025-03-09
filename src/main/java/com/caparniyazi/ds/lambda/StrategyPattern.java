package com.caparniyazi.ds.lambda;

import java.util.ArrayList;
import java.util.List;

public class StrategyPattern {
    public static void main(String[] args) {
        List<Stock> stocks = new ArrayList<Stock>();
        stocks.add(new Stock("AAPL", 318.65, 10));
        stocks.add(new Stock("MSFT", 166.86, 45));
        stocks.add(new Stock("Google", 99, 12.5));
        stocks.add(new Stock("AMZ", 1866.74, 45));
        stocks.add(new Stock("GOOGLE", 1480.20, 3.5));
        stocks.add(new Stock("AAPL", 318.65, 8));
        stocks.add(new Stock("AMZ", 1866.74, 9));

//        StockFilters.bySymbol(stocks, "AMZ").forEach(System.out::println);
//        System.out.println("----------------------------------------------------");
//        StockFilters.byPriceAbove(stocks, 300).forEach(System.out::println);

        // Strategy pattern with lambda.
        StockFilters.filter(stocks, e -> e.getSymbol().equals("AMZ")).forEach(System.out::println);
    }
}
