package pl.samouczekprogramisty.szs.currency;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertThrows;

@Tag("integration")
class NbpApiIntegrationTest {

    private static NbpApi api;

    @BeforeAll
    static void setUpClass() {
        api = new NbpApi();
    }

    @Test
    void shouldRequestURLWhenFetchingExchangeRate() {
        LocalDate exchangeDate = LocalDate.of(2018, 9, 7);
        assertThat(api.exchangeRate(exchangeDate, "USD"), is(new BigDecimal("3.6995")));
    }

    @Test
    void shouldThrowExceptionWhenResponseHasCodeOtherThan200() {
        LocalDate tooEarlyDate = LocalDate.of(18, 9, 7);
        ApiException exception = assertThrows(ApiException.class, () -> api.exchangeRate(tooEarlyDate, "USD"));
        // "Bark danych" ;)
        assertThat(exception.getMessage(), is("Something went wrong! [404] Bark danych / No data available"));
    }

    @Test
    void shouldThrowExceptionWhenResponseHasCodeOtherThan2001() {
        String illegalCurrencyCode = "peelen";
        ApiException exception = assertThrows(ApiException.class, () -> api.exchangeRate(LocalDate.of(2018, 9, 7), illegalCurrencyCode));
        assertThat(exception.getMessage(), is("Something went wrong! [404] Not Found"));
    }

}