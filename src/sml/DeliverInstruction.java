package sml;

import java.lang.reflect.Constructor;

import java.util.Arrays;

/**
 * Utility class whose responsibility is to enable InstructionFactory to implement Dependency Injection.
 * It has three methods findConstructor and argsForCOnstructor are reflection methods to create Classes on the fly.
 * The scan method reads a String and partitions it returning the leftmost partition of the string.
 * @author alexv
 */
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
    @SuppressWarnings("SameReturnValue")
    public Object[] argsForConstructor(Constructor<?> cons, String label, String line) {

        // TODO
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
            Object argument =null;

            if (currentType == int.class){
                argument = Integer.parseInt(labelvals[i-1]);
            }else{
                argument = labelvals[i-1];
            }
            argsArray[i] = argument;
        }

        return argsArray;
    }
    public void setLine(String line){
        this.line = line;
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
