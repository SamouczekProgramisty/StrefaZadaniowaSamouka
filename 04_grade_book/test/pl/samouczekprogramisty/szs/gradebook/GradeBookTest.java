package pl.samouczekprogramisty.szs.gradebook;

import org.junit.Test;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertEquals;

public class GradeBookTest {

    private static final String SUBJECT = "matematyka";

    @Test
    public void shouldBeAbleToCreateGradeBook() {
        GradeBook gradeBook = new GradeBook();
        assertNotNull(gradeBook);
    }

    @Test(expected = IllegalStateException.class)
    public void shouldntAllowToAddSubjectThatAlreadyExists() {
        GradeBook gradeBook = new GradeBook();
        gradeBook.addSubject(SUBJECT);
        gradeBook.addSubject(SUBJECT);
    }

    @Test
    public void shouldGetValidSubjectFromGradeBook() {
        GradeBook gradeBook = new GradeBook();
        gradeBook.addSubject(SUBJECT);
        Subject subject = gradeBook.getSubject(SUBJECT);
        assertEquals(SUBJECT, subject.getName());
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldntAllowToGetNonExistingSubject() {
        GradeBook gradeBook = new GradeBook();
        gradeBook.getSubject(SUBJECT);
    }
}