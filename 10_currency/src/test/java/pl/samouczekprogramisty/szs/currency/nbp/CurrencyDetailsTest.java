package pl.samouczekprogramisty.szs.currency.nbp;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import javax.json.bind.JsonbBuilder;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;

public class CurrencyDetailsTest {

    public static final String SAMPLE_RESPONSE = "{\"table\":\"A\",\"currency\":\"dolar amerykański\",\"code\":\"USD\",\"rates\":[{\"no\":\"174/A/NBP/2018\",\"effectiveDate\":\"2018-09-07\",\"mid\":3.6995}]}";

    private static CurrencyDetails instance;

    @BeforeAll
    static void parseResponse() {
        instance = JsonbBuilder.create().fromJson(SAMPLE_RESPONSE, CurrencyDetails.class);
    }

    @Test
    void shouldDeserializeTable() {
        assertThat(instance.getCode(), is("USD"));
    }

    @Test
    void shouldDeserializeCode() {
        assertThat(instance.getTable(), is("A"));
    }

    @Test
    void shouldDeserializeCurrency() {
        assertThat(instance.getCurrency(), is("dolar amerykański"));
    }

    @Test
    void shouldDeserializeRates() {
        assertThat(instance.getRates(), hasSize(1));
    }
}