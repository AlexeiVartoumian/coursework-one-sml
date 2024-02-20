package sml.instructions;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import sml.Instruction;
import sml.Machine;
import sml.Registers;

public class MulInstructionTest {
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
        regs.register(2, 4);
        regs.register(3, 5);
        i = new MulInstruction("lbl", 1, 2, 3);
        i.execute(m);
        Assertions.assertEquals(20, m.registers().register(1));
    }
    @Test
    void executeValidTwo() {
        regs.register(2, 8);
        regs.register(3, 8);
        i = new MulInstruction("lbl", 1, 2, 3);
        i.execute(m);
        Assertions.assertEquals(64, m.registers().register(1));
    }
}
