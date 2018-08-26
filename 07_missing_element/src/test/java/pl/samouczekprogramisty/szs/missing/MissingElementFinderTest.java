package pl.samouczekprogramisty.szs.missing;

import org.hamcrest.Matcher;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static pl.samouczekprogramisty.szs.missing.MissingElementFinder.memoryGreedyFindMissing;
import static pl.samouczekprogramisty.szs.missing.MissingElementFinder.naiveFindMissing;
import static pl.samouczekprogramisty.szs.missing.MissingElementFinder.optimalFindMissing;

public class MissingElementFinderTest {

    @Test
    public void shouldFindMissingElementForEmptyArrayNaive() {
        assertThat(naiveFindMissing(), equalTo(0));
    }

    @Test
    public void shouldFindMissingElementForEmptyArrayMemmoryGreedy() {
        assertThat(memoryGreedyFindMissing(), equalTo(0));
    }

    @Test
    public void shouldFindMissingElementForEmptyArrayOptimal() {
        assertThat(optimalFindMissing(), equalTo(0));
    }

    @Test
    public void shouldFindMissingElementForOneElementArrayNaive() {
        assertThat(naiveFindMissing(0), equalTo(1));
        assertThat(naiveFindMissing(1), equalTo(0));
    }

    @Test
    public void shouldFindMissingElementForOneElementArrayMemmoryGreedy() {
        assertThat(memoryGreedyFindMissing(0), equalTo(1));
        assertThat(memoryGreedyFindMissing(1), equalTo(0));
    }

    @Test
    public void shouldFindMissingElementForOneElementArrayOptimal() {
        assertThat(optimalFindMissing(0), equalTo(1));
        assertThat(optimalFindMissing(1), equalTo(0));
    }

    @Test
    public void shouldFindMissingElementInSampleInputNaive() {
        assertThat(naiveFindMissing(0, 2, 1, 4), equalTo(3));
    }

    @Test
    public void shouldFindMissingElementInSampleInputMemmoryGreedy() {
        assertThat(memoryGreedyFindMissing(0, 2, 1, 4), equalTo(3));
    }

    @Test
    public void shouldFindMissingElementInSampleInputOptimal() {
        assertThat(optimalFindMissing(0, 2, 1, 4), equalTo(3));
    }

    @Test
    public void shouldFindMissingElementIfItsFirstNaive() {
        assertThat(naiveFindMissing(3, 2, 1, 4), equalTo(0));
    }

    @Test
    public void shouldFindMissingElementIfItsFirstMemmoryGreedy() {
        assertThat(memoryGreedyFindMissing(3, 2, 1, 4), equalTo(0));
    }

    @Test
    public void shouldFindMissingElementIfItsFirstOptimal() {
        assertThat(optimalFindMissing(3, 2, 1, 4), equalTo(0));
    }

    @Test
    public void shouldFindMissingElementIfItsLastNaive() {
        assertThat(naiveFindMissing(0, 3, 2, 1, 4), equalTo(5));
    }

    @Test
    public void shouldFindMissingElementIfItsLastMemmoryGreedy() {
        assertThat(memoryGreedyFindMissing(0, 3, 2, 1, 4), equalTo(5));
    }

    @Test
    public void shouldFindMissingElementIfItsLastOptimal() {
        assertThat(optimalFindMissing(0, 3, 2, 1, 4), equalTo(5));
    }

}