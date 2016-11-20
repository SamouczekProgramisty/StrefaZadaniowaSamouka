package pl.samouczekprogramisty.szs.gradebook;

import org.junit.Test;

import static org.junit.Assert.assertNotNull;

public class SubjectTest {
    @Test
    public void shoudBeAbleToCreateSubject() {
        Subject subject = new Subject("matematyka");
        assertNotNull(subject);
    }
}
