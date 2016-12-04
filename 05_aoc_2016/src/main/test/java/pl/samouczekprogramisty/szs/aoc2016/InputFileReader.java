package pl.samouczekprogramisty.szs.aoc2016;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class InputFileReader {
    private InputFileReader() {
    }

    public static List<String> readFileAsLines(String fileName) {
        List<String> lines = new ArrayList<>();

        try {
            try (BufferedReader input = new BufferedReader(new FileReader(getInputFile(fileName)))) {
                String line;
                while ((line = input.readLine()) != null) {
                    lines.add(line);
                }
            }
        }
        catch (IOException e) {
            throw new RuntimeException(e);
        }

        return lines;
    }

    private static File getInputFile(String fileName) {
        return new File(
                "src" + File.separator +
                "main" + File.separator +
                "test" + File.separator +
                "resources" + File.separator +
                fileName
        );
    }
}
