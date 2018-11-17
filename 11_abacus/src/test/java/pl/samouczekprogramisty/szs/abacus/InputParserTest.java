package pl.samouczekprogramisty.szs.abacus;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.LinkedList;

import static java.util.Arrays.asList;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

class InputParserTest {

    @Test
    void shouldParseAllLinesExample1() {
        Calculator calculator = InputParser.parse(new LinkedList<>(asList("add 2", "multiply 3", "apply 10")));
        assertThat(calculator.compute(), is(new BigDecimal(36)));
    }

    @Test
    void shouldParseAllLinesExample2() {
        Calculator calculator = InputParser.parse(new LinkedList<>(asList("multiply 3", "add 2", "apply 10")));
        assertThat(calculator.compute(), is(new BigDecimal(32)));
    }

    @Test
    void shouldParseAllLinesExample3() {
        Calculator calculator = InputParser.parse(new LinkedList<>(asList("apply 10")));
        assertThat(calculator.compute(), is(new BigDecimal(10)));
    }

}