package pl.samouczekprogramisty.szs.gradebook;


import java.util.HashMap;
import java.util.Map;

public class GradeBook {
    private Map<String, Subject> subjects = new HashMap<>();

    public void addSubject(String subject) {
        if (subjects.containsKey(subject)) {
            throw new IllegalStateException(String.format("Przedmiot  %s juz istnieje!", subject));
        }
        subjects.put(subject, new Subject(subject));
    }

    public Subject getSubject(String subject) {
        if (subjects.containsKey(subject)) {
            return subjects.get(subject);
        }
        throw new IllegalArgumentException(String.format("Przedmiot %s nie istnieje!", subject));
    }

    public void addNote(String subjectName, double note) {
        Subject subject = getSubject(subjectName);
        subject.addNote(note);
    }
}
