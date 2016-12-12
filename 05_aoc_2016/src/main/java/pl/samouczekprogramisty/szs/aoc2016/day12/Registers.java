package pl.samouczekprogramisty.szs.aoc2016.day12;


import java.util.Map;
import java.util.TreeMap;

public class Registers {
    private Map<String, Register> registers = new TreeMap<>();

    public Register getRegister(String name) {
        if (!registers.containsKey(name)) {
            registers.put(name, new Register(name));
        }
        return registers.get(name);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Register register : registers.values()) {
            sb.append(register);
            sb.append(" ");
        }
        return sb.toString();
    }
}
