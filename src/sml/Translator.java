package sml;

import sml.instructions.*;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.nio.charset.StandardCharsets;
import java.util.*;

/**
 * This class ....
 * <p>
 * The translator of a <code>SML</code> program.
 *
 * @author ...
 */
public final class Translator {

    private static final String PATH = "";

    // word + line is the part of the current line that's not yet processed
    // word has no whitespace
    // If word and line are not empty, line begins with whitespace
    private final String fileName; // source file of SML code
    private String line = "";

    public Translator(final String file) {
        fileName = PATH + file;
    }

    // translate the small program in the file into lab (the labels) and
    // prog (the program)
    // return "no errors were detected"

    public boolean readAndTranslate(final Labels lab, final List<Instruction> prog) {
        try (var sc = new Scanner(new File(fileName), StandardCharsets.UTF_8)) {
            // Scanner attached to the file chosen by the user
            // The labels of the program being translated
            lab.reset();
            // The program to be created
            prog.clear();

            try {
                line = sc.nextLine();
            } catch (NoSuchElementException ioE) {
                return false;
            }

            // Each iteration processes line and reads the next input
            // line into "line"
            while (line != null) {
                // Store the label in label
                var label = scan();
                System.out.println(label+ "   ");
                if (label.length() > 0) {

                    //var ins = getInstruction(label);
                    var ins = returnInstruction(label , scan());
                    if (ins != null) {

                        lab.addLabel(label);
                        prog.add(ins);
                    }
                }

                try {
                    line = sc.nextLine();
                } catch (NoSuchElementException ioE) {
                    return false;
                }
            }
        } catch (IOException ioE) {
            System.err.println("File: IO error " + ioE);
            return false;
        } catch (NoSuchMethodException e) {
            throw new RuntimeException(e);
        }
        return true;
    }

    // The input line should consist of an SML instruction, with its label already removed.
    // Translate line into an instruction with label "label" and return the instruction.
    public Instruction getInstruction(final String label) {
        int s1; // Possible operands of the instruction
        int s2;
        int r;
        String lbl;

        if (line.isEmpty()) {
            return null;
        }
        var opCode = scan();
        switch (opCode) {
            case "add" -> {
                r = scanInt();
                s1 = scanInt();
                s2 = scanInt();
                return new AddInstruction(label, r, s1, s2);
            }
            case "lin" -> {
                r = scanInt();
                s1 = scanInt();
                return new LinInstruction(label, r, s1);
            }
            // TODO: You will have to write code here for the other instructions.
            case "sub" -> {
                r = scanInt();
                s1 = scanInt();
                s2 = scanInt();
                return new SubInstruction(label , r , s1, s2);
            }
            case "mul" -> {
                r = scanInt();
                s1 = scanInt();
                s2 = scanInt();
                return new MulInstruction(label , r , s1, s2);
            }
            case "out" -> {
                r = scanInt();
                s1 = scanInt();
                return new OutInstruction(label, r);
            }
            //TODO: Handle branch case to consider also the div case
            case "div" -> {
                r = scanInt();
                s1 = scanInt();
                s2 = scanInt();
                return new DivInstruction(label, r, s1, s2);
            }

            case "bnz"-> {
                r = scanInt();
                lbl = scan();

                return new BnzInstruction(label, r, lbl);
            }

            default -> System.out.println(STR."Unknown instruction: \{opCode}");
        }
        return null; // FIX THIS
    }

    /*
     * Return the first word of line and remove it from line. If there is no word,
     * return ""
     */
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

    // Return the first word of line as an integer. If there is any error, return
    // the maximum int
    private int scanInt() {
        String word = scan();
        if (word.isEmpty()) {
            return Integer.MAX_VALUE;
        }

        try {
            return Integer.parseInt(word);
        } catch (NumberFormatException e) {
            return Integer.MAX_VALUE;
        }
    }

    private Instruction returnInstruction(final String label, String opCode) throws NoSuchMethodException {
        String pkg = "sml.instructions";
        String base = "Instruction";

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
        var cons = findConstructor(clazz);
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

        String[] labelvals = new String[5];
        int z = 0;
        while (!line.isEmpty()){
            labelvals[z] = scan();
            z++;
        }
        System.out.println(Arrays.toString(ConstructorParams));
        System.out.println(Arrays.toString(labelvals));
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
}
