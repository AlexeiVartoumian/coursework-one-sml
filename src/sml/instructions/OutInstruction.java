package sml.instructions;

import sml.Instruction;
import sml.Machine;

/**
 * same format as load Instruction
 *I only need one register
 *
 * //TODO:Test case to consider that nothing is in fact stored in the register
 *
 */
public final class OutInstruction extends Instruction {

    private String label;

    private final int register;

    public OutInstruction(final String label,int register ){

        super(label, "Out");
        this.register = register;
    }

    /**
     * I dont like the way im hadnling this. quick and dirty way to print the contents of the register.
     * @param m  of type Machine.
     *           added a getter method in Register class to access the index of Array Registers.
     *           havent handled outof bounds indexes .
     */
    public void execute(Machine m){

        int registerval = m.registers().getRegisterValue(register);
        String result = toString() + ": " + registerval;
        System.out.println(result);
    }

    @Override
    public String toString(){
        return STR."\{STR."\{super.toString()} Value contained inside register " + register}  ";

    }

}
