package com.sg.dp.systemdesign.vending;

import static com.sg.dp.log.Logger.stdout;

public class VendingMachineImpl extends VendingMachine {
    public void dispense(String product) {
        state.dispense(product);
        moneyInserted.reset();
    }

    public void insertQuater() {
        state.insertQuarter();
    }

    public void insertDollaer() {
        state.insertDollar();
    }

    public void reset() {
        state.reset();
    }

    public static  void printCurrentProductCounts() {
        VendingMachineImpl.getProductCounts().entrySet().stream().forEach(entry -> stdout(entry.getKey() + ":" + entry.getValue()));
    }
}
