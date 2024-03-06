package sml;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import sml.instructions.AddInstruction;
import org.junit.jupiter.api.Test;


class InstructionFactoryTest {

    private Instruction i;


    @BeforeEach
    void setUp() {
        i = new AddInstruction("lbl", 1, 2, 3);
    }

    @Test
    void createInstruction() throws NoSuchMethodException {

        Assertions.assertEquals(i , InstructionFactory.createInstruction("lbl","add","1 2 3"));
    }
}