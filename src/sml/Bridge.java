package sml;

/**
 * Utility Class.
 * Used as A wrapper to byPass access privileges for subclass instructions that live in a different package.
 * It has two Fields
 * 1.Labels:  keeps track of index of instructions implemented as a ArrayList
 * 2.Machine: which executes the program instructions.

 * Used --> Bnznstruction execute method needs to set the program counter of the machine instance passed in.
 * The problem is that machines instance of label is private.
 * This class will be a field in bnz instruction.
 * This means the BnzInstruction can now access the things it needs to apply its operations
 *
 * @author alexv
 */

public class Bridge {
    private Labels labels;
    protected Machine machine;
    public Bridge(){

    }
    /**
     * The whole point of this class is to access the machines.labels() arrayList. once the Index of an Instruction
     * is known a jump can be performed.
     *
     * @param machine context in which the program runs in
     * @param labelDestination String value to which the program counter of machine instance will be pointed to
     */
    public void setProgramCounter(Machine machine,  String labelDestination ){
        this.machine = machine;
        this.labels = this.machine.labels();
        int index = labels.indexOf(labelDestination);
        machine.pc(index);
    }
}
