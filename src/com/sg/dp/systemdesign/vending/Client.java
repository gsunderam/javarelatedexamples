package com.sg.dp.systemdesign.vending;

import static com.sg.dp.log.Logger.print;
import static com.sg.dp.log.Logger.stderr;

public class Client {
    public static final String BISCUIT = "biscuit";
    public static final String PROTEINBAR = "PROTEINBAR";

    public static void main(String[] args) {
        VendingMachineImpl machine = new VendingMachineImpl();
        machine.dispense(BISCUIT); //No charge
        dispenseProduct(machine, BISCUIT);
        dispenseProduct(machine, BISCUIT);
        dispenseProduct(machine, BISCUIT); //sold out

        dispenseProduct(machine, PROTEINBAR);
        machine.dispense(PROTEINBAR); //No charge
        dispenseProduct(machine, PROTEINBAR);

        try {
            machine.reset();//can't reset in Working State
        } catch (Exception e) {
            stderr(e);
        }

        dispenseCoke(machine, "coke");
        dispenseCoke(machine, "coke");
        dispenseCoke(machine, "coke");

        dispenseProduct(machine, BISCUIT);
        VendingMachineImpl.printCurrentProductCounts();
        print("\n");

        //reset from soldout state
        machine.reset();
        VendingMachineImpl.printCurrentProductCounts();
        dispenseCoke(machine, "coke");

        //Put machine in maint mode. Inreal world ONLY admins can do this function. Need Role check
        machine.setState(machine.getMaintState());
        dispenseProduct(machine, BISCUIT);
        machine.reset();
        dispenseProduct(machine, BISCUIT);
    }

    private static void dispenseCoke(VendingMachineImpl machine, String coke) {
        try {
            machine.insertDollaer();
            machine.dispense(coke.toUpperCase());
        } catch (Exception e) {
            stderr(e);
        }
    }

    private static void dispenseProduct(VendingMachineImpl machine, String product) {
        try {
            machine.insertDollaer();
            machine.insertQuater();
            machine.dispense(product.toUpperCase());
        } catch (Exception e) {
            stderr(e);
        }
    }
}
