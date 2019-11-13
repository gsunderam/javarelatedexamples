package com.sg.dp.systemdesign.vending;

import static com.sg.dp.log.Logger.stderr;
import static com.sg.dp.log.Logger.stdout;

public class WorkingState  implements MachineState {
    VendingMachine machine;

    public WorkingState(VendingMachine machine) {
        this.machine = machine;
    }

    @Override
    public void dispense(String product) {
        checkIfAllSoldout();

        if (machine.moneyInserted.sum() == Products.getCharge(product)) {
            Integer count = VendingMachine.getProductCounts().get(product);
            if (count > 0) {
                stdout("dispensing product: " + product);
                machine.getProductCounts().put(product, count - 1);
            } else {
                stdout("The product " + product + " is sold out");
            }
        } else {
            stderr("Sorry: Please enter correct charge for the product");
        }
    }

    private boolean checkIfAllSoldout() {
        long count = VendingMachine.getProductCounts().values().parallelStream().filter(n -> n == 0).count();
        if (count == Products.values().length) {
            machine.setState(machine.getSoldoutState());
            stdout("Machine is in " + machine.getSoldoutState());
            return true;
        }

        return false;
    }

    @Override
    public void insertQuarter() {
        machine.moneyInserted.add(Money.QUARTER.getValue());
    }

    @Override
    public void insertDollar() {
        machine.moneyInserted.add(Money.DOLLAR.getValue());
    }

    @Override
    public void reset() {
        throw new IllegalStateException("No reset allowed when machine is in " + VMStates.WORKING);
    }

    @Override
    public String toString() {
        return VMStates.WORKING.toString();
    }
}
