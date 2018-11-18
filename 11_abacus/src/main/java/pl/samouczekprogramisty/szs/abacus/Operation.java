package pl.samouczekprogramisty.szs.abacus;

import java.math.BigDecimal;
import java.util.function.BinaryOperator;
import java.util.function.Function;

public enum Operation {

    ADD(BigDecimal::add),
    SUBTRACT(BigDecimal::subtract),
    MULTIPLY(BigDecimal::multiply),
    DIVIDE(BigDecimal::divide);

    private final BinaryOperator<BigDecimal> command;

    Operation(BinaryOperator<BigDecimal> command) {
        this.command = command;
    }

    public BigDecimal apply(BigDecimal value1, BigDecimal value2) {
        return command.apply(value1, value2);
    }

    public static Function<BigDecimal, BigDecimal> parse(String line) {
        String[] tokens = line.split(" ");
        if (tokens.length != 2) {
            throw new IllegalArgumentException("Line (" + line + ") has illegal format!");
        }
        BigDecimal operand = new BigDecimal(tokens[1]);
        return x -> Operation.valueOf(tokens[0].toUpperCase()).apply(x, operand);
    }

}
