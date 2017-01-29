package com.sg.dp.corejava;

import static com.sg.dp.log.Logger.stdout;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * SDF is not thread safe, so usually it is safe to creae an object of SDF per thread
 * @author chandrashekar
 *
 */
public class ThreadLocalDateFormat implements Runnable {
	private static final ThreadLocal<SimpleDateFormat> sdf = ThreadLocal.withInitial(() -> { 
			stdout("Initing SDF for thread: " + Thread.currentThread().getName());
			return new SimpleDateFormat("MM/dd/YYYY");
		});

	@Override
	public void run() {
		stdout("Pattern for thread " + Thread.currentThread().getName() + " is " + sdf.get().toPattern());
		stdout(sdf.get().format(new Date()));
	}
	
	public static void main(String[] args) {
		ThreadLocalDateFormat tldf = new ThreadLocalDateFormat();
		Thread t1 = new Thread(tldf, "A");
		Thread t2 = new Thread(tldf, "B");
		Thread t3 = new Thread(tldf, "C");
		t1.start();t2.start();t3.start();
	}
}
