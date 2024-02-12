package sml;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.Accessors;

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

}