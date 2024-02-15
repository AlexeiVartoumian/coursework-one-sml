package sml;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

public class InstructionSet2 implements InstructionProvider {
    private String line;

    public InstructionSet2(String line){
        this.line = line;
    }
    @Override
    public Instruction deliverInstruction(List<String>arguments)  {
        String pkg = "sml.instructions";
        String base = "Instruction";
        String label = arguments.getFirst();
        String opCode = arguments.getLast();
        //final String label, String opCode
        // add -> sml.instructions.AddInstruction
        System.out.println( label+" " +opCode + " I fool you");
        String fqcn = pkg + "." + opCode.substring(0, 1).toUpperCase(Locale.ROOT) + opCode.substring(1) + base;
        System.out.println(label + " " + opCode);

        Class<?> clazz;
        try {
            clazz = Class.forName(fqcn);
        } catch (ClassNotFoundException e) {
            System.err.println(STR."Unknown instruction: \{fqcn}");
            return null;
        }

        // find the correct constructor
        Constructor<?> cons = null;
        try {
            cons = findConstructor(clazz);
        } catch (NoSuchMethodException e) {
            throw new RuntimeException(e);
        }
        //var objArray = argsForConstructor(null, label);
        var objArray = argsForConstructor(cons,label );
        try {
            return (Instruction) cons.newInstance(objArray);
        } catch (InvocationTargetException | InstantiationException | IllegalAccessException | NullPointerException e) {
            e.printStackTrace();
        }
        return null;
    }

    @SuppressWarnings("SameReturnValue")
    private Constructor<?> findConstructor(Class<?> cl) throws NoSuchMethodException{
        //Constructor<?> cons = null;
        System.out.println(cl);
        // TODO
        //return null;
        Constructor<?>[] cons = cl.getDeclaredConstructors();
        for (var res: cons){
            if (res != null){
                res.setAccessible(true);
                return res;
            }
        }
        //cons.setAccessible(true);
        return null;
    }

    @SuppressWarnings("SameReturnValue")
    private Object[] argsForConstructor(Constructor<?> cons, String label) {
        //Object[] argsArray = null;
        // TODO
        Object[] argsArray = new Object [cons.getParameterCount()];
        Class<?>[] ConstructorParams = cons.getParameterTypes();

        String[] labelvals = new String[ConstructorParams.length];
        int z = 0;
        while (!line.isEmpty()){
            labelvals[z] = scan();
            z++;
        }
        System.out.println(Arrays.toString(ConstructorParams));
        System.out.println(Arrays.toString(labelvals));
        argsArray[0] = label;
        for (int i = 1; i <ConstructorParams.length; i++) {
            System.out.println(ConstructorParams[i]);
            Class<?> currentType = ConstructorParams[i];
            Object argument =null;
            //currentType == int.class ? argument = Integer.parseInt(labelvals[i]) : labelvals[i];
            if (currentType == int.class){
                argument = Integer.parseInt(labelvals[i-1]);
            }else{
                argument = labelvals[i-1];
            }
            argsArray[i] = argument;
        }

        return argsArray;
    }
    public String scan(){
        line = line.trim();
        if(line.isEmpty()){
            return "";
        }
        int i = 0;
        while (i < line.length() && line.charAt(i) != ' ' && line.charAt(i) != '\t') {
            i = i + 1;
        }
        String word = line.substring(0,i);
        line = line.substring(i);
        return word;
    }
}
