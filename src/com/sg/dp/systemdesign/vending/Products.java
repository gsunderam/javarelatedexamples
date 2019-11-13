package com.sg.dp.systemdesign.vending;

public enum Products {
    BISCUIT(1.25, 2),
    COKE(1.00, 2),
    PROTEINBAR(1.25, 2);

    double charge;
    int count;

    Products(double charge, int count) {
        this.charge = charge;
        this.count = count;
    }

    public int getCount() {
        return count;
    }

    public static double getCharge(String product) {
        for (Products item: Products.values()) {
            if (item.toString().equalsIgnoreCase(product))
                return item.charge;
        }

        return 0;
    }
}