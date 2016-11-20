package pl.samouczekprogramisty.szs.gradebook;

import org.junit.Test;

import static org.junit.Assert.assertNotNull;

public class GradeBookTest {
    @Test
    public void shouldBeAbleToCreateGradeBook() {
        GradeBook gradeBook = new GradeBook();
        assertNotNull(gradeBook);
    }

    @Test(expected = IllegalStateException.class)
    public void shouldntAllowToAddSubjectThatAlreadyExists() {
        GradeBook gradeBook = new GradeBook();
        gradeBook.addSubject("matematyka");
        gradeBook.addSubject("matematyka");
    }

    @Test
    public void shouldBeAbleToGetSubjectFromGradeBook() {
        GradeBook gradeBook = new GradeBook();
        gradeBook.addSubject("matematyka");
        assertNotNull(gradeBook.getSubject("matematyka"));
    }
}