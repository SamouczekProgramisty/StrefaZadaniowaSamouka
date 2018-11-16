package pl.samouczekprogramisty.szs.abacus;

import java.math.BigDecimal;
import java.util.function.BinaryOperator;

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

}
