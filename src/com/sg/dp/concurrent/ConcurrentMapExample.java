package com.sg.dp.concurrent;

import java.util.Iterator;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by chandrashekar on 2/8/2017.
 * CHM by default uses 16 threads for lock striping. 16 threads can write to 16 different buckets
 * concurrently, that's why the name.
 */
public class ConcurrentMapExample {
    public static void main(String[] args) {
        ConcurrentHashMap<String,String> premiumPhone = new ConcurrentHashMap<String,String>();
        premiumPhone.put("Apple", "iPhone6");
        premiumPhone.put("HTC", "HTC one");
        premiumPhone.put("Samsung","S6");

        printMap(premiumPhone);
    }

    /**
     * CHM is fail-safe. It doesn't throw the CMexception when updated while iteration goes on
     * @param premiumPhone
     */
    private static void printMap(ConcurrentHashMap<String, String> premiumPhone) {
        Iterator iterator = premiumPhone.keySet().iterator();

        while (iterator.hasNext()) {
            System.out.println(premiumPhone.get(iterator.next()));
            premiumPhone.put("Sony", "Xperia Z");
        }
    }
}
