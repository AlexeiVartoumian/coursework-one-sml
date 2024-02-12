package sml.instructions;

import sml.Instruction;
import sml.Machine;

/**
 * Branch Instruction bnz: for --> label L1 Terminology below
 * L1 bnz s1 L2	If the contents of register s1 is not zero, then make the statement labeled L2 the next statement to execute
 *
 * we need to consider the case where the instruction to fect does not exist or the next instruction
 * does in fact do something wierd.
 * As such the way im approaching this problem at the first pass is to set the program counter to the LABEL index using
 * the method indexOf belonging to label class to rol the prgram counter back.
 *
 * As such I need:
 * register
 * label --> label orgin
 * label2 --> LABEL DESTINATION
 */
public final class BnzInstruction  extends Instruction{

    private String label;
    private final int register;
    private String labelDestination;

    public BnzInstruction (String label , int register , String labelDestination){
        super(label , "bnz");
        this.register = register;
        this.labelDestination = labelDestination;
    }

    /**
     * approach is as such if register value (implemented a get method for the register value for the Out/print instruction)
     * is not zero then use the setter method for prgcounter on machine
     * setting it to the return value of the int stream on Label class
     * specifically label destination. other wise do nothing.
     */
    @Override
    public void execute(Machine m){
        int registerval = m.registers().getRegisterValue(register);

        try{
            if(registerval != 0){
                m.setProgCounter(m.getLabelIndex(labelDestination));
            }
        }catch(Exception e){
            e.printStackTrace();
        }


    }




}
