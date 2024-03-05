package sml;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import sml.instructions.AddInstruction;
import sml.instructions.BnzInstruction;

import static org.junit.jupiter.api.Assertions.*;

class BridgeTest {

    private Machine m;

    private Bridge bridge;

    @BeforeEach
    void setUp() {
        m = new Machine();
        m.registers(new Registers());

        bridge = new Bridge();
        m.labels().addLabel("TEST");
        m.labels().addLabel("TEST2");
    }
    @AfterEach
    void tearDown() {
        m = null;

    }
    @Test
    void executeValid() {
        bridge.setProgramCounter(m, "TEST");
        Assertions.assertEquals(0, m.pc());
    }
    @Test
    void executeValidTwo() {
        bridge.setProgramCounter(m, "TEST2");
        Assertions.assertEquals(1, m.pc());

    }

}