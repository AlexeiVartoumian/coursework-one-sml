package sml;

import java.awt.*;
import java.util.ArrayList;

public class Bridge {

    private Labels labels;
    protected Machine machine;
    public Bridge(){

    }

    public void setProgramCounter(Machine machine,  String labelDestination ){
        this.machine = machine;
        this.labels = this.machine.labels();
        int index = labels.indexOf(labelDestination);
        machine.pc(index);
    }
}
