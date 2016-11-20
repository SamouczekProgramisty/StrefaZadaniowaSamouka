package pl.samouczekprogramisty.szs.gradebook;

import org.junit.Test;

import static org.junit.Assert.assertNotNull;

public class GradeBookTest {
    @Test
    public void shouldBeAbleToCreateGradeBook() {
        GradeBook gradeBook = new GradeBook();
        assertNotNull(gradeBook);
    }

    @Test
    public void shouldBeAbleToAddNewSubject() {
        GradeBook gradeBook = new GradeBook();
        gradeBook.addSubject(new Subject("matematyka"));
    }
}