package com.sg.dp.corejava;

import static com.sg.dp.log.Logger.stdout;

/**
 * Created by chandrashekar on 2/23/2017.
 */
public class VolatileFlag {
    private static boolean flag = true;
    private static int tasksCount;
    private static final int MAX_TASKS_EXECUTED = 20;

    public static void main(String[] args) {
        new Thread(new ShutdownSwitch()).start();
        new Thread(new ServerProgram()).start();
    }

    static class ShutdownSwitch implements Runnable {
        @Override
        public void run() {
            while (true){
                if(!flag){
//                    stdout("Flag: " + flag + " Starting shutdown sequence.");
                    for(int i = 0; i < 100000; i++);

//                    stdout("Flag: " + flag + " shut down complete. All closed successfully");
                    System.exit(0);
                }
            }
        }
    }

    static class ServerProgram implements Runnable {
        @Override
        public void run() {
            while (true){
                if(flag){
                    stdout("Flag: " + flag + " Running server..Tasks Run " + tasksCount);
                    tasksCount++;
                    if (tasksCount == MAX_TASKS_EXECUTED) {
                        flag = false;
                        stdout("Flag reset. Server will be shutting down.");
                        break;
                    }
                }
            }
        }
    }
}
