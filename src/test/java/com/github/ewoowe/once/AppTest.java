package com.github.ewoowe.once;

import org.junit.Test;

import java.util.concurrent.atomic.AtomicInteger;

public class AppTest 
{
    @Test
    public void shouldAnswerWithTrue()
    {
        final AtomicInteger doCount = new AtomicInteger(0);
        final Once<String> once = new Once<>(() -> {
            int i = doCount.incrementAndGet();
            return "hello world - " + i;
        });
        for (int i = 0; i < 10000; i++)
            new Thread(() -> System.out.println(once.doo())).start();
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
