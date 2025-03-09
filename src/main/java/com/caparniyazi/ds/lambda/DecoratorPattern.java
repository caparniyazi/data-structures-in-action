package com.caparniyazi.ds.lambda;

public class DecoratorPattern {
    public static void main(String[] args) {
        Burger myOrder = new BurgerShop(Burger::addCheese).use(new BurgerShop(Burger::addVeggies).use(new Burger()));
        System.out.println("I get " + myOrder);
    }
}
