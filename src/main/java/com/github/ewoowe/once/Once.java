package com.github.ewoowe.once;

import java.util.concurrent.Callable;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * copy from go Once
 *
 * @author wangcheng
 * @since 2023/9/24
 */
public class Once<V> {

    private final AtomicBoolean done = new AtomicBoolean(false);

    private final Callable<V> doFunc;

    private volatile V outcome;

    public Once(Callable<V> doFunc) {
        this.doFunc = doFunc;
    }

    public V doo() {
        if (!done.get())
            doSlow();
        return outcome;
    }

    public synchronized void doSlow() {
        if (!done.get()) {
            try {
                outcome = doFunc.call();
                done.set(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
