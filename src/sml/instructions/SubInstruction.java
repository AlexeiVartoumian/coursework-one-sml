package sml.instructions;

import sml.Instruction;
import sml.Machine;

import java.util.function.BiFunction;

/**
 * Requires  two registers to operate and
 */
public final class SubInstruction extends Instruction {
    private String label;
    private final int result;

    private final int register1;
    private final int register2;
    public SubInstruction(String label, int i1,int i2, int i3){
        super(label , "sub");
        result = i1;
        register1 = i2;
        register2 = i3;
    }
    /**
     * @param m -> instance of machine that maintains context of program
     * uses static method on abstract Instruction class to perform its implementation.
     * @see sml.Instruction#Operator(Machine, int, int, int, BiFunction)
     * for additional information
     */
    @Override
    public void execute(Machine m ){
//        var value1 = m.registers().register(register1);
//        var value2 = m.registers().register(register2);
//
//        m.registers().register(result, value1 - value2);
        Instruction.Operator(m, result, register1,register2,(register1, register2) -> register1 - register2);
    }
    @Override
    public String toString(){
        return STR."\{STR."\{STR."\{super.toString()} store in register " + result} the contents of register "
                + register1} subtracted from the contents of register " + register2;
    }
}
