package pl.samouczekprogramisty.szs.gradebook;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class SubjectTest {
    @Test
    void shoudBeAbleToCreateSubject() {
        Subject subject = new Subject("matematyka");
        assertNotNull(subject);
    }

    @Test
    void shouldBeAbleToComputeAverageNote() {
        Subject subject = new Subject("matematyka");
        subject.addNote(4);
        subject.addNote(5);
        assertEquals(4.5, subject.getAverage(), 0.001);
    }
}
