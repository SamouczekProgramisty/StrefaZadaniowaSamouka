package pl.samouczekprogramisty.szs.aoc2016.day21;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PasswordScramblerTest {

    private PasswordScrambler scrambler;

    @BeforeEach    public void setUp() {
        scrambler = new PasswordScrambler("abcde");
    }

    @Test
    public void shouldSwapIndices() {
        assertEquals("ebcda", scrambler.swapIndices(4, 0).getPassword());
    }

    @Test
    public void shouldSwapLetters() {
        assertEquals("adcbe", scrambler.swapLetters('d', 'b').getPassword());
    }

    @Test
    public void shouldReverse() {
        assertEquals("adcbe", scrambler.reverse(1, 3).getPassword());
    }

    @Test
    public void shouldRotateLeft() {
        assertEquals("bcdea", scrambler.rotateLeft(1).getPassword());
    }

    @Test
    public void shouldRotateLeftByMultipleCharacters() {
        assertEquals("deabc", scrambler.rotateLeft(3).getPassword());
    }

    @Test
    public void shouldRotateRight() {
        assertEquals("eabcd", scrambler.rotateRight(1).getPassword());
    }

    @Test
    public void shouldRotateRightByMultipleCharacters() {
        assertEquals("cdeab", scrambler.rotateRight(3).getPassword());
    }

    @Test
    public void shouldMoveLetter() {
        assertEquals("acdeb", scrambler.move(1, 4).getPassword());
    }

    @Test
    public void shouldMoveLetterBack() {
        assertEquals("aebcd", scrambler.move(4, 1).getPassword());
    }

    @Test
    public void shouldRotate() {
        assertEquals("deabc", scrambler.rotateByLetter('b').getPassword());
    }

    @Test
    public void shouldRotateByMoreThan2Characters() {
        assertEquals("eabcd", scrambler.rotateByLetter('e').getPassword());
    }

    @Test
    public void shouldSolveGivenExample() {
        String password = scrambler
                .swapIndices(4, 0)
                .swapLetters('d', 'b')
                .reverse(0, 4)
                .rotateLeft(1)
                .move(1, 4)
                .move(3, 0)
                .rotateByLetter('b')
                .rotateByLetter('d')
                .getPassword();
        assertEquals("decab", password);
    }
}
