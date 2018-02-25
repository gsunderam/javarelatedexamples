package com.sg.dp.corejava;


import static com.sg.dp.log.Logger.stdout;

/**
 * Created by chandrashekar on 2/23/2017.
 */
public class VolatileTest {
    private static volatile int MY_INT;

    public static void main(String[] args) {
        new Thread(new ChangeListener()).start();
        new Thread(new ChangeMaker()).start();
    }

    static class ChangeListener implements Runnable {
        @Override
        public void run() {
            int local_value = MY_INT;
            while ( local_value < 7){
                if( local_value!= MY_INT){
                    stdout("Got Change for MY_INT : {0}" + MY_INT);
                    local_value= MY_INT;
                }
            }
        }
    }

    static class ChangeMaker implements Runnable {
        @Override
        public void run() {
            int local_value = MY_INT;
            while (MY_INT <7){
                stdout("Incrementing MY_INT to {0}" + (local_value+1));
                MY_INT = ++local_value;
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) { e.printStackTrace(); }
            }
        }
    }
}
