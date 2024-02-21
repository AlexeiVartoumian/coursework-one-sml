package sml.instructions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import sml.Instruction;
import sml.Machine;
import sml.Registers;

public class BnzInstructionTest {
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
        i = new BnzInstruction("lbl", 2, "2");
        i.execute(m);
        Assertions.assertEquals(6, m.registers().register(2));
    }
    @Test
    void executeValidTwo() {
        regs.register(2, -5);
        regs.register(3, 6);
        i = new BnzInstruction("lbl", 2,"3");
        i.execute(m);
        Assertions.assertEquals(6, m.registers().register(3));
    }
}