package pl.samouczekprogramisty.szs.gradebook;


import java.util.HashMap;
import java.util.Map;

public class GradeBook {
    private Map<String, Subject> subjects = new HashMap<>();

    public void addSubject(String subject) {
        if (subjects.containsKey(subject)) {
            throw new IllegalStateException("Przedmiot " + subject + " juz istnieje!");
        }
        subjects.put(subject, new Subject(subject));
    }
}
