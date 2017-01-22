package com.sg.dp.concurrent;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.BlockingQueue;

import static com.sg.dp.log.Logger.stdout;

/**
 * Created by chandrashekar on 1/22/2017.
 * consumes the jobs from the SAME blocking queue where producer had put it
 */
public class DelayJobConsumer implements Runnable {
    private BlockingQueue<DelayJob> queue;

    public DelayJobConsumer(BlockingQueue<DelayJob> queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        DelayJob job = null;
        //pick up the job from the delay queue
        while (true) {
            try {
                job = queue.take();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            stdout(Thread.currentThread().getName() + ":Job Name: " + job + " processed successfully at " + convertMillisToDate(System.currentTimeMillis()));

            //Exit condition to exit the System gracefully
            if (job.data.indexOf("Exit") != -1) {
              exit(0);
            }
        }
    }

    private void exit(int exitStatus) {
        stdout("All jobs processed. Exiting the system");
        System.exit(exitStatus);
    }

    public String convertMillisToDate(long millis) {
        long yourmilliseconds = System.currentTimeMillis();
        SimpleDateFormat sdf = new SimpleDateFormat("MMM dd,yyyy HH:mm:ss");
        Date resultdate = new Date(millis);
        return sdf.format(resultdate);
    }
}
