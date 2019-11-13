package com.sg.dp.systemdesign.vending;

public enum Money {
    QUARTER(0.25),
    DOLLAR(1.00);

    private final double value;

    Money(double value) {
        this.value = value;
    }

    public double getValue() {
        return value;
    }
}
