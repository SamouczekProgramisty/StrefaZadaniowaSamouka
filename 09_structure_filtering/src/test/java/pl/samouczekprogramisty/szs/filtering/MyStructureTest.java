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
    void shouldReturnNullWhenNotFoundByCodeAndStructureIsEmpty() {
        assertThat(structure.findByCode("xxx"), is(nullValue()));
    }

    @Test
    void shouldReturnNullWhenNotFoundByRendererAndStructureIsEmpty() {
        assertThat(structure.findByRenderer("xxx"), is(nullValue()));
    }

    @Test
    void shouldReturn0WhenStructureIsEmpty() {
        assertThat(structure.count(), is(equalTo(0)));
    }

    @Test
    void shouldBeAbleToAddNodesToStructure() {
        structure.addNode(new Node("code1", "renderer1"));
        assertThat(structure.count(), is(1));
    }

    @Test
    void shouldReturnNullWhenNotFoundByCode() {
        structure.addNode(new Node("code1", "renderer1"));
        assertThat(structure.findByCode("code2"), is(nullValue()));
    }

    @Test
    void shouldReturnNullWhenNotFoundByRenderer() {
        structure.addNode(new Node("code1", "renderer1"));
        assertThat(structure.findByRenderer("renderer2"), is(nullValue()));
    }

}