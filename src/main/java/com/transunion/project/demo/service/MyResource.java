package com.transunion.project.demo.service;

class MyResource implements AutoCloseable {
    private boolean closed = false;

    public void doWork() {
        if (closed) {
            throw new IllegalStateException("Resource already closed!");
        }
        System.out.println("Working with resource...");
    }

    @Override
    public void close() {
        closed = true;
        System.out.println("Resource closed.");
    }

    public boolean isClosed() {
        return closed;
    }
}
