package sml.instructions;

import sml.Instruction;
import sml.Machine;

import java.util.function.BiFunction;

/**
 *
 * TODO: handle divisions by zero. later down the line will have to consider negative operations
 */
public final class DivInstruction  extends Instruction {

    private String label;
    private final int result;
    private final int register1;
    private final int register2;

    public DivInstruction(String label ,int i1, int i2,int i3 ){
        super(label, "div");
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
    public void execute(Machine m){
//        var value1 = m.registers().register(register1);
//        var value2 = m.registers().register(register2);
//        m.registers().register(result, value1 / value2);
        Instruction.Operator(m, result, register1, register2, Integer::divideUnsigned);
    }
    @Override
    public String toString(){
        return STR."\{STR."\{STR."\{super.toString()} store in register " + result}  the contents of register"
        + register1} subtracted from the vontents of register" + register2;
    }
}
