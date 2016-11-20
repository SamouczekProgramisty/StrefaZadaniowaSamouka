package pl.samouczekprogramisty.szs.gradebook;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class SubjectTest {
    @Test
    public void shoudBeAbleToCreateSubject() {
        Subject subject = new Subject("matematyka");
        assertNotNull(subject);
    }

    @Test
    public void shouldBeAbleToComputeAverageNote() {
        Subject subject = new Subject("matematyka");
        subject.addNote(4);
        subject.addNote(5);
        assertEquals(4.5, subject.getAverage(), 0.001);
    }
}
