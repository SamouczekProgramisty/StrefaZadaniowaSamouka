package pl.samouczekprogramisty.szs.filtering;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

class MyStructureTest {

    private MyStructure structure;

    @BeforeEach
    void setUp() {
        structure = new MyStructure();
    }

    @Test
    void shouldBeAbleToInstantiateClass() {
        assertThat(structure, notNullValue());
    }

    @Test
    void shouldReturnNullWhenNotFoundByCode() {
        assertThat(structure.findByCode("xxx"), is(nullValue()));
    }

    @Test
    void shouldReturnNullWhenNotFoundByRenderer() {
        assertThat(structure.findByRenderer("xxx"), is(nullValue()));
    }

    @Test
    void shouldReturn0WhenStructureIsEmpty() {
        assertThat(structure.count(), is(equalTo(0)));
    }

}