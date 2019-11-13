package com.sg.dp.systemdesign.vending;

import static com.sg.dp.log.Logger.stdout;

public class MaintState implements MachineState {
    VendingMachine machine;

    public MaintState(VendingMachine machine) {
        this.machine = machine;
    }

    @Override
    public void dispense(String product) {
        stdout("Machine in Maintenance state");
    }

    @Override
    public void insertQuarter() {
        throw new IllegalStateException("Can't insert quarter in " + this);
    }

    @Override
    public void insertDollar() {
        throw new IllegalStateException("Can't insert dollar in " + this);
    }

    @Override
    public void reset() {
        stdout("Resetting to working state from MAINT");
        machine.setState(machine.getWorkingState());
        for (Products product : Products.values())
            machine.getProductCounts().put(product.toString(), 5);
    }

    @Override
    public String toString() {
        return VMStates.MAINT.toString();
    }
}
