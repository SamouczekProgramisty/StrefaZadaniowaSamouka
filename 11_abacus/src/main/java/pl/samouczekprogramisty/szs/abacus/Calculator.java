package pl.samouczekprogramisty.szs.abacus;

import java.math.BigDecimal;
import java.util.function.Function;

public class Calculator {

    private Function<BigDecimal, BigDecimal> linkedOperations = Function.identity();

    public void execute(Function<BigDecimal, BigDecimal> operation) {
        linkedOperations = linkedOperations.andThen(operation);
    }

    public BigDecimal apply(BigDecimal initialValue) {
        return linkedOperations.apply(initialValue);
    }

}
