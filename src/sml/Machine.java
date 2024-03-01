package sml;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents the machine, the context in which programs run.
 * <p>
 * An instance contains 32 registers and methods to access and change them.
 *
 * @author KLM
 */

@Accessors(fluent = true)
@Data
@NoArgsConstructor
public final class Machine {

    // The labels in the SML program, in the order in
    // which they appear (are defined) in the program
    private Labels labels;

    // The SML program, consisting of prog.size() instructions,
    // each of class Instruction (or one of its subclasses)
    private List<Instruction> prog;

    // The registers of the SML machine

    private Registers registers;

    // The program counter; it contains the index (in prog)
    // of the next instruction to be executed.
    private int pc;

    {
        labels = new Labels();
        prog = new ArrayList<>();
        pc = 0;
    }

    /**
     * String representation of the program under execution.
     *
     * @return pretty formatted version of the code.
     */
    @Override
    public String toString() {
        var s = new StringBuilder();
        for (int i = 0; i != prog().size(); i++) {
            s.append(prog().get(i)).append("\n");
        }
        return s.toString();
    }

    /**
     * Execute the program in prog, beginning at instruction 0. Precondition: the
     * program and its labels have been store properly.
     */
    public void execute() {
        pc(0);
        registers(new Registers());
        while (pc() < prog().size()) {
            Instruction ins = prog().get(pc());
            pc(pc() + 1);
            ins.execute(this); // so convoluted
            System.out.format("executed: %s%n", ins);
        }
    }

    /**
     * Bridge Between Pattern. Because Class Machine lives in the same package as Labels as well as having an
     * instance of it and because the Labels method indexOf is public The machine class can use a effectively
     * use that method as a getter to set its own Program counter. This is necessary for the instructions in the sub folder
     * who take a parameter of type machine in order to execute thier respective operation.
     *
     * @param getLabelIndex --> @see getLabelIndex
     */
//    public void setProgCounter(int getLabelIndex){
//        this.pc = getLabelIndex;
//    }

    /**
     *
     * @param label --> indentifier of a given Instruction.
     * @return --> the index at which that Instruction occured in the MOdified ArrayList label class.
     */
//    public int getLabelIndex(String label){
//        return this.labels.indexOf(label);
//    }



}
