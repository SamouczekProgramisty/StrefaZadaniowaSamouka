package pl.samouczekprogramisty.szs.abacus;

import java.math.BigDecimal;
import java.util.LinkedList;

public class InputParser {

    public static Calculator parse(LinkedList<String> fileLines) {
        String lastLine = fileLines.peekLast();
        String[] tokens = lastLine.split(" ");


        if (!tokens[0].toUpperCase().equals("APPLY")) {
            throw new IllegalArgumentException("Last line (" + lastLine + ") doesn't contain APPLY instruction!");
        }

        Calculator calculator = new Calculator(new BigDecimal(tokens[1]));

        fileLines.subList(0, fileLines.size() - 1).stream()
                .map(Operation::parse)
                .forEach(calculator::execute);

        return calculator;
    }

}
