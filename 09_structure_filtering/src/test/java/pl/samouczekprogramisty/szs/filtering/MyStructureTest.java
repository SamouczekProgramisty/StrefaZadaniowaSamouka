package pl.samouczekprogramisty.szs.filtering;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.assertThrows;

class MyStructureTest {

    private static final Node NODE1 = new Node("code1", "renderer1");
    private static final Node NODE2 = new Node("code2", "renderer2");

    private MyStructure emptyStructure;
    private MyStructure filledStructure;

    @BeforeEach
    void setUp() {
        emptyStructure = new MyStructure();

        filledStructure = new MyStructure();
        filledStructure.addNode(NODE1);
        filledStructure.addNode(NODE2);
    }

    @Test
    void shouldBeAbleToInstantiateClass() {
        assertThat(emptyStructure, notNullValue());
    }

    @Test
    void shouldReturnNullWhenNotFoundByCodeAndStructureIsEmpty() {
        assertThat(emptyStructure.findByCode("xxx"), is(nullValue()));
    }

    @Test
    void shouldReturnNullWhenNotFoundByRendererAndStructureIsEmpty() {
        assertThat(emptyStructure.findByRenderer("xxx"), is(nullValue()));
    }

    @Test
    void shouldReturn0WhenStructureIsEmpty() {
        assertThat(emptyStructure.count(), is(equalTo(0)));
    }

    @Test
    void shouldReturnNullWhenNotFoundByCode() {
        assertThat(filledStructure.findByCode("missingCode"), is(nullValue()));
    }

    @Test
    void shouldReturnNullWhenNotFoundByRenderer() {
        assertThat(filledStructure.findByRenderer("missingRenderer"), is(nullValue()));
    }

    @Test
    void shouldReturnNodeFoundByCode() {
        assertThat(filledStructure.findByCode("code2"), is(sameInstance(NODE2)));
    }

    @Test
    void shouldReturnNodeFoundByRenderer() {
        assertThat(filledStructure.findByRenderer("renderer2"), is(sameInstance(NODE2)));
    }

    @Test
    void shouldntAcceptNullAsCode() {
        assertThrows(IllegalArgumentException.class, () -> emptyStructure.findByCode(null));
    }

    @Test
    void shouldntAcceptNullAsRenderer() {
        assertThrows(IllegalArgumentException.class, () -> emptyStructure.findByRenderer(null));
    }

}