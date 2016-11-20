package pl.samouczekprogramisty.szs.gradebook;


import java.util.LinkedList;
import java.util.List;

public class GradeBook {
    private List<Subject> subjects = new LinkedList<>();
    public void addSubject(Subject subject) {
        subjects.add(subject);
    }
}
