package com.caparniyazi.ds.lambda;

public class FluentShopping {
    public static void main(String[] args) {
        Order.place(order ->
                order.add("Shoes").add("Headphones").deliverAt("Istanbul, Nr:34"));
    }
}
