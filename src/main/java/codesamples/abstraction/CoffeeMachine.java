package codesamples.abstraction;

import java.util.HashMap;
import java.util.Map;

public class CoffeeMachine {
    // Data fields
    private Map<CoffeeSelection, Configuration> configMap;
    private final Map<CoffeeSelection, CoffeeBean> beans;
    private Grinder grinder;    // Abstracts the complexity of grinding the coffee.
    private BrewingUnit brewingUnit;    // Hides the complexity of the brewing process.

    public CoffeeMachine(Map<CoffeeSelection, CoffeeBean> beans) {
        this.beans = beans;
        this.grinder = new Grinder();
        this.brewingUnit = new BrewingUnit();

        this.configMap = new HashMap<>();
        this.configMap.put(CoffeeSelection.ESPRESSO, new Configuration(8, 28));
        this.configMap.put(CoffeeSelection.FILTER_COFFEE, new Configuration(30, 480));
    }

    // Methods
    public Coffee brewCoffee(CoffeeSelection selection) throws CoffeeException {
        return switch (selection) {
            case FILTER_COFFEE -> brewFilterCoffee();
            case ESPRESSO -> brewEspresso();
            default -> throw new CoffeeException("CoffeeSelection [" + selection + "] not supported!");
        };
    }

    private Coffee brewFilterCoffee() {
        Configuration config = configMap.get(CoffeeSelection.FILTER_COFFEE);

        // Grind the coffee beans
        GroundCoffee groundCoffee = this.grinder.grind(this.beans.get(CoffeeSelection.FILTER_COFFEE), config.getQuantityCoffee());

        // Brew a filter coffee
        return this.brewingUnit.brew(CoffeeSelection.FILTER_COFFEE, groundCoffee, config.getQuantityWater());
    }

    private Coffee brewEspresso() {
        Configuration config = configMap.get(CoffeeSelection.ESPRESSO);

        // Grind the coffee beans
        GroundCoffee groundCoffee = this.grinder.grind(this.beans.get(CoffeeSelection.ESPRESSO), config.getQuantityCoffee());

        // Brew an espresso
        return this.brewingUnit.brew(CoffeeSelection.ESPRESSO, groundCoffee, config.getQuantityWater());
    }
}
