package com.sg.dp.corejava;

import static com.sg.dp.log.Logger.stdout;

import java.util.Arrays;

public class ThreadGroupDemo {
	
	@SuppressWarnings("unused")
	public static void main(String[] args) {
	  ThreadGroup tg1 = new ThreadGroup ("subgroup 1");
      Thread t1 = new Thread (tg1, "thread 1"); //these could be printing threads in eral world
      Thread t2 = new Thread (tg1, "thread 2"); //group them as ONE unit in a thread group
      Thread t3 = new Thread (tg1, "thread 3");
      
      ThreadGroup tg2 = new ThreadGroup ("subgroup 2"); //these could be logging threads
      Thread t4 = new Thread (tg2, "my thread");
      
      ThreadGroup mainThreadGrp = Thread.currentThread().getThreadGroup(); //main thread group
      int agc = mainThreadGrp.activeGroupCount();
      stdout("Active thread groups in " + tg1.getName () + " thread group: " + agc);
      ThreadGroup [] list = new ThreadGroup[agc];
      
      mainThreadGrp.enumerate(list);
      
      //GET the names of sub groups
      Arrays.stream(list).forEach(grp -> stdout(grp.getName()));
      
      ThreadGroup system = null;
      
      //Get all thread groups in the JVM.
      while(mainThreadGrp != null) {
    	  system = mainThreadGrp;
    	  mainThreadGrp = mainThreadGrp.getParent();
      }
      
      if (system != null) {
         Thread [] thds = new Thread [system.activeCount ()];
         int nthds = system.enumerate (thds);
         for (Thread thread : thds)
              stdout(thread + " " + thread.isDaemon ());
      }
	}

}
