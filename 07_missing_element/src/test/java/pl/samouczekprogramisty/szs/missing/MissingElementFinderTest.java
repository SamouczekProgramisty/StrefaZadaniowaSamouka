package pl.samouczekprogramisty.szs.missing;

import org.junit.Test;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;
import static pl.samouczekprogramisty.szs.missing.MissingElementFinder.findMissing;

public class MissingElementFinderTest {

    @Test
    public void shouldFindMissingElementForEmptyArray() {
        assertThat(findMissing(), equalTo(0));
    }

    @Test
    public void shouldFindMissingElementForOneElementArray() {
        assertThat(findMissing(0), equalTo(1));
        assertThat(findMissing(1), equalTo(0));
    }

    @Test
    public void shouldFindMissingElementInSampleInput() {
        assertThat(findMissing(0, 2, 1, 4), equalTo(3));
    }

    @Test
    public void shouldFindMissingElementIfItsFirst() {
        assertThat(findMissing(3, 2, 1, 4), equalTo(0));
    }

    @Test
    public void shouldFindMissingElementIfItsLast() {
        assertThat(findMissing(0, 3, 2, 1, 4), equalTo(5));
    }

}