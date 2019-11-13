package com.sg.dp.systemdesign.vending;

import static com.sg.dp.log.Logger.stdout;

public class SoldoutState implements MachineState {
    VendingMachine machine;

    public SoldoutState(VendingMachine machine) {
        this.machine = machine;
    }

    @Override
    public void dispense(String product) {
        throw new IllegalStateException("Can't dispense in sold out state");
    }

    @Override
    public void insertQuarter() {
        throw new IllegalStateException("Can't insert quater in sold out state");
    }

    @Override
    public void insertDollar() {
        throw new IllegalStateException("Can't insert dollar in sold out state");
    }

    @Override
    public void reset() {
        stdout("Resetting the machine to working state...");
        machine.setState(machine.getWorkingState());
        for (Products product : Products.values())
            machine.getProductCounts().put(product.toString(), 5);
    }

    @Override
    public String toString() {
        return VMStates.SOLDOUT.toString();
    }
}
