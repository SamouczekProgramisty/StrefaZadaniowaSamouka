package pl.samouczekprogramisty.szs.gradebook;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GradeBookTest {

    private static final String SUBJECT = "matematyka";
    private static final String OTHER_SUBJECT = "fizyka";

    private GradeBook gradeBookWithSubject;

    @BeforeEach
    void setUp() {
        gradeBookWithSubject = new GradeBook();
        gradeBookWithSubject.addSubject(SUBJECT);
    }

    @Test
    void shouldBeAbleToCreateGradeBook() {
        GradeBook gradeBook = new GradeBook();
        assertNotNull(gradeBook);
    }

    @Test
    void shouldntAllowToAddSubjectThatAlreadyExists() {
        assertThrows(IllegalArgumentException.class, () -> gradeBookWithSubject.addSubject(SUBJECT));
    }

    @Test
    void shouldGetValidSubjectFromGradeBook() {
        Subject subject = gradeBookWithSubject.getSubject(SUBJECT);
        assertEquals(SUBJECT, subject.getName());
    }

    @Test
    void shouldntAllowToGetNonExistingSubject() {
        GradeBook gradeBook = new GradeBook();
        assertThrows(IllegalArgumentException.class, () -> gradeBook.getSubject(SUBJECT));
    }

    @Test
    void shouldBeAbleToAddNote() {
        gradeBookWithSubject.addNote(SUBJECT, 4.5);
    }

    @Test
    void shouldntAllowToAddANoteToNonExistingSubject() {
        GradeBook gradeBook = new GradeBook();
        assertThrows(IllegalArgumentException.class, () -> gradeBook.addNote(SUBJECT, 4.5));
    }

    @Test
    void shouldComputeGlobalAverageNote()  {
        gradeBookWithSubject.addSubject(OTHER_SUBJECT);
        gradeBookWithSubject.addNote(SUBJECT, 3);
        gradeBookWithSubject.addNote(SUBJECT, 5);
        gradeBookWithSubject.addNote(OTHER_SUBJECT, 2);
        gradeBookWithSubject.addNote(OTHER_SUBJECT, 4);

        assertEquals(3.5, gradeBookWithSubject.getAverageNote(), 0.001);
    }
}