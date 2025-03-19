package com.caparniyazi.ds.rxjava;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;

public class Book implements SubjectLibrary {
    // Data fields

    @Getter
    @Setter
    private String name;

    @Getter
    @Setter
    private String type;

    @Getter
    @Setter
    private String author;

    @Getter
    @Setter
    private double price;
    @Getter
    private String inStock;
    @Getter
    @Setter
    private ArrayList<Observer> observers = new ArrayList<>();

    public Book(String name, String type, String author, double price, String inStock) {
        this.name = name;
        this.type = type;
        this.author = author;
        this.price = price;
        this.inStock = inStock;
    }

    public void setInStock(String inStock) {
        this.inStock = inStock;
        System.out.println("Availability changed from Sold out to Back in stock\n");
        notifyObservers();
    }

    @Override
    public void subscribeObserver(Observer observer) {
        observers.add(observer);
    }

    @Override
    public void unsubscribeObserver(Observer observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyObservers() {
        System.out.println(
                "Book name: " + this.name +
                        ", Book type: " + this.type +
                        ", Price: " + this.price +
                        ", Author: " + this.author +
                        ", is now " + this.inStock + ". So, please notify all users.\n");
        for (Observer observer : observers) {
            observer.update(this.inStock);
        }
    }
}
