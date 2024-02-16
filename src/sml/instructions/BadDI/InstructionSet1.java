package sml.instructions.BadDI;

import sml.Instruction;
import sml.instructions.*;

import java.util.List;


/**
 * This class responsibility extracts the getInstruction methods and implements it here.
 */
public class InstructionSet1 implements InstructionProviderOld {
    private String line = "";
    public InstructionSet1(String line){
        this.line = line;
    }

    @Override
    public Instruction deliverInstruction(List<String> args){
        int s1;
        int s2;
        int r;
        String lbl;
        final String label = args.getFirst();
        if(line.isEmpty()){
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
        return null;
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
    public int scanInt(){
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
}
