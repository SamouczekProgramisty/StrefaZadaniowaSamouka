package pl.samouczekprogramisty.szs.currency;

import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pl.samouczekprogramisty.szs.currency.nbp.CurrencyDetailsTest;

import javax.json.bind.JsonbBuilder;
import java.math.BigDecimal;
import java.time.LocalDate;

import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.*;

class NbpApiTest {

    private ConnectionFactory factoryMock;
    private HttpConnection connectionMock;
    private NbpApi api;

    @BeforeEach
    void setUp() {
        factoryMock = mock(ConnectionFactory.class);
        connectionMock = mock(HttpConnection.class);
        when(factoryMock.build(anyString())).thenReturn(connectionMock);

        api = new NbpApi(factoryMock, JsonbBuilder.create());
    }

    @Test
    void shouldReturnValidExchangeRate() {
        when(connectionMock.response()).thenReturn(CurrencyDetailsTest.SAMPLE_RESPONSE);

        BigDecimal rate = api.exchangeRate(LocalDate.of(2018, 9, 7), "USD");
        MatcherAssert.assertThat(rate, Matchers.is(new BigDecimal("3.6995")));
    }

    @Test
    void shouldAttemptToConnectToRightUrl() {
        when(connectionMock.response()).thenReturn(CurrencyDetailsTest.SAMPLE_RESPONSE);

        api.exchangeRate(LocalDate.of(2018, 9, 7), "USD");

        verify(factoryMock).build("http://api.nbp.pl/api/exchangerates/rates/a/USD/2018-09-07");
    }

}