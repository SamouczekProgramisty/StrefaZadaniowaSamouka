package pl.samouczekprogramisty.szs.aoc2016.day12.instructions;


import pl.samouczekprogramisty.szs.aoc2016.day12.DataHolder;
import pl.samouczekprogramisty.szs.aoc2016.day12.Register;
import pl.samouczekprogramisty.szs.aoc2016.day12.Registers;
import pl.samouczekprogramisty.szs.aoc2016.day12.StaticNumber;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class InstructionFactory {
    public static final Pattern CPY_INSTRUCTION = Pattern.compile("cpy (-?\\d+|[a-z]) ([a-z])");
    public static final Pattern INC_INSTRUCTION = Pattern.compile("inc ([a-z])");
    public static final Pattern DEC_INSTRUCTION = Pattern.compile("dec ([a-z])");
    public static final Pattern JNZ_INSTRUCTION = Pattern.compile("jnz (-?\\d+|[a-z]) (-?\\d+|[a-z])");

    private InstructionFactory() {
    }

    public static Instruction parse(Registers registers, String instruction) {
        Matcher matcher = CPY_INSTRUCTION.matcher(instruction);
        if (matcher.matches()) {
            DataHolder source = getDataHolder(registers, matcher.group(1));
            Register destination = registers.getRegister(matcher.group(2));
            return new Copy(source, destination);
        }
        matcher = INC_INSTRUCTION.matcher(instruction);
        if (matcher.matches()) {
            Register destination = registers.getRegister(matcher.group(1));
            return new Increment(destination);
        }
        matcher = DEC_INSTRUCTION.matcher(instruction);
        if (matcher.matches()) {
            Register destination = registers.getRegister(matcher.group(1));
            return new Decrement(destination);
        }
        matcher = JNZ_INSTRUCTION.matcher(instruction);
        if (matcher.matches()) {
            return new Jump(
                getDataHolder(registers, matcher.group(1)),
                getDataHolder(registers, matcher.group(2))
            );
        }
        throw new IllegalArgumentException("Couldn't parse [" + instruction + "]!");
    }

    public static DataHolder getDataHolder(Registers registers, String dataHolderName) {
        try {
            Integer value = Integer.valueOf(dataHolderName);
            return new StaticNumber(value);
        }
        catch (NumberFormatException e) {
            return registers.getRegister(dataHolderName);
        }
    }
}
