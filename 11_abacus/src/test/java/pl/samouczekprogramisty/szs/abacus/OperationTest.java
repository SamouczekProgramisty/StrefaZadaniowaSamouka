package pl.samouczekprogramisty.szs.abacus;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.hamcrest.MatcherAssert.assertThat;

class OperationTest {

    @Test
    void shouldBeAbleToAdd() {
        assertThat(Operation.ADD.apply(BigDecimal.TEN, BigDecimal.ONE), Matchers.is(new BigDecimal(11)));
    }

    @Test
    void shouldBeAbleToSubtract() {
        assertThat(Operation.SUBTRACT.apply(BigDecimal.TEN, BigDecimal.ONE), Matchers.is(new BigDecimal(9)));
    }

    @Test
    void shouldBeAbleToMultiply() {
        assertThat(Operation.MULTIPLY.apply(BigDecimal.TEN, new BigDecimal(2)), Matchers.is(new BigDecimal(20)));
    }

    @Test
    void shouldBeAbleToDivide() {
        assertThat(Operation.DIVIDE.apply(BigDecimal.TEN, new BigDecimal(2)), Matchers.is(new BigDecimal(5)));
    }

}