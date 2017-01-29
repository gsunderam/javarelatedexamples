package com.sg.dp.corejava;

import static com.sg.dp.log.Logger.stdout;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Thread locals are an alternative to using synchronization as its costly. Each thread has a copy of its
 * own state, like a threadId that needs to be unique per thread. Usually thread locals are static fields in the
 * Thread/Runnable class and return a value on a per thread basis. This doesn't change once set.
 * 
 * @author chandrashekar
 *
 */
class ThreadLocalDemo {
   public static void main (String [] args)
   {
      MyThread mt1 = new MyThread ("A");
      MyThread mt2 = new MyThread ("B");
      MyThread mt3 = new MyThread ("C");
      mt1.start();
      mt2.start();
      mt3.start();
   }
}

class MyThread extends Thread {
   //Atomic variable is needed instead of synchronization here
   private static AtomicInteger threadId = new AtomicInteger(100);
   
   private static ThreadLocal<Integer> tl = ThreadLocal.withInitial(() -> {
	   /* Called for each thread */
	   int id = threadId.getAndIncrement();
	   stdout("Initializing the id for " + Thread.currentThread().getName() + ":" + id);
	   return id;
	 });
          
   MyThread(String name) {
      super(name);
   }
   
   public void run() {
      for (int i = 0; i < 5; i++)
           stdout(getName() + " " + tl.get());
   }
}
