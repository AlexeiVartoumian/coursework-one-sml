package sml;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Locale;
import java.util.Properties;

/**
 * An Object Factory the responsibility of which is to create Class Instruction with
 *  dependency Injection done through primary method CreateInstruction.
 *
 * This class uses an abstract factory patter along with the Singleton Pattern
 * to ensure only one Instance of Instruction Factory can create instructions.
 * @author Alexei vartoumian
 */

public class InstructionFactory {

    //once created InstructionFactory will not be moidified
    private static final InstructionFactory instance = new InstructionFactory();

    private static DeliverInstruction server = new DeliverInstruction();
    private InstructionFactory(){

    }

    /**
     * Method CreateInstruciton uses dependencyInjection to read the String name of a Class from the beans.properties file.
     * This is done to get the concrete implementation of an Instruction and not depend on the code.
     *
     * @param label Instruction id , needed for bnz instruction for example
     * @param opCode - the type of instruction to perform
     * @param line - the operands on which qiven Opcode requires
     * @return Instruction
     * @throws NoSuchMethodException
     */
    public static Instruction createInstruction(final String label, String opCode, String line) throws NoSuchMethodException {


        server.setLine(line);
        String pkg = "sml.instructions";
        String base = "Instruction";

        String fqcn = pkg + "." + opCode.substring(0, 1).toUpperCase(Locale.ROOT) + opCode.substring(1) + base;

        Properties props = new Properties();
        //String filePath = Paths.get("../../bean.properties").toString();
        //String filePath = "C:\\Users\\alexv\\IdeaProjects\\coursework-one-sml-AlexeiVartoumian\\beans.properties";
        String filePath2 = "beans.properties";
            try (var resource = new FileInputStream(filePath2)) {

                props.load(resource);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            //props.forEach( (i,x) -> System.out.println(i  + " " +  x  + "     "  +  fqcn + "   " + opCode.substring(0, 1).toUpperCase(Locale.ROOT) + opCode.substring(1) + base));

            String key= opCode.substring(0, 1).toUpperCase(Locale.ROOT) + opCode.substring(1) + base+".class";
            String providerClass = props.getProperty(key);

            Class<?> clazz;
            try {
                clazz = Class.forName(providerClass);
            } catch (ClassNotFoundException e) {
                System.err.println(STR."Unknown instruction: \{fqcn}");
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
    public static InstructionFactory getInstance(){
           return instance;
    }
}
