

package com.kvc.joy.swing;


import javax.swing.SwingUtilities;

/**
 * Improvement version of the javax SwingWorker class to avoid deadlocks. This gives user
 * multi-threaded abilities within their swing apps.
 *
 * @author Kevice
 */
public abstract class XSwingWorker<T> {
    private T value;

    /**
     * Class to maintain reference to current worker thread
     * under separate synchronization control.
     */
    private static class ThreadVar {
        private Thread thread;

        ThreadVar(Thread t) {
            thread = t;
        }

        synchronized Thread get() {
            return thread;
        }

        synchronized void clear() {
            thread = null;
        }
    }

    private ThreadVar threadVar;

    /**
     * Get the value produced by the worker thread, or null if it
     * hasn't been constructed yet.
     * @return Object produced by worker thread.
     */
    protected synchronized T getValue() {
        return value;
    }

    /**
     * Set the value produced by worker thread
     *
     * @param x Sets value for worker thread.
     */
    private synchronized void setValue(T x) {
        value = x;
    }

    /**
     * Compute the value to be returned by the <code>get</code> method.
     *
     * @return Object computed.
     */
    public abstract T construct();

    /**
     * Called on the event dispatching thread (not on the worker thread)
     * after the <code>construct</code> method has returned.
     */
    public void finished() {
    }

    /**
     * A new method that interrupts the worker thread.  Call this method
     * to force the worker to stop what it's doing.
     */
    public void interrupt() {
        Thread t = threadVar.get();
        if (t != null) {
            t.interrupt();
        }
        threadVar.clear();
    }


    /**
     * Return the value created by the <code>construct</code> method.
     * Returns null if either the constructing thread or the current
     * thread was interrupted before a value was produced.
     *
     * @return the value created by the <code>construct</code> method
     */
    public T get() {
        while (true) {
            Thread t = threadVar.get();
            if (t == null) {
                return getValue();
            }
            try {
                t.join();
            }
            catch (InterruptedException e) {
                Thread.currentThread().interrupt(); // propagate
                return null;
            }
        }
    }


    /**
     * Start a thread that will call the <code>construct</code> method
     * and then exit.
     */
    public XSwingWorker() {
        new Runnable() {
            @Override
            public void run() {
                finished();
            }
        };

        Runnable doConstruct = new Runnable() {
            @Override
            public void run() {
                try {
                    setValue(construct());
                }
                finally {
                    threadVar.clear();
                }
                SwingUtilities.invokeLater(new Runnable() {
                    @Override
                    public void run() {
                        finished();
                    }
                });

            }
        };

        Thread t = new Thread(doConstruct);
        threadVar = new ThreadVar(t);
    }

    /**
     * Start the worker thread.
     */
    public void start() {
        Thread t = threadVar.get();
        if (t != null) {
            t.start();
        }
    }
}

