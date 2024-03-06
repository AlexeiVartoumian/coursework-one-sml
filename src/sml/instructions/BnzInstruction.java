package sml.instructions;

import sml.Bridge;
import sml.Instruction;
import sml.Machine;





public final class BnzInstruction  extends Instruction{


    private final int register;

    /**
     * String parameter specific to BnzInstruction. destination of Instruction to point/jump to.
     */
    private String labelDestination;

    /**
     * @see sml.Bridge
     */
    private Bridge bridge = new Bridge();


    public BnzInstruction (String label , int register , String labelDestination){
        super(label , "bnz");
        this.register = register;
        this.labelDestination = labelDestination;
    }

    /**
     * Bnz Instruction is a jump instruction.
     * The file to be read which is assembly like language handles the logic.
     * This method only cares if the machine instance value is zero or not, the result of which
     * sets the program counter to String LabelDestination.
     *
     * @param m -> instance of machine that maintains context of program
     * @see sml.Bridge#setProgramCounter(Machine, String)
     * for additional information
     */
    @Override
    public void execute(Machine m){
        int registerval = m.registers().getRegisterValue(register);

        try{
            if(registerval != 0){

                bridge.setProgramCounter(m, labelDestination);
            }
        }catch(Exception e){
            e.printStackTrace() ;
        }

    }



}
