package performance;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.Date;

/**
 * The basic interface object to represent the price range of a stock on a given day,
 * along with a collection of option prices for that stock.
 */
public interface StockPrice {
    String getSymbol();

    Date getDate();

    BigDecimal getClosingPrice();

    BigDecimal getHigh();

    BigDecimal getLow();

    BigDecimal getOpeningPrice();

    boolean isYearHigh();

    boolean isYearLow();

    Collection<? extends StockOptionPrice> getOptions();
}
