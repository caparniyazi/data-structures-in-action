package codesamples.ioc;

public class OrderService {

    private PaymentProcessor paymentProcessor;

    // Constructor injection
    public OrderService(PaymentProcessor paymentProcessor) {
        this.paymentProcessor = paymentProcessor;
    }

    public void placeOrder(double amount) {
        // Business logic
        paymentProcessor.processPayment(amount);
    }
}
