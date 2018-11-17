package pl.samouczekprogramisty.szs.abacus;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

class CalculatorTest {

    @Test
    void shouldJoinOperations() {
        Calculator calculator = new Calculator(BigDecimal.TEN);
        calculator.execute(Operation.parse("add 2"));
        calculator.execute(Operation.parse("multiply 3"));
        BigDecimal result = calculator.compute();

        assertThat(result, is(new BigDecimal(36)));
    }

    @Test
    void shouldJoinOtherOperations() {
        Calculator calculator = new Calculator(BigDecimal.TEN);
        calculator.execute(Operation.parse("multiply 3"));
        calculator.execute(Operation.parse("add 2"));
        BigDecimal result = calculator.compute();

        assertThat(result, is(new BigDecimal(32)));
    }

    @Test
    void shouldHandleSingleApply() {
        Calculator calculator = new Calculator(BigDecimal.TEN);
        BigDecimal result = calculator.compute();

        assertThat(result, is(BigDecimal.TEN));
    }

}