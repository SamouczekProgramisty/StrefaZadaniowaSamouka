package pl.samouczekprogramisty.szs.cyclic;

import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static pl.samouczekprogramisty.szs.cyclic.CyclicNumberFinder.isCyclic;

public class CyclicNumberFinderTest {

    @Test
    public void shouldTellThat142857IsCyclic() {
        assertThat(isCyclic("142857"), is(true));
    }

    @Test
    public void shouldTellThat142856IsntCyclic() {
        assertThat(isCyclic("142856"), is(false));
    }

    @Test
    public void shouldTellThat076923IsntCyclic() {
        assertThat(isCyclic("076923"), is(false));
    }

    @Test
    public void shouldTellThat16DigitNumberIsCyclic() {
        assertThat(isCyclic("0588235294117647"), is(true));
    }

    @Test
    public void shouldTellThat18DigitNumberIsCyclic() {
        assertThat(isCyclic("052631578947368421"), is(true));
    }

    @Test
    public void shouldTellThat22DigitNumberIsCyclic() {
        assertThat(isCyclic("0434782608695652173913"), is(true));
    }

    @Test
    public void shouldTellThat28DigitNumberIsCyclic() {
        assertThat(isCyclic("0344827586206896551724137931"), is(true));
    }

    @Test
    public void shouldTellThat46DigitNumberIsCyclic() {
        assertThat(isCyclic("0212765957446808510638297872340425531914893617"), is(true));
    }

    @Test
    public void shouldTellThat58DigitNumberIsCyclic() {
        assertThat(isCyclic("0169491525423728813559322033898305084745762711864406779661"), is(true));
    }

    @Test
    public void shouldTellThat60DigitNumberIsCyclic() {
        assertThat(isCyclic("016393442622950819672131147540983606557377049180327868852459"), is(true));
    }

    @Test
    public void shouldTellThat96DigitNumberIsCyclic() {
        assertThat(isCyclic("010309278350515463917525773195876288659793814432989690721649484536082474226804123711340206185567"), is(true));
    }

    @Test
    public void shouldTellThat96DigitNumberIsntCyclic() {
        assertThat(isCyclic("110309278350515463917525773195876288659793814432989690721649484536082474226804123711340206185567"), is(false));
    }

}