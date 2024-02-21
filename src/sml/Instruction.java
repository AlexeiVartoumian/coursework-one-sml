package sml;

import jdk.dynalink.Operation;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.UnaryOperator;

/**
 * This class represents an abstract instruction for the <code>SML</code> language
 *
 * @author KLM
 *
 * additional notes: Class instruction serves the purpose of a general template for which all instructions obey.
 *
 * All instructions share this behaviour:
 * - Label Intruction Identifier to which it can be indentified
 * - Op-Code: the operation to perform.
 * - execute: not sure yet. it takes Machine as a parameter. gotta figure out the behaviour of machine
 */
@Data
@AllArgsConstructor
@Accessors(fluent = true)
public abstract class Instruction {
    private String label;
    private String opcode;

    public abstract void execute(Machine m);

    /**
     *
     * @param m -> m of type machine where the Function shall produce a side effect on
     * @param result -> the register that will store the value
     * @param val1 -> operand of type integer
     * @param val2 -> operand of type integer
     * @param function -> An optional binary function helper method for Instructions that share the similar behaviour of
     * performing operations with two operands.
     */
    public static void Operator(Machine m , int result,int val1 , int val2, BiFunction<Integer,Integer,Integer>function){
        var value1  = m.registers().register(val1);
        var value2 = m.registers().register(val2);
        m.registers().register( result , function.apply(value1,value2) );
    }
}