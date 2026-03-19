package performance;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Map;

/**
 * The sample applications typically deal with a collection of these prices, representing
 * the history of the stock over a period of time (e.g., 1 year or 25 years, depending on
 * the example):
 */
public interface StockPriceHistory {
    int STANDARD = 0;
    int LOGGING = 1;
    int COMPRESS = 2;
    int COMPRESS_LAZY = 3;
    int COMPRESS_SKIP = 4; // NOT USED
    int COMPRESS_EAGER = 5;
    int COMPRESS_EAGER_TRANSIENT = 6;

    StockPrice getPrice(Date d);

    Collection<StockPrice> getPrices(Date startDate, Date endDate);

    Map<Date, StockPrice> getAllEntries();

    Map<BigDecimal, ArrayList<Date>> getHistogram();

    BigDecimal getAveragePrice();

    Date getFirstDate();

    BigDecimal getHighPrice();

    Date getLastDate();

    BigDecimal getLowPrice();

    BigDecimal getStdDev();

    String getSymbol();
}
