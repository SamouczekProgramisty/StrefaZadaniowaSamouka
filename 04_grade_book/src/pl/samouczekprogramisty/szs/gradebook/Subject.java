package pl.samouczekprogramisty.szs.gradebook;


import java.util.LinkedList;
import java.util.List;

public class Subject {
    private final String name;
    private List<Double> notes = new LinkedList<>();

    public Subject(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void addNote(double note) {
        notes.add(note);
    }
}
