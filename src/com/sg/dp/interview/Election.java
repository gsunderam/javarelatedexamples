package com.sg.dp.interview;

import com.sg.dp.log.Logger;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

public class Election {
    public int electCandidate(int n, int k) {
        List<Integer> students = new ArrayList<>(n);
        IntStream.range(0, n).forEach(i -> students.add(i + 1)); //Collect(list) gives us immutable list.
        Logger.stdout(students);                                 //So create our own list

        int next = k - 1;

        while(students.size() > 1) {
            students.remove(next);
            Logger.stdout(students);
            next += k - 1;
            next = next < students.size() - 1 ? next : next % students.size();
        }

        return students.get(0);
    }

    public static void main(String[] args) {
        Logger.stdout(new Election().electCandidate(6,4));
    }

}
