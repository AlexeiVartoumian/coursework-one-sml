package sml.instructions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import sml.Instruction;
import sml.Machine;
import sml.Registers;

public class OutInstructionTest {
    private Machine m;
    private Instruction i;
    private Registers regs;

    @BeforeEach
    void setUp() {
        m = new Machine();
        m.registers(new Registers());
        regs = m.registers();
        //...
    }
    @AfterEach
    void tearDown() {
        m = null;
        i = null;
        regs = null;
    }
    @Test
    void executeValid() {
        regs.register(2, 6);
        regs.register(3, 5);
        i = new OutInstruction("lbl", 3);
        i.execute(m);
        Assertions.assertEquals(5, m.registers().register(3));
    }
    @Test
    void executeValidTwo() {
        regs.register(2, -5);
        regs.register(3, 6);
        i = new OutInstruction("lbl", 2);
        i.execute(m);
        Assertions.assertEquals(-5, m.registers().register(2));
    }
}
