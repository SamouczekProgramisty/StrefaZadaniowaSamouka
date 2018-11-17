package pl.samouczekprogramisty.szs.abacus;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.function.Function;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

class OperationTest {

    @Test
    void shouldBeAbleToAdd() {
        assertThat(Operation.ADD.apply(BigDecimal.TEN, BigDecimal.ONE), is(new BigDecimal(11)));
    }

    @Test
    void shouldBeAbleToSubtract() {
        assertThat(Operation.SUBTRACT.apply(BigDecimal.TEN, BigDecimal.ONE), is(new BigDecimal(9)));
    }

    @Test
    void shouldBeAbleToMultiply() {
        assertThat(Operation.MULTIPLY.apply(BigDecimal.TEN, new BigDecimal(2)), is(new BigDecimal(20)));
    }

    @Test
    void shouldBeAbleToDivide() {
        assertThat(Operation.DIVIDE.apply(BigDecimal.TEN, new BigDecimal(2)), is(new BigDecimal(5)));
    }

    @Test
    void shouldParseDivision() {
        Function<BigDecimal, BigDecimal> curriedFunction = Operation.parse("divide 10");
        BigDecimal result = curriedFunction.apply(new BigDecimal(20));
        assertThat(result, is(new BigDecimal(2)));
    }

    @Test
    void shouldParseMultiplication() {
        Function<BigDecimal, BigDecimal> curriedFunction = Operation.parse("MULTIPLY 10");
        BigDecimal result = curriedFunction.apply(new BigDecimal(20));
        assertThat(result, is(new BigDecimal(200)));
    }

    @Test
    void shouldParseSubtraction() {
        Function<BigDecimal, BigDecimal> curriedFunction = Operation.parse("SUBTRACT 5");
        BigDecimal result = curriedFunction.apply(new BigDecimal(15));
        assertThat(result, is(new BigDecimal(10)));
    }

    @Test
    void shouldParseAddition() {
        Function<BigDecimal, BigDecimal> curriedFunction = Operation.parse("add 5");
        BigDecimal result = curriedFunction.apply(new BigDecimal(15));
        assertThat(result, is(new BigDecimal(20)));
    }

}