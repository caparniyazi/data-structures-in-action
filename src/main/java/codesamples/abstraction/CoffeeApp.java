package codesamples.abstraction;

import java.util.HashMap;
import java.util.Map;

public class CoffeeApp {
    public static void main(String[] args) {
        // Create a map of available coffee beans.
        Map<CoffeeSelection, CoffeeBean> beans = new HashMap<>();
        beans.put(CoffeeSelection.ESPRESSO, new CoffeeBean("My favorite espresso bean", 1000));
        beans.put(CoffeeSelection.FILTER_COFFEE, new CoffeeBean("My favorite filter coffee bean", 1000));

        // Get a new machine object
        // The abstraction provided by the coffee machine class hides all the details of the brewing process.

        CoffeeMachine machine = new CoffeeMachine(beans);

        // Brew a fresh coffee.
        try {
            Coffee coffee = machine.brewCoffee(CoffeeSelection.ESPRESSO);
            System.out.println("Coffee is ready.");
        } catch (CoffeeException e) {
            e.printStackTrace();
        }
    }
}
