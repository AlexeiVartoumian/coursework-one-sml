
package sml.instructions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import sml.Instruction;
import sml.Machine;
import sml.Registers;

public class LinInstructionTest {
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
//        regs.register(2, 6);
//        regs.register(3, 5);
        i = new LinInstruction("lbl", 1, 2);
        i.execute(m);
        Assertions.assertEquals(2, m.registers().register(1));
    }
    @Test
    void executeValidTwo() {
//        regs.register(2, -5);
//        regs.register(3, 6);
        i = new LinInstruction("lbl", 1, 6);
        i.execute(m);
        Assertions.assertEquals(6, m.registers().register(1));
    }
}
