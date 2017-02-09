package com.sg.dp.singleton;

import static com.sg.dp.log.Logger.stdout;

/**
 * Created by chandrashekar on 12/5/2016.
 */
public class SingletonTest {
    public static void main(String[] args) throws InterruptedException {
        //create 5 threads and start. Using java 8 style lambda instead of anonymous classes.
        //run accepts () 0 args and a method body of just one line. so no braces needed
        for (int i = 0; i < 5; i++) new Thread(() -> stdout(Singleton.getInstance())).start();

        Thread.sleep(500);

        try {
            Singleton.getInstance().clone();
        } catch (CloneNotSupportedException e) {
            stdout("Object not cloneable");
        }
    }
}

