package sml.instructions;

import sml.Instruction;

public interface InstructionProvider {

    Instruction deliverInstruction(String... args);
}
