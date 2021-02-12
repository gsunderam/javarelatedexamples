package com.sg.dp.interview;

import com.sg.dp.log.Logger;

import java.util.ArrayDeque;
import java.util.LinkedList;

/**
 * {@link ArrayDeque} is better and efficient than {@link LinkedList}, especially for large
 * datasets. The former is more memory efficient as well. If the use case is adding and removing from the
 * head/tail, then {@link ArrayDeque} is the way to go. deque doesn't offer indexed methods
 */
public class CollectionsExamples {
    public static void main(String[] args) {
        LinkedList<Integer> ll = new LinkedList<>();
        ll.add(1);
        ll.addFirst(2);
        ll.addFirst(3);
        ll.addLast(5);
        ll.addLast(7);
        ll.addFirst(4);

        Logger.stdout(ll);
        int first = ll.removeFirst();
        int last = ll.removeLast();
        Logger.stdout(ll);

        ArrayDeque deque = new ArrayDeque(8);
        deque.add(23);
        deque.addFirst(33);
        deque.offerFirst(25);
        deque.offer(32);
        deque.offerLast(41);

        Logger.stdout(deque);

    }
}
