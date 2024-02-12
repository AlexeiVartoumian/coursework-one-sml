package sml;

import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.util.Arrays;

/**
 * This class represents the registers of the SML machine.
 * <p>
 * An instance contains 32 registers and methods to access and change them
 * </p>
 *
 * @author KLM
 */
@ToString
@EqualsAndHashCode
@Accessors(fluent = true)
/**
 * Note To Self.
 * I chucked in a getRegisters method for the Print/OUT INstruction in a bif to get the sintruction to work.
 * Will need to think about if theres a better way to do this as Now Im ust handle index of instructions being out of boudns
 */
public final class Registers {
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private static final int NUMBER_OF_REGISTERS = 32;


    private final int[] registers;

    // Constructor: an instance whose registers are set to 0

    {
        registers = new int[NUMBER_OF_REGISTERS];
    }

    public int getRegisterValue(int registerIndex){
        try {
            return registers[registerIndex];
        }catch(ArrayIndexOutOfBoundsException e){
            System.out.println("Register :" + registerIndex + "Does not exist ");
            //e.printStackTrace();
        }
        return 0;
    }
    /**
     * Set up the registers for the SML machine.
     */
    public Registers() {
        // set all the register values to zero
        Arrays.fill(registers, 0);
    }

    // Set register i to v.
    // Precondition: 0 <= i <= NUMBER_OF_REGISTERS

    public void register(int i, int v) {
        assert 0 <= i && i < NUMBER_OF_REGISTERS : "Register index out of bounds";
        registers[i] = v;
    }

    public int register(int i) {
        assert 0 <= i && i < NUMBER_OF_REGISTERS : "Register index out of bounds";
        return registers[i];
    }
}