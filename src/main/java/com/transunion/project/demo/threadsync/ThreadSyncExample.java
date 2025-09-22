package com.transunion.project.demo.threadsync;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreadSyncExample {

    public static void main(String[] args) throws InterruptedException, ExecutionException {
        // Executor for 4 worker threads
        ExecutorService executor = Executors.newFixedThreadPool(4);

        // Callable tasks for T1..T4
        Callable<String> task1 = () -> {
            System.out.println("T1 started");
            Thread.sleep(1000);
            System.out.println("T1 finished");
            return "T1 done";
        };

        Callable<String> task2 = () -> {
            System.out.println("T2 started");
            Thread.sleep(1000);
            System.out.println("T2 finished");
            return "T2 done";
        };

        Callable<String> task3 = () -> {
            System.out.println("T3 started");
            Thread.sleep(1000);
            System.out.println("T3 finished");
            return "T3 done";
        };

        Callable<String> task4 = () -> {
            System.out.println("T4 started");
            Thread.sleep(1000);
            System.out.println("T4 finished");
            return "T4 done";
        };

        List<Callable<String>> tasks = Arrays.asList(task1, task2, task3, task4);

        // invokeAll waits until all tasks complete
        executor.invokeAll(tasks);

        // Shutdown executor for T1..T4
        executor.shutdown();

        // T5 runs only after all previous tasks finish
        Thread t5 = new Thread(() -> System.out.println("T5 started after T1..T4 finished"));
        t5.start();
    }
}

