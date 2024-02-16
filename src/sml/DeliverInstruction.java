package sml;

import java.lang.reflect.Constructor;

import java.util.Arrays;


public class DeliverInstruction {
    private  String line;


    @SuppressWarnings("SameReturnValue")
    public Constructor<?> findConstructor(Class<?> cl) throws NoSuchMethodException{
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
        return null;
    }
    @SuppressWarnings("SameReturnValue")
    public Object[] argsForConstructor(Constructor<?> cons, String label, String line) {
        //Object[] argsArray = null;
        // TODO
        Object[] argsArray = new Object [cons.getParameterCount()];
        Class<?>[] ConstructorParams = cons.getParameterTypes();

        String[] labelvals = new String[5];
        int z = 0;
        boolean flag = true;
        while (flag ){
            labelvals[z] = scan();
            if (labelvals[z].isEmpty()){
                flag = false;
            }
            z++;
        }
//        System.out.println(Arrays.toString(ConstructorParams));
//        System.out.println(Arrays.toString(labelvals));
        argsArray[0] = label;
        for (int i = 1; i <ConstructorParams.length; i++) {

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
        System.out.println(Arrays.toString(argsArray) + "    hahaa");
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
