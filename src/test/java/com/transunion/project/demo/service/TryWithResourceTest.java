package com.transunion.project.demo.service;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TryWithResourceTest {

    @Test
    void testResourceClosed() {
        MyResource resource = new MyResource();

        try (MyResource res = resource) {
            res.doWork();
        }

        //Verify resource was closed automatically
        assertTrue(resource.isClosed(), "Resource should be closed after try-with-resources");
    }
}

