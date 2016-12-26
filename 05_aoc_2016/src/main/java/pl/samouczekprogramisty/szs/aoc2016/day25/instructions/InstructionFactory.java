package pl.samouczekprogramisty.szs.aoc2016.day25.instructions;

import pl.samouczekprogramisty.szs.aoc2016.day12.DataHolder;
import pl.samouczekprogramisty.szs.aoc2016.day12.Registers;
import pl.samouczekprogramisty.szs.aoc2016.day23.instructions.ToggeableInstructionFactory;
import pl.samouczekprogramisty.szs.aoc2016.day23.instructions.ToggleableInstruction;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static pl.samouczekprogramisty.szs.aoc2016.day12.instructions.InstructionFactory.getDataHolder;

public class InstructionFactory {

    public static final Pattern OUT_INSTRUCTION = Pattern.compile("out (\\d+|[a-z])");

    private InstructionFactory() {
    }

    public static ToggleableInstruction parse(Registers registers, String instruction) {
        try {
            return ToggeableInstructionFactory.parse(registers, instruction);
        }
        catch (IllegalArgumentException e) {
            Matcher matcher = OUT_INSTRUCTION.matcher(instruction);
            if (matcher.matches()) {
                DataHolder source = getDataHolder(registers, matcher.group(1));
                return new OutToggle(source);
            }
        }
        throw new IllegalArgumentException("Couldn't parse [" + instruction + "]!");
    }
}
