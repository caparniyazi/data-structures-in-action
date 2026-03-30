package codesamples.ioc;

/**
 * Credit-Card implementation.
 */
public class CreditCardProcessor implements PaymentProcessor {
    @Override
    public void processPayment(double amount) {
        System.out.println("Processing " + amount + " via Credit Card.");
    }
}
