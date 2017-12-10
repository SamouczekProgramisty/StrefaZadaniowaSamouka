package pl.samouczekprogramisty.szs.floatingpoint;

import org.junit.Test;
import pl.samouczekprogramisty.szs.floatingpoint.FloatingPoint.Format;

import java.math.BigDecimal;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class TestFloatingPoint {

    @Test
    public void shouldBeAbleToParseSinglePrecisionFloatingPointNumber() {
        assertThat(FloatingPoint.instantiate(Format.SINGLE_PRECISION, "0.1").toString(), is(equalTo("0 01111011 10011001100110011001101")));
    }

    @Test
    public void shouldBeAbleToParseSinglePrecisionFloatingPointNumber2() {
        assertThat(FloatingPoint.instantiate(Format.SINGLE_PRECISION, "270.125").toString(), is(equalTo("0 01111011 10011001100110011001101")));
    }

    @Test
    public void shouldBeAbleToParseDoublePrecisionFloatingPointNumber() {
        assertThat(FloatingPoint.instantiate(Format.DOUBLE_PRECISION, "0.1").toString(), is(equalTo("0 01111111011 1001100110011001100110011001100110011001100110011010")));
    }

    @Test
    public void shouldBeAbleToShowErrorDuringConversion() {
        assertThat(FloatingPoint.instantiate(Format.SINGLE_PRECISION, "0.1").getError(), is(greaterThan(BigDecimal.ZERO)));
    }

}
