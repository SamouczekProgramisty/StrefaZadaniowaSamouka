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

    @Test(expected = IllegalArgumentException.class)
    public void shouldntAllowToAddANoteToNonExistingSubject() {
        GradeBook gradeBook = new GradeBook();
        gradeBook.addNote(SUBJECT, 4.5);
    }

    @Test
    public void shouldComputeGlobalAverageNote()  {
        gradeBookWithSubject.addSubject("fizyka");
        gradeBookWithSubject.addNote(SUBJECT, 3);
        gradeBookWithSubject.addNote(SUBJECT, 5);
        gradeBookWithSubject.addNote("fizyka", 2);
        gradeBookWithSubject.addNote("fizyka", 4);

        assertEquals(3.5, gradeBookWithSubject.getAverageNote(), 0.001);
    }
}