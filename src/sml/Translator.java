package sml;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.Scanner;

/**
 * This class Reads from file String by String and turns them into Objects of type Instruction.
 * <p>
 * The translator of a <code>SML</code> program.
 *
 * @author alexv
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

    /**
     * Main method of Translator class. Turns All strings in the file into a sequence of Instructions
     *
     * @see InstructionFactory#createInstruction(String, String, String) for additional details
     * @param lab: arraylist where sequence of instructions is kept as a record
     * @param prog : arraylist where actual instructions to be executed are stored
     * @return boolean : false if error in file reading , else true.
     */

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
                if (label.length() > 0) {
                    var ins = InstructionFactory.createInstruction(label, scan(), line);
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
//    Old implementation as in get it to work. was first replaced with reflection which in turn was replaced/evolved to DI.
//    @return Concrete implementation of Instruction
//
//     The input line should consist of an SML instruction, with its label already removed.
//     Translate line into an instruction with label "label" and return the instruction.
//    public Instruction getInstruction(final String label) {
//        int s1; // Possible operands of the instruction
//        int s2;
//        int r;
//        String lbl;
//
//        if (line.isEmpty()) {
//            return null;
//        }
//        var opCode = scan();
//        switch (opCode) {
//            case "add" -> {
//                r = scanInt();
//                s1 = scanInt();
//                s2 = scanInt();
//                return new AddInstruction(label, r, s1, s2);
//            }
//            case "lin" -> {
//                r = scanInt();
//                s1 = scanInt();
//                return new LinInstruction(label, r, s1);
//            }
//
//            case "sub" -> {
//                r = scanInt();
//                s1 = scanInt();
//                s2 = scanInt();
//                return new SubInstruction(label , r , s1, s2);
//            }
//            case "mul" -> {
//                r = scanInt();
//                s1 = scanInt();
//                s2 = scanInt();
//                return new MulInstruction(label , r , s1, s2);
//            }
//            case "out" -> {
//                r = scanInt();
//                s1 = scanInt();
//                return new OutInstruction(label, r);
//            }
//            case "div" -> {
//                r = scanInt();
//                s1 = scanInt();
//                s2 = scanInt();
//                return new DivInstruction(label, r, s1, s2);
//            }
//
//            case "bnz"-> {
//                r = scanInt();
//                lbl = scan();
//
//                return new BnzInstruction(label, r, lbl);
//            }
//
//            default -> System.out.println(STR."Unknown instruction: \{opCode}");
//        }
//        return null; // FIX THIS
//    }

    /*
     * Return the first word of line and remove it from line. If there is no word,
     * return ""
     */


    // Return the first word of line as an integer. If there is any error, return
    // the maximum int
//    private int scanInt() {
//        String word = scan();
//        if (word.isEmpty()) {
//            return Integer.MAX_VALUE;
//        }
//
//        try {
//            return Integer.parseInt(word);
//        } catch (NumberFormatException e) {
//            return Integer.MAX_VALUE;
//        }
//    }


//     Old implementation that used reflection to create Instances of Instruction class at Runtime.

//    private Instruction returnInstruction(final String label, String opCode) throws NoSuchMethodException {
//        String pkg = "sml.instructions";
//        String base = "Instruction";
//
//        // add -> sml.instructions.AddInstruction
//        System.out.println( label+" " +opCode + " I fool you");
//        String fqcn = pkg + "." + opCode.substring(0, 1).toUpperCase(Locale.ROOT) + opCode.substring(1) + base;
//        System.out.println(label + " " + opCode);
//
//        Class<?> clazz;
//        try {
//            clazz = Class.forName(fqcn);
//        } catch (ClassNotFoundException e) {
//            System.err.println(STR."Unknown instruction: \{fqcn}");
//            return null;
//        }
//
//        // find the correct constructor
//        var cons = findConstructor(clazz);
//        //var objArray = argsForConstructor(null, label);
//        var objArray = argsForConstructor(cons,label );
//        try {
//            return (Instruction) cons.newInstance(objArray);
//        } catch (InvocationTargetException | InstantiationException | IllegalAccessException | NullPointerException e) {
//            e.printStackTrace();
//        }
//        return null;
//    }
//
     //Helper method for returnInstruction . created Constructor for a Classname.

    //   @SuppressWarnings("SameReturnValue")
//    private Constructor<?> findConstructor(Class<?> cl) throws NoSuchMethodException{
//        //Constructor<?> cons = null;
//        System.out.println(cl);
//
//        //return null;
//        Constructor<?>[] cons = cl.getDeclaredConstructors();
//        for (var res: cons){
//            if (res != null){
//                res.setAccessible(true);
//                return res;
//            }
//        }
//        //cons.setAccessible(true);
//        return null;
//    }

    //
     // Helper method for returnInstruction method. creates suitable params for a constructor.

    //@SuppressWarnings("SameReturnValue")
//    private Object[] argsForConstructor(Constructor<?> cons, String label) {
//        //Object[] argsArray = null;
//
//        Object[] argsArray = new Object [cons.getParameterCount()];
//        Class<?>[] ConstructorParams = cons.getParameterTypes();
//
//        String[] labelvals = new String[5];
//        int z = 0;
//        while (!line.isEmpty()){
//            labelvals[z] = scan();
//            z++;
//        }
//        System.out.println(Arrays.toString(ConstructorParams));
//        System.out.println(Arrays.toString(labelvals));
//        argsArray[0] = label;
//        for (int i = 1; i <ConstructorParams.length; i++) {
//
//            Class<?> currentType = ConstructorParams[i];
//            Object argument =null;
//                    //currentType == int.class ? argument = Integer.parseInt(labelvals[i]) : labelvals[i];
//            if (currentType == int.class){
//                argument = Integer.parseInt(labelvals[i-1]);
//            }else{
//                argument = labelvals[i-1];
//            }
//            argsArray[i] = argument;
//        }
//        System.out.println(Arrays.toString(argsArray) + "    hahaa");
//        return argsArray;
//    }

//    /**
//     * old stuff .  This was a helper method
//     * for when I wrongly thought use case of DI was to depend on a souped up method DeliverInstruction.
//     * I turned that method into a class with the argument that it Provided the InstructionSet architecture and therefore
//     * if we wanted to change the way All instructions are implemented then this should be changed.
//     * The current version is more granular where the DI is focussed on the specific instructions.
//     * @param label --> accepts the Identifier associated with each instruction which is used to generate
//     * @return a list of arguments with which to generate the appopriate instruction set
//     */
//    public List<String> arguments(String label){
//        String filePath = "beans.properties";
//        Properties props = new Properties();
//        int lastDigit = -1; // Default value in case of failure
//
//
//        try (FileInputStream input = new FileInputStream(filePath)) {
//            props.load(input);
//
//            String numberStr = props.getProperty("provider.class");
//            System.out.println(numberStr);
//            int number = Integer.parseInt(numberStr.substring(numberStr.length()-1));
//
//            lastDigit = number;
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        System.out.println(lastDigit);
//        switch(lastDigit){
//            case 1 -> {
//                return List.of(label) ;
//            }
//            case 2 ->{
//
//                return List.of(label , scan());
//            }
//        }
//           return null;
//
//    }
}
