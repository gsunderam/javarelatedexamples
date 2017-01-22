package com.sg.dp.concurrent;

import java.util.Random;
import java.util.concurrent.BlockingQueue;

import static com.sg.dp.log.Logger.stdout;

/**
 * Created by chandrashekar on 1/22/2017.
 * Puts the delayed jobs for deferred processing in a special blocking queue in the concurrent API
 * Delaye Queue. Refer to java docs for more details
 */
public class DelayJobProducer implements Runnable {
    private BlockingQueue<DelayJob> queue;
    private Random random = new Random();

    public DelayJobProducer(BlockingQueue<DelayJob> queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        //Create a queue of delayed jobs and put it in the delay queue.
        stdout("Producing jobs to put into the Queue");
        int highest = 10000;

//        while(true) {
            for (int i = 0; i < 10; i++) {
                int delay = random.nextInt(10000);
                if (delay > highest) highest = delay; //Save the highest delay

                DelayJob job = new DelayJob("DATA From Server " + i, delay + i);
                try {
                    queue.put(job);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        try {
            //Make sure all real jobs get processed before exiting
            queue.put(new DelayJob("Exit process ", highest + 1000));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
//        }
    }
}
