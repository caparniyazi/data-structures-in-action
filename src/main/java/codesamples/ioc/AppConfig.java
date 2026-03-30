package codesamples.ioc;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * DI inverts dependency creation and binding.
 * Use it when you want loose coupling and easier testing.
 * IOC is the broader principle.
 * DI is a specific implementation of IOC.
 */
@Configuration  // Using with Spring Framework
public class AppConfig {
    @Bean
    public PaymentProcessor paymentProcessor() {
        return new CreditCardProcessor();
    }

    @Bean
    public OrderService orderService(PaymentProcessor paymentProcessor) {
        return new OrderService(paymentProcessor);
    }
}
