package com.sg.dp.knapsack;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import static com.sg.dp.log.Logger.stdout;

public class Knapsack {
    private static final int [] weights = {0, 3, 5, 7, 9, 11};
    private static final int [] values = {0, 1, 4, 7, 8, 9};
    private static final int CAPACITY = 14;
    private static final int [][] knapsackTable = new int[weights.length][CAPACITY + 1];
    private static final Map<Integer, String> map = new HashMap<>();

    private void printTable() {
        for (int i = 0; i < weights.length; i++) {
            stdout(Arrays.toString(knapsackTable[i]));
        }

        stdout(map);
    }

    public void solve() {
        for (int i = 1; i < weights.length; i++) {
            for (int j = 1; j <= CAPACITY ; j++) {
                if (weights[i] > j) {
                    knapsackTable[i][j] = knapsackTable[i - 1][j];
                } else {
                    int prevValue = knapsackTable[i - 1][j];
                    int residue = knapsackTable[i - 1][j - weights[i]];
                    int currentValue = values[i] + residue;
                    if (prevValue > currentValue) {
                        knapsackTable[i][j] = prevValue;
                    } else {
                        knapsackTable[i][j] = currentValue;
                        String s = map.get(residue) != null ? map.get(residue) : "";
                        map.put(currentValue, i + "-" + s);
                    }
                }
            }
        }
    }

    public static void main(String[] args) {
        Knapsack knapsack = new Knapsack();
        knapsack.solve();
        knapsack.printTable();
        stdout(map.get(knapsackTable[weights.length - 1][CAPACITY]));
    }
}
