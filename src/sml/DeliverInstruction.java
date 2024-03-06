package sml;

import lombok.Setter;

import java.lang.reflect.Constructor;


/**
 * Utility class whose responsibility is to enable InstructionFactory to implement Dependency Injection.
 * It has three methods.
 * First two findConstructor and argsForConstructor are reflection methods to create Classes.
 * The scan method reads a String and partitions it returning the leftmost partition of the string.
 * @author alexv
 */
@Setter
public class DeliverInstruction {
    private  String line;

    @SuppressWarnings("SameReturnValue")
    public Constructor<?> findConstructor(Class<?> cl) throws NoSuchMethodException{
        Constructor<?>[] cons = cl.getDeclaredConstructors();

        for (var res: cons){
            if (res != null){
                res.setAccessible(true);
                return res;
            }
        }
        return null;
    }

    /**
     * Generates the types Parameters needed to create the class.
     * @param cons class name of given Instruction.
     * @param label Instruction identifier
     * @param line used in Instruction Factory to create Instruction.
     * @return An array of Constructor type parameters
     */
    @SuppressWarnings("SameReturnValue")
    public Object[] argsForConstructor(Constructor<?> cons, String label, String line) {

        Object[] argsArray = new Object [cons.getParameterCount()];
        Class<?>[] ConstructorParams = cons.getParameterTypes();
        String[] labelvals = new String[cons.getParameterCount()];
        int z = 0;
        boolean flag = true;
        while (flag ){
            labelvals[z] = scan();
            if (labelvals[z].isEmpty()){
                flag = false;
            }
            z++;
        }
        argsArray[0] = label;
        for (int i = 1; i <ConstructorParams.length; i++) {

            Class<?> currentType = ConstructorParams[i];
            Object argument;

            if (currentType == int.class){
                argument = Integer.parseInt(labelvals[i-1]);
            }else{
                argument = labelvals[i-1];
            }
            argsArray[i] = argument;
        }
        return argsArray;
    }

    private String scan() {
        line = line.trim();
        if (line.isEmpty()) {
            return "";
        }
        int i = 0;
        while (i < line.length() && line.charAt(i) != ' ' && line.charAt(i) != '\t') {
            i = i + 1;
        }
        String word = line.substring(0, i);
        line = line.substring(i);
        return word;
    }

}
