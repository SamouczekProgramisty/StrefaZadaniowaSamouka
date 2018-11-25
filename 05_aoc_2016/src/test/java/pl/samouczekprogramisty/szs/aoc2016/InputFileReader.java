package pl.samouczekprogramisty.szs.aoc2016;

import java.io.*;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;


public class InputFileReader {
    private InputFileReader() {
    }

    public static String readFile(String fileName) {
        StringBuilder file = new StringBuilder();
        List<String> lines = readFileAsLines(fileName);
        for (int lineIndex = 0; lineIndex < lines.size(); lineIndex++) {
            String line = lines.get(lineIndex);
            file.append(line);
            boolean isntLastLine = lineIndex != lines.size() - 1;
            if (isntLastLine) {
                file.append(System.lineSeparator());
            }
        }
        return file.toString();
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
        String resourceLocation = File.separator + fileName;
        String resourcePath = InputFileReader.class.getResource(resourceLocation).getPath();
        return new File(resourcePath);
    }
}
