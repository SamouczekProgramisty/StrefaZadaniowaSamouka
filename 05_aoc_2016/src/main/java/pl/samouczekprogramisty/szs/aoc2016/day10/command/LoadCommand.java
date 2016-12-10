package pl.samouczekprogramisty.szs.aoc2016.day10.command;


import pl.samouczekprogramisty.szs.aoc2016.day10.Bot;
import pl.samouczekprogramisty.szs.aoc2016.day10.DataSinks;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LoadCommand implements Command {

    public static final Pattern PATTERN = Pattern.compile("value (\\d+) goes to bot (\\d+)");

    private final Bot bot;
    private final Integer value;

    public LoadCommand(DataSinks dataSinks, String instruction) {
        Matcher matcher = PATTERN.matcher(instruction);
        if (!matcher.matches()) {
            throw new IllegalArgumentException(String.format("Instruction [%s] can't be parsed!", instruction));
        }
        value = Integer.valueOf(matcher.group(1));
        bot = dataSinks.getBot(Integer.valueOf(matcher.group(2)));
    }

    @Override
    public void execute() {
        bot.consumeData(value);
    }

    @Override
    public boolean canBeExecuted() {
        return true;
    }
}
