package sml;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.function.BiFunction;

/**
 * This class represents an abstract instruction for the <code>SML</code> language
 *
 * @author KLM
 *
 * additional notes: Completely Superflous but added a static lambda function for the sake of practise.
 *
 */
@Data
@AllArgsConstructor
@Accessors(fluent = true)
public abstract class Instruction {
    private String label;
    private String opcode;

    public abstract void execute(Machine m);

    /**
     *<p>
     *
     *     Intended for Operator Instructions Add, Subtract, Multiply ,Divide.
     *     Now instead of three lines a one liner can be written.

     *     var value1 = m.registers().register(register1);
     *     var value2 = m.registers().register(register2);
     *     m.registers().register(result, value1 + value2);

     *     Will now be this:
     *     Instruction.Operator(m , result , register1,register2, Integer::sum);
     *</p>
     *
     * @param m -> m of type machine where the Function shall produce a side effect on
     * @param result -> the register that will store the value
     * @param val1 -> operand of type integer
     * @param val2 -> operand of type integer
     * @param function -> A binary function helper method for Instructions that share the similar behaviour of
     * performing operations with two operands.
     *
     *
     */
    protected static void Operator(Machine m , int result,int val1 , int val2, BiFunction<Integer,Integer,Integer>function){
        var value1  = m.registers().register(val1);
        var value2 = m.registers().register(val2);
        m.registers().register( result , function.apply(value1,value2) );
    }
}