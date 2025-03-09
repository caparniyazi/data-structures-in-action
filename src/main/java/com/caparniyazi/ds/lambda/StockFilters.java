package com.caparniyazi.ds.lambda;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public class StockFilters {
    public static List<Stock> bySymbol(List<Stock> list, String symbol) {
        List<Stock> result = new ArrayList<Stock>();

        for (Stock stock : list) {
            if (stock.getSymbol().equals(symbol)) {
                result.add(stock);
            }
        }
        return result;
    }

    public static List<Stock> byPriceAbove(List<Stock> list, double price) {
        List<Stock> result = new ArrayList<Stock>();

        for (Stock stock : list) {
            if (stock.getPrice() > price) {
                result.add(stock);
            }
        }
        return result;
    }

    public static List<Stock> filter(List<Stock> list, Predicate<Stock> predicate) {
        List<Stock> result = new ArrayList<>();

        for (Stock stock : list) {
            if (predicate.test(stock)) {
                result.add(stock);
            }
        }

        return result;
    }
}
