package com.caparniyazi.ds.lambda;

import lombok.Getter;
import lombok.Setter;

// The POJO class.
@Getter
@Setter
public class Stock {
    // Data fields
    private String symbol;
    private double price;
    private double units;

    Stock(String symbol, double price, double units) {
        this.symbol = symbol;
        this.price = price;
        this.units = units;
    }

    @Override
    public String toString() {
        return "Stock [symbol=" + symbol + ", price=" + price + ", units=" + units + "]";
    }
}
