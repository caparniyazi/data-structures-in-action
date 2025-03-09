package com.caparniyazi.ds.lambda;

import java.util.function.Function;

public class BurgerShop {
    // Data fields
    Function<Burger, Burger> decoration;

    public BurgerShop(Function<Burger, Burger> decoration) {
        this.decoration = decoration;
    }

    public Burger use(Burger baseBurger) {
        System.out.println("Base burger: " + baseBurger);
        return decoration.apply(baseBurger);
    }
}
