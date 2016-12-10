package pl.samouczekprogramisty.szs.aoc2016.day10.command;


import pl.samouczekprogramisty.szs.aoc2016.day10.Bot;
import pl.samouczekprogramisty.szs.aoc2016.day10.DataSink;
import pl.samouczekprogramisty.szs.aoc2016.day10.DataSinks;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EmitCommand implements Command {

    public static final Pattern PATTERN = Pattern.compile("bot (\\d+) gives low to (bot|output) (\\d+) and high to (bot|output) (\\d+)");

    private final Bot bot;

    private final DataSink lowSink;
    private final DataSink highSink;

    public EmitCommand(DataSinks dataSinks, String instruction) {
        Matcher matcher = PATTERN.matcher(instruction);
        if (!matcher.matches()) {
            throw new IllegalArgumentException(String.format("Instruction [%s] can't be parsed!", instruction));
        }

        bot = dataSinks.getBot(Integer.valueOf(matcher.group(1)));

        DataSink.Type lowSinkType = DataSink.Type.valueOf(matcher.group(2));
        lowSink = dataSinks.getDataSink(lowSinkType, Integer.valueOf(matcher.group(3)));

        DataSink.Type highSinkType = DataSink.Type.valueOf(matcher.group(4));
        highSink = dataSinks.getDataSink(highSinkType, Integer.valueOf(matcher.group(5)));
    }

    @Override
    public void execute() {
        bot.emitData(lowSink, highSink);
    }

    @Override
    public boolean canBeExecuted() {
        return bot.isFilled();
    }
}
