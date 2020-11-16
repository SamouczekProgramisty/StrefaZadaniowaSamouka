package pl.samouczekprogramisty.szs.filtering;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

class NodeTest {

    private static final Node NODE1 = new Node("code1", "renderer1");
    private static final Node NODE2 = new Node("code2", "renderer2");

	@Test
	void shouldNotBeUniqueForTheSameNode() {
		assertFalse(NODE1.isUnique(NODE1));
	}
	
	@Test
	void shouldBeUniqueForDifferentNode() {
		assertTrue(NODE1.isUnique(NODE2));
	}
}
