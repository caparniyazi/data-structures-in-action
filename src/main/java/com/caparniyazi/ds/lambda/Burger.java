package com.caparniyazi.ds.lambda;

public class Burger {
    // Data fields
    private String burgerType;

    public Burger() {
        this.burgerType = "";
    }

    public Burger(String burgerType) {
        this.burgerType = burgerType;
    }

    // Decoration
    public Burger addVeggies() {
        System.out.println("Adding veggies to the burger");
        return new Burger(this.burgerType += " Veggie");
    }

    // Decoration
    public Burger addCheese() {
        System.out.println("Adding cheese to the burger");
        return new Burger(this.burgerType += " Cheese");
    }

    @Override
    public String toString() {
        return String.format("%s", burgerType + " burger");
    }
}
