package pl.samouczekprogramisty.szs.aoc2016.day12;

public class Register implements DataHolder {
    private Integer value = 0;
    private final String name;

    public Register(String name) {
        this.name = name;
    }

    @Override
    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public void increment() {
        value++;
    }

    public void decrement() {
        value--;
    }

    @Override
    public String toString() {
        return name + '[' + value + ']';
    }
}
