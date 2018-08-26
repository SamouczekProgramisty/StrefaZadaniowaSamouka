package pl.samouczekprogramisty.szs.cyclic;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static pl.samouczekprogramisty.szs.cyclic.CyclicNumberFinder.isCyclicGeneration;
import static pl.samouczekprogramisty.szs.cyclic.CyclicNumberFinder.isCyclicNaive;


class CyclicNumberFinderTest {

    private static Stream<Arguments> data() {
        return Stream.of(
            Arguments.of("333", false),
            Arguments.of("142857", true),
            Arguments.of("142856", false),
            Arguments.of("076923", false),
            Arguments.of("0588235294117647", true),
            Arguments.of("052631578947368421", true),
            Arguments.of("0434782608695652173913", true),
            Arguments.of("0344827586206896551724137931", true),
            Arguments.of("0212765957446808510638297872340425531914893617", true),
            Arguments.of("0169491525423728813559322033898305084745762711864406779661", true),
            Arguments.of("016393442622950819672131147540983606557377049180327868852459", true),
            Arguments.of("010309278350515463917525773195876288659793814432989690721649484536082474226804123711340206185567", true)
        );
    }

    @ParameterizedTest
    @MethodSource("data")
    void shouldTestIsCyclicNaive(String number, Boolean isCyclic) {
        assertThat(isCyclicNaive(number), is(isCyclic));
    }

    @ParameterizedTest
    @MethodSource("data")
    void shouldTestIsCyclicGeneration(String number, Boolean isCyclic) {
        assertThat(isCyclicGeneration(number), is(isCyclic));
    }

}