package pl.samouczekprogramisty.szs.abacus;

import java.math.BigDecimal;
import java.util.function.Function;

public class Calculator {

    private final BigDecimal initialValue;
    private Function<BigDecimal, BigDecimal> linkedOperations = Function.identity();

    public Calculator(BigDecimal initialValue) {
        this.initialValue = initialValue;
    }

    public void execute(Function<BigDecimal, BigDecimal> operation) {
        linkedOperations = linkedOperations.andThen(operation);
    }

    public BigDecimal compute() {
        return linkedOperations.apply(initialValue);
    }

}
