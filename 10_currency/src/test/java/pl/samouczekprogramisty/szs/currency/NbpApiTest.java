package pl.samouczekprogramisty.szs.currency;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class NbpApiTest {

    @Test
    void shouldAllowToQueryNbpApi() {
        try(NbpApi api = new NbpApi()) {
            assertNotNull(api.request());
        }
    }

}