package codesamples.ioc;

import lombok.extern.slf4j.Slf4j;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Service Locator inverts service discovery.
 * Use it when you need centralized service management.
 */
@Slf4j
public class PaymentProcessorWithServiceLocator {
    public void processPayment() {
        // Get logger service from locator instead of creating it.
        Logger logger = (Logger) ServiceLocator.getService("logger");
        logger.log(Level.ALL, "Processing payment..");
    }
}
