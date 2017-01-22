package com.sg.dp.concurrent;

import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

/**
 * Created by chandrashekar on 1/22/2017.
 * Models a job or task that is intended to be processed after a fixed delay.
 */
public class DelayJob implements Delayed {
    String data;
    long startTime;

    public DelayJob(String data, long delay) {
        this.data = data;
        this.startTime = System.currentTimeMillis() + delay;
    }

    public DelayJob() {}

    @Override
    public long getDelay(TimeUnit unit) {
        long diff = startTime - System.currentTimeMillis();
        return unit.convert(diff, TimeUnit.MILLISECONDS);
    }

    @Override
    public int compareTo(Delayed o) {
        if (this.startTime < ((DelayJob) o).startTime) {
            return -1;
        }

        if (this.startTime > ((DelayJob) o).startTime) {
            return 1;
        }

        return 0;
    }

    @Override
    public String toString() {
        return "Data " + data + " " + startTime + "]";
    }
}
