package com.sg.dp.concurrent;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.DelayQueue;

/**
 * Created by chandrashekar on 1/22/2017.
 */
public class RunDelayJobs {
    public static void main(String[] args) {
        BlockingQueue<DelayJob> queue = new DelayQueue<>();
        new Thread(new DelayJobProducer(queue)).start();
        new Thread(new DelayJobConsumer(queue), "Job Consumer").start();
    }
}
