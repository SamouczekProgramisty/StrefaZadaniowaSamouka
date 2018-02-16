package pl.samouczekprogramisty.szs.cyclic;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static pl.samouczekprogramisty.szs.cyclic.CyclicNumberFinder.isCyclicNaive;
import static pl.samouczekprogramisty.szs.cyclic.CyclicNumberFinder.isCyclicGeneration;


@RunWith(Parameterized.class)
public class CyclicNumberFinderTest {

    private static class TestData {
        final String number;
        final boolean isCyclic;

        public TestData(String number, boolean isCyclic) {
            this.number = number;
            this.isCyclic = isCyclic;
        }
    }

    @Parameterized.Parameters
    public static List<TestData> data() {
        return Arrays.asList(
            new TestData("333", false),
            new TestData("142857", true),
            new TestData("142856", false),
            new TestData("076923", false),
            new TestData("0588235294117647", true),
            new TestData("052631578947368421", true),
            new TestData("0434782608695652173913", true),
            new TestData("0344827586206896551724137931", true),
            new TestData("0212765957446808510638297872340425531914893617", true),
            new TestData("0169491525423728813559322033898305084745762711864406779661", true),
            new TestData("016393442622950819672131147540983606557377049180327868852459", true),
            new TestData("010309278350515463917525773195876288659793814432989690721649484536082474226804123711340206185567", true)
        );
    }

    private final String number;

    private final boolean isCyclic;

    public CyclicNumberFinderTest(TestData data) {
        this.number = data.number;
        this.isCyclic = data.isCyclic;
    }

    @Test
    public void shouldTestIsCyclicNaive() {
        assertThat(isCyclicNaive(number), is(isCyclic));
    }
    @Test
    public void shouldTestIsCyclicGeneration() {
        assertThat(isCyclicGeneration(number), is(isCyclic));
    }

}