package com.sg.dp.systemdesign.vending;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.DoubleAdder;
import static com.sg.dp.log.Logger.stdout;

/**
 * Vending machine implementation using the STATE Design Pattern from the GOF
 * Three states are posiible viz. Working, Soldout and Maint
 */
public abstract class VendingMachine {
    MachineState workingState = new WorkingState(this);
    MachineState soldoutState = new SoldoutState(this);
    MachineState maintState = new MaintState(this);

    protected MachineState state = new WorkingState(this);

    protected final DoubleAdder moneyInserted = new DoubleAdder();
    private static final Map<String, Integer> productCounts = new ConcurrentHashMap<>();

    static {
        for (Products product: Products.values()) {
            productCounts.put(product.toString(), product.getCount());
        }
    }

    public static Map<String, Integer> getProductCounts() {
        return productCounts;
    }

    public void setState(MachineState state) {
        this.state = state;
        stdout("CURRENT State: " + state);
    }

    public MachineState getWorkingState() {
        return workingState;
    }

    public MachineState getSoldoutState() {
        return soldoutState;
    }

    public MachineState getMaintState() {
        return maintState;
    }

}
