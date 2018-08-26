package pl.samouczekprogramisty.szs.filtering;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MyStructureTest {

    @Test
    void shouldBeAbleToInstantiateClass() {
        IMyStructure structure = new MyStructure();
        assertNotNull(structure);
    }

}