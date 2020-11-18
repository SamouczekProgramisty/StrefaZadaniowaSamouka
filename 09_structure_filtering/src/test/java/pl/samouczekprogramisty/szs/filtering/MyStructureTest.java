package pl.samouczekprogramisty.szs.filtering;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.assertThrows;

class MyStructureTest {

    private static final Node NODE1 = new Node("code1", "renderer1");
    private static final Node NODE2 = new Node("code2", "renderer2");
    private static final CompositeNode COMPOSITE_NODE3 = new CompositeNode("code3", "renderer3");
    private static final Node NODE4 = new Node("code4", "renderer4");
    private static final CompositeNode COMPOSITE_NODE5 = new CompositeNode("code5", "renderer5");
    private static final Node NODE6 = new Node("code6", "renderer6");

    private MyStructure emptyStructure;
    private MyStructure filledStructure;

    @BeforeAll
    static void setUpClass() {
        COMPOSITE_NODE3.addNode(NODE4);
        COMPOSITE_NODE3.addNode(COMPOSITE_NODE5);

        COMPOSITE_NODE5.addNode(NODE6);
    }

    @BeforeEach
    void setUp() {
        emptyStructure = new MyStructure();

        filledStructure = new MyStructure(NODE1, NODE2, COMPOSITE_NODE3);
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
        assertThat(filledStructure.findByCode("code2"), is(NODE2));
    }

    @Test
    void shouldReturnNodeFoundByRenderer() {
        assertThat(filledStructure.findByRenderer("renderer2"), is(NODE2));
    }

    @Test
    void shouldntAcceptNullAsCode() {
        assertThrows(IllegalArgumentException.class, () -> emptyStructure.findByCode(null));
    }

    @Test
    void shouldntAcceptNullAsRenderer() {
        assertThrows(IllegalArgumentException.class, () -> emptyStructure.findByRenderer(null));
    }

    @Test
    void shouldFindCompositeNodeByCode() {
        assertThat(filledStructure.findByCode("code3"), is(COMPOSITE_NODE3));
    }

    @Test
    void shouldFindCompositeNodeByReferer() {
        assertThat(filledStructure.findByRenderer("renderer3"), is(COMPOSITE_NODE3));
    }

    @Test
    void shouldBeAbleToFindNestedNodeByCode() {
        assertThat(filledStructure.findByCode("code4"), is(NODE4));
    }

    @Test
    void shouldBeAbleToFindNestedNodeByRenderer() {
        assertThat(filledStructure.findByRenderer("renderer4"), is(NODE4));
    }

    @Test
    void shouldBeAbleToFindMultipleNestedNodeByCode() {
        assertThat(filledStructure.findByCode("code6"), is(NODE6));
    }

    @Test
    void shouldBeAbleToFindMultipleNestedNodeByRenderer() {
        assertThat(filledStructure.findByRenderer("renderer6"), is(NODE6));
    }

    @Test
    void shouldHandleCountProperlyWithNestedStructure() {
        assertThat(filledStructure.count(), is(6));
    }

}