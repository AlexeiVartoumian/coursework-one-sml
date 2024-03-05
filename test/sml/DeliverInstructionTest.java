package sml;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Constructor;
import java.util.Properties;

import static org.junit.jupiter.api.Assertions.*;

class DeliverInstructionTest {
    private DeliverInstruction deliverInstruction;

    private String sampleInstructionPath;

    private String label;

    private Class<?> clazz1 ;


    private String instructionInfo = "10 10 10";


    @BeforeEach
    void setUp() throws ClassNotFoundException{
        deliverInstruction = new DeliverInstruction();
        sampleInstructionPath = "sml.instructions.AddInstruction";
        label = "f0 ";
        deliverInstruction.setLine("10 10 10");
        clazz1 = Class.forName(sampleInstructionPath);


    }
    @Test
    void testConstructor1() throws NoSuchMethodException {
        Constructor<?>[] constructor1 = clazz1.getDeclaredConstructors();

        Constructor<?> value = null;
        for (Constructor<?> res : constructor1) {
            res.setAccessible(true);
            value = res;
        }
        assertEquals(value, deliverInstruction.findConstructor(clazz1));
    }

    @Test
    void testArgsForConstructor() throws NoSuchMethodException {

        Object[] parameters = new Object[4];
        parameters[0] = label;

        parameters[1] = Integer.parseInt(instructionInfo.substring(0,2));

        parameters[2] = Integer.parseInt(instructionInfo.substring(3,5));
        parameters[3] = Integer.parseInt(instructionInfo.substring(instructionInfo.length()-2));
        Constructor<?> constructor = deliverInstruction.findConstructor(clazz1);

        assertArrayEquals(parameters,deliverInstruction.argsForConstructor(constructor, label, instructionInfo));

    }


}