package sml.instructions;

import sml.Instruction;
import sml.Machine;

import java.util.function.BiFunction;

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
     * Execute method Prints current Instruction and the value inside its field register Belonging to Machine.
     *
     *  @param m -> instance of machine that maintains context of program
     *
     */
    public void execute(Machine m){

        int registerVal = m.registers().getRegisterValue(register);
        String result = STR."\{this}: \{registerVal}";
        System.out.println(result);
    }

    @Override
    public String toString(){
        return STR."\{STR."\{super.toString()} Value contained inside register " + register}  ";

    }

}
