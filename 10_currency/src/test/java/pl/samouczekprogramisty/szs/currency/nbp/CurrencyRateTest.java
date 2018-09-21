package pl.samouczekprogramisty.szs.currency.nbp;

import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.hamcrest.number.BigDecimalCloseTo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import javax.json.bind.JsonbBuilder;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

class CurrencyRateTest {

    private static CurrencyRate instance;

    @BeforeAll
    static void setUpClass() {
        String json = "{\"no\":\"174/A/NBP/2018\",\"effectiveDate\":\"2018-09-07\",\"mid\":3.6995}";
        instance = JsonbBuilder.create().fromJson(json, CurrencyRate.class);
    }

    @Test
    void shouldDeserializeId() {
        assertThat(instance.getId(), is("174/A/NBP/2018"));
    }

    @Test
    void shouldDeserializeEffectiveDate() {
        assertThat(instance.getEffectiveDate(), is(LocalDate.of(2018, 9, 7)));
    }

    @Test
    void shouldDeserializeRate() {
        assertThat(instance.getRate(), is(new BigDecimal("3.6995")));
    }

}