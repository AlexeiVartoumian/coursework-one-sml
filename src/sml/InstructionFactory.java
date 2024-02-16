package sml;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Locale;
import java.util.Properties;

public class InstructionFactory {

    private static final InstructionFactory instance = new InstructionFactory();

    private static DeliverInstruction server = new DeliverInstruction();
    private InstructionFactory(){

    }

    public static Instruction createInstruction(final String label, String opCode, String line) throws NoSuchMethodException {

        server.setLine(line);
        String pkg = "sml.instructions";
        String base = "Instruction";

        String fqcn = pkg + "." + opCode.substring(0, 1).toUpperCase(Locale.ROOT) + opCode.substring(1) + base;
        System.out.println(label + " " + opCode);
        Properties props = new Properties();
        //String filePath = Paths.get("../../bean.properties").toString();
        String filePath = "C:\\Users\\alexv\\IdeaProjects\\coursework-one-sml-AlexeiVartoumian\\beans.properties";
            try (var resource = new FileInputStream(filePath)) {
                props.load(resource);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            String providerClass = props.getProperty(fqcn);
            Class<?> clazz;
            try {
                clazz = Class.forName(fqcn);
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
