package pl.samouczekprogramisty.szs.currency;

import java.math.BigDecimal;
import java.time.LocalDate;

public interface ExchangeApi {
    /**
     * @param date date for which exchange rate should be returned
     * @param currencyCode currency code that should be checked, for example USD
     * @return excahge rate between PLN and currencyCode on date
     */
    BigDecimal exchangeRate(LocalDate date, String currencyCode);
}
