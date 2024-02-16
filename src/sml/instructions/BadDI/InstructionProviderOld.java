package sml.instructions.BadDI;

import sml.Instruction;

import java.util.List;

/**
 * Interface InstructionProvider accepts variable amount of String arguments.
 * This is to represent the idea the decoding of the Instruction set according to a particular
 * architecture and translating a Given Instruction to meet the Instruction architecture.
 * I.e maybe we want to parallelize given operands.
 */
public interface InstructionProviderOld {

    //Instruction deliverInstruction(String... args);

    Instruction deliverInstruction(List<String> args);

}
