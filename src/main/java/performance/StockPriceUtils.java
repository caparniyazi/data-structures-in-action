package performance;

import java.math.BigInteger;
import java.util.concurrent.ThreadLocalRandom;
/**
 * The stock example is based on a sample application that calculates
 * the “historical” high and low price of a stock over a range of dates, as well as the standard
 * deviation during that time. Historical is in quotes here because in the application,
 * all the data is fictional; the prices and the stock symbols are randomly generated.
 */
public class StockPriceUtils {
    public static final int STANDARD = 0;
    public static final int LOGGER = 1;
    public static final int HALF = 2;
    public static final int ALTERNATE = 3;

    public static final int SYMBOL_LENGTH = 4;
    public static int NSYMBOLS;

    static {
        NSYMBOLS = 1;
        for (int i = 0; i < SYMBOL_LENGTH; i++) {
            NSYMBOLS = 26 * NSYMBOLS;
        }
    }


    public static String makeSymbol(int symbolNumber) {
        String s = new BigInteger("" + symbolNumber).toString(26);
        StringBuilder sb = new StringBuilder();
        int len = SYMBOL_LENGTH - s.length();
        while (len-- > 0) {
            sb.append('A');
        }
        for (int i = 0; i < s.length(); i++) {
            char c = Character.toUpperCase(s.charAt(i));
            if (c >= '0' & c <= '9') {
                c = (char) ('A' + c - '0');
            } else {
                c = (char) (c + 10);
            }
            sb.append(c);
        }
        return sb.toString();
    }

    public static String getRandomSymbol() {
        return makeSymbol(ThreadLocalRandom.current().nextInt(NSYMBOLS));
    }
}
