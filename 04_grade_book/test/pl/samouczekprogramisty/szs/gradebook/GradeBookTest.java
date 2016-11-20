package pl.samouczekprogramisty.szs.gradebook;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class GradeBookTest {

    private static final String SUBJECT = "matematyka";
    private GradeBook gradeBookWithSubject;

    @Before
    public void setUp() {
        gradeBookWithSubject = new GradeBook();
        gradeBookWithSubject.addSubject(SUBJECT);
    }

    @Test
    public void shouldBeAbleToCreateGradeBook() {
        GradeBook gradeBook = new GradeBook();
        assertNotNull(gradeBook);
    }

    @Test(expected = IllegalStateException.class)
    public void shouldntAllowToAddSubjectThatAlreadyExists() {
        gradeBookWithSubject.addSubject(SUBJECT);
    }

    @Test
    public void shouldGetValidSubjectFromGradeBook() {
        Subject subject = gradeBookWithSubject.getSubject(SUBJECT);
        assertEquals(SUBJECT, subject.getName());
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldntAllowToGetNonExistingSubject() {
        GradeBook gradeBook = new GradeBook();
        gradeBook.getSubject(SUBJECT);
    }

    @Test
    public void shouldBeAbleToAddNote() {
        gradeBookWithSubject.addNote(SUBJECT, 4.5);
    }
}