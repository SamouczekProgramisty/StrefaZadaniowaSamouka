package pl.samouczekprogramisty.szs.aoc2016.day10.command;


import pl.samouczekprogramisty.szs.aoc2016.day10.DataSinks;

public class CommandFactory {
    private CommandFactory() {
    }

    public static Command build(DataSinks dataSinks, String instruction) {
        if (instruction.startsWith("value ")) {
            return new LoadCommand(dataSinks, instruction);
        }
        return new EmitCommand(dataSinks, instruction);
    }
}
