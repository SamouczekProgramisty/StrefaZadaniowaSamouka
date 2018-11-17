package pl.samouczekprogramisty.szs.abacus;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) throws IOException {
        assert args.length == 1;

        try (BufferedReader reader = new BufferedReader(new FileReader(args[0]))) {
            LinkedList<String> fileLines = reader.lines().collect(Collectors.toCollection(LinkedList::new));
            Calculator calculator = InputParser.parse(fileLines);
            System.out.println(calculator.compute());
        }
    }
}
