package com.sg.dp.singleton;

/**
 * Hard-to-get-it-right implementation of a Singleton supporting lazy
 * instantiation.
 * REFERENCE: https://www.tuturself.com/posts/view?menuId=63&postId=313&src=li
 */
public class Singleton implements Cloneable {
    private static final long serialVersionUID = 1L;

    /*
     * Marking volatile is necessary for avoiding the threads from creating
     * their own copies, even with double checked locking.
     */
    private static volatile Singleton INSTANCE;

    /*
     * Guard against 'reflection' to create a new instance.
     */
    private Singleton() {
        if (INSTANCE != null) {
            throw new IllegalStateException();
        }
    }

    /*
     * Synchronizes only on the critical section, and uses double checked
     * locking to ensure that all get requests are not Synchronized once the
     * instance is created.
     */
    public static Singleton getInstance() {
        if (INSTANCE == null) {
            synchronized (Singleton.class) {
                if (INSTANCE == null) {
                    INSTANCE = new Singleton();
                }
            }
        }
        return INSTANCE;
    }

    /**
     * Yeah, why dont you try cloning me? Nice one
     */
    @Override
    protected Object clone() throws CloneNotSupportedException {
        throw new CloneNotSupportedException();
    }
}
