package pl.samouczekprogramisty.szs.aoc2016.day10;


import pl.samouczekprogramisty.szs.aoc2016.day10.command.Command;
import pl.samouczekprogramisty.szs.aoc2016.day10.command.CommandFactory;

import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class BotOverlord implements DataSinks {
    private Map<Integer, Bot> bots = new HashMap<>();
    private Map<Integer, Output> outputs = new HashMap<>();

    private BotOverlord() {
    }

    @Override
    public Bot getBot(Integer botId) {
        if (!bots.containsKey(botId)) {
            bots.put(botId, new Bot(botId));
        }
        return bots.get(botId);
    }

    @Override
    public Output getOutput(Integer identifier) {
        if (!outputs.containsKey(identifier)) {
            outputs.put(identifier, new Output(identifier));
        }

        return outputs.get(identifier);
    }

    @Override
    public DataSink getDataSink(DataSink.Type sinkType, Integer sinkId) {
        if (sinkType == DataSink.Type.bot) {
            return getBot(sinkId);
        }
        return getOutput(sinkId);
    }

    public static BotOverlord processInstructions(List<String> instructions) {
        BotOverlord overlord = new BotOverlord();
        List<Command> commands = parseCommands(instructions, overlord);

        // by any means this isn't optimal. here we have O(n^2)
        // I *think* that this could be done with O(n)
        while (!commands.isEmpty()) {
            Iterator<Command> commandsIterator = commands.iterator();
            while (commandsIterator.hasNext()) {
                Command command = commandsIterator.next();
                if (command.canBeExecuted()) {
                    command.execute();
                    commandsIterator.remove();
                    break;
                }
            }
        }

        return overlord;
    }

    private static List<Command> parseCommands(List<String> instructions, BotOverlord overlord) {
        List<Command> commands = new LinkedList<>();

        for (String instruction : instructions) {
            Command command = CommandFactory.build(overlord, instruction);
            commands.add(command);
        }
        return commands;
    }

    public Bot getBotResponsibleFor(int lowerValue, int higherValue) {
        for (Bot bot : bots.values()) {
            if(bot.isResponsibleFor(lowerValue, higherValue)) {
                return bot;
            }
        }
        return null;
    }
}
