package com.transunion.project.demo.service;

public class TryWithResourceExample {
    public static void main(String[] args) {
        MyResource resource = new MyResource();

        // Using try-with-resources
        try (MyResource res = resource) {
            res.doWork();
        }

        // After try block → resource must be closed
        System.out.println("Is resource closed? " + resource.isClosed());
    }
}

