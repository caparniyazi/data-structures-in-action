package codesamples.abstraction;

public class Grinder {
    public GroundCoffee grind(CoffeeBean coffeeBean, double quantity) {
        return new GroundCoffee();
    }
}
