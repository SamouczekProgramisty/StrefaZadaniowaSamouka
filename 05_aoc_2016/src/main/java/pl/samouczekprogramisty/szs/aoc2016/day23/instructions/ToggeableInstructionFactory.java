package pl.samouczekprogramisty.szs.aoc2016.day23.instructions;

import pl.samouczekprogramisty.szs.aoc2016.day12.DataHolder;
import pl.samouczekprogramisty.szs.aoc2016.day12.Register;
import pl.samouczekprogramisty.szs.aoc2016.day12.Registers;
import pl.samouczekprogramisty.szs.aoc2016.day12.instructions.InstructionFactory;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static pl.samouczekprogramisty.szs.aoc2016.day12.instructions.InstructionFactory.getDataHolder;

public class ToggeableInstructionFactory {

    public static final Pattern TGL_INSTRUCTION = Pattern.compile("tgl ([a-z])");

    private ToggeableInstructionFactory() {
    }

    public static ToggleableInstruction parse(Registers registers, String instruction) {
        Matcher matcher = InstructionFactory.CPY_INSTRUCTION.matcher(instruction);
        if (matcher.matches()) {
            DataHolder source = getDataHolder(registers, matcher.group(1));
            Register destination = registers.getRegister(matcher.group(2));
            return new CopyToggle(source, destination);
        }
        matcher = InstructionFactory.INC_INSTRUCTION.matcher(instruction);
        if (matcher.matches()) {
            Register destination = registers.getRegister(matcher.group(1));
            return new IncrementToggle(destination);
        }
        matcher = InstructionFactory.DEC_INSTRUCTION.matcher(instruction);
        if (matcher.matches()) {
            Register destination = registers.getRegister(matcher.group(1));
            return new DecrementToggle(destination);
        }
        matcher = InstructionFactory.JNZ_INSTRUCTION.matcher(instruction);
        if (matcher.matches()) {
            return new JumpToggle(
                    getDataHolder(registers, matcher.group(1)),
                    getDataHolder(registers, matcher.group(2))
            );
        }
        matcher = TGL_INSTRUCTION.matcher(instruction);
        if (matcher.matches()) {
            return new Toggle(
                    registers.getRegister(matcher.group(1))
            );
        }
        throw new IllegalArgumentException("Couldn't parse [" + instruction + "]!");
    }
}


