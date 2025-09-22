package com.transunion.project.demo.threadsync;

import java.util.concurrent.CountDownLatch;

public class ThreadOrderWithCountDownLatch {
	
	public static void main(String[] args) {
        // Latch initialized with 4 (since T1–T4 must complete before T5 starts)
        CountDownLatch latch = new CountDownLatch(4);

        // Worker threads T1–T4
        Runnable worker = () -> {
            String threadName = Thread.currentThread().getName();
            System.out.println(threadName + " started");
            try {
                Thread.sleep(1000); // simulate work
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(threadName + " finished");
            latch.countDown(); // signal task completion
        };

        Thread t1 = new Thread(worker, "T1");
        Thread t2 = new Thread(worker, "T2");
        Thread t3 = new Thread(worker, "T3");
        Thread t4 = new Thread(worker, "T4");

        // Thread T5 waits for others
        Thread t5 = new Thread(() -> {
            try {
                latch.await(); // wait until count reaches 0
                System.out.println("T5 started after all other threads finished");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "T5");

        // Start all threads
        t1.start();
        t2.start();
        t3.start();
        t4.start();
        t5.start();
    }

}
