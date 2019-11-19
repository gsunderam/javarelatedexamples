package com.sg.dp.concurrent;

import com.sg.dp.log.Logger;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

import static com.sg.dp.log.Logger.stdout;

/**
 * Simple future task example using just threads in lieu of executors. This may be asked in interviews.
 */
public class FutureExample {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        FutureTask<String> task = new FutureTask(() -> stdout("Success"), "success");
        Thread t = new Thread(task);
        t.start();
        //Do other processing...

        //Call get on the future task to get the processing result
        stdout("result " + task.get());
    }
}
