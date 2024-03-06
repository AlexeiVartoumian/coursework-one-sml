package sml;

import lombok.Getter;

import java.io.FileInputStream;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

import java.util.Properties;
import java.util.function.UnaryOperator;

/**
 * An Object Factory the responsibility of which is to create Class Instruction with
 *  dependency Injection done through primary method CreateInstruction.

 * This class uses an abstract factory patter along with the Singleton Pattern
 * to ensure only one Instance of Instruction Factory can create instructions.
 * @author Alexei vartoumian
 */

public class InstructionFactory {

    //once created InstructionFactory will not be modified
    @Getter
    private static final InstructionFactory instance = new InstructionFactory();

    /**
     * @see DeliverInstruction
     */
    private static DeliverInstruction server = new DeliverInstruction();
    private InstructionFactory(){

    }

    /**
     * Method CreateInstruction uses dependencyInjection to read the String name of a Class from the beans.properties file.
     * This is done to get the concrete implementation of an Instruction and not depend on hard-coded implementation of Instruction.
     *
     * @param label Instruction id , needed for bnz instruction for example
     * @param opCode - the type of instruction to perform
     * @param line - the operands on which qiven Opcode requires
     * @return Instruction
     *
     */
    public static Instruction createInstruction(final String label, String opCode, String line) throws NoSuchMethodException {

        server.setLine(line);

        UnaryOperator<String> toUpper  = word ->  {
                String capitalInstruction;
                capitalInstruction = word.substring(0,1).toUpperCase() + word.substring(1);
                return capitalInstruction;
        };

        String renderInstruction = toUpper.apply(opCode);

        Properties props = new Properties();

        String filePath2 = "beans.properties";
            try (var resource = new FileInputStream(filePath2)) {

                props.load(resource);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            String providerClass = props.getProperty(renderInstruction);

            Class<?> clazz;
            try {
                clazz = Class.forName(providerClass);
            } catch (ClassNotFoundException e) {
                System.err.println(STR."Unknown Instruction: \{opCode}");
                return null;
            }
            var cons = server.findConstructor(clazz);
            var objArray = server.argsForConstructor(cons, label, line);
            try {
                return (Instruction) cons.newInstance(objArray);
            } catch (InvocationTargetException | InstantiationException | IllegalAccessException |
                     NullPointerException e) {
                e.printStackTrace();
            }
            return null;

    }
}
