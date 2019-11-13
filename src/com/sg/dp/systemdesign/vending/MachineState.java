package com.sg.dp.systemdesign.vending;

public interface MachineState {
    void dispense(String product);
    void insertQuarter();
    void insertDollar();
    void reset();
}
