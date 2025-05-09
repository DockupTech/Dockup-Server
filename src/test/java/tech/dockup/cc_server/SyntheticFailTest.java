package tech.dockup.cc_server;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.fail;

public class SyntheticFailTest {
    @Test
    void thisTestAlwaysFails() {
        fail("This is a synthetic failure for pipeline testing.");
    }
} 