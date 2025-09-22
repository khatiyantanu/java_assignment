package com.transunion.project.demo.threadsync;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class WithCompletableFuture {
	 public static void main(String[] args) throws ExecutionException, InterruptedException {

	        Runnable worker = () -> {
	            String threadName = Thread.currentThread().getName();
	            System.out.println(threadName + " started");
	            try {
	                Thread.sleep(1000); // simulate work
	            } catch (InterruptedException e) {
	                e.printStackTrace();
	            }
	            System.out.println(threadName + " finished");
	        };

	        // Run T1–T4 asynchronously
	        CompletableFuture<Void> t1 = CompletableFuture.runAsync(worker);
	        CompletableFuture<Void> t2 = CompletableFuture.runAsync(worker);
	        CompletableFuture<Void> t3 = CompletableFuture.runAsync(worker);
	        CompletableFuture<Void> t4 = CompletableFuture.runAsync(worker);

	        // Run T5 only after all previous futures are done
	        CompletableFuture<Void> all = CompletableFuture.allOf(t1, t2, t3, t4);

	        all.thenRun(() -> {
	            System.out.println("T5 started after all other threads finished");
	        });

	        // Wait for completion (so main thread doesn’t exit early)
	        all.get();
	    }

}
