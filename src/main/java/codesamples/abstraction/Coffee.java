package codesamples.abstraction;

public class Coffee {
    // Data fields
    private final CoffeeSelection selection;
    private double quantity;

    public Coffee(CoffeeSelection selection, double quantity) {
        this.selection = selection;
        this.quantity = quantity;
    }

    public CoffeeSelection getSelection() {
        return selection;
    }

    public double getQuantity() {
        return quantity;
    }

    public void setQuantity(double quantity) {
        this.quantity = quantity;
    }
}
