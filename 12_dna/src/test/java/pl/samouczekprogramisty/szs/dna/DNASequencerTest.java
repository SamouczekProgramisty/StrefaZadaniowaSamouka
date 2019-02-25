package pl.samouczekprogramisty.szs.dna;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Arrays;
import java.util.List;
import java.util.function.BiFunction;
import java.util.stream.Stream;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

class DNASequencerTest {

    private static final List<BiFunction<String, String, Boolean>> ALGORITHMS = Arrays.asList(
            DNASequencer::naiveChangePossible,
            DNASequencer::slightlyBetterChangePossible,
            DNASequencer::optimalChangePossible,
            DNASequencer::changePossible
    );

    private static final List<BiFunction<String, String, Boolean>> NON_TRIVAL = Arrays.asList(
            DNASequencer::slightlyBetterChangePossible,
            DNASequencer::optimalChangePossible,
            DNASequencer::changePossible
    );

    private static Stream<Arguments> VALID_SEQUENCES_SMALL() {
        return Stream.of(
                Arguments.of("ABC", "BCA"),
                Arguments.of("AABBCC", "BCBACA"),
                Arguments.of("AABBCCDEFGH", "BCBACADEFGH"),
                Arguments.of("A", "A")
        );
    }

    private static Stream<Arguments> INVALID_SEQUENCES() {
        return Stream.of(
                Arguments.of("ABC", "BC"),
                Arguments.of("AABBCC", "CBACA"),
                Arguments.of("A", "AC"),
                Arguments.of("A", "C")
        );
    }

    private static Stream<Arguments> VALID_SEQUENCES_BIGGER() {
        return Stream.of(
                Arguments.of("ABCDEFGHIJKLMNOPQRST", "TSRQPONMLKJIHGFEDCBA"),
                Arguments.of("AABBCCDDEEFFGGHHIIJJKKLLMMNNOOPPQQRRSSTT", "TTSSRRQQPPOONNMMLLKKJJIIHHGGFFEEDDCCBBAA"),
                Arguments.of("TSRQPONMLKJIHGFEDCBA", "DOSQPKNMTFERCBAJIHGL")
        );
    }

    @ParameterizedTest
    @MethodSource("VALID_SEQUENCES_SMALL")
    void shouldBeAbleToProduceOneSequenceFromTheOther(String sequence1, String sequence2) {
        for (BiFunction<String, String, Boolean> algorighm : ALGORITHMS) {
            assertThat(algorighm.apply(sequence1, sequence2), is(true));
        }
    }

    @ParameterizedTest
    @MethodSource("VALID_SEQUENCES_BIGGER")
    void shouldBeAbleToProduceLargeSequenceFromTheOther(String sequence1, String sequence2) {
        for (BiFunction<String, String, Boolean> algorighm : NON_TRIVAL) {
            assertThat(algorighm.apply(sequence1, sequence2), is(true));
        }
    }

    @ParameterizedTest
    @MethodSource("INVALID_SEQUENCES")
    void shouldntBeAbleToProduceOneSequenceFromTheOther(String sequence1, String sequence2) {
        for (BiFunction<String, String, Boolean> algorighm : ALGORITHMS) {
            assertThat(algorighm.apply(sequence1, sequence2), is(false));
        }
    }
}