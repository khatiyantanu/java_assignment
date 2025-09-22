package com.transunion.project.demo.service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class SquareUsingStreams {
	// Method 1: Square all numbers
    public static List<Integer> squareAll(List<Integer> numbers) {
        return numbers.stream()
                      .map(n -> n * n)
                      .collect(Collectors.toList());
    }

    // Method 2: Square only even numbers
    public static List<Integer> squareEven(List<Integer> numbers) {
        return numbers.stream()
                      .filter(n -> n % 2 == 0)
                      .map(n -> n * n)
                      .collect(Collectors.toList());
    }

    // Main method for testing
    public static void main(String[] args) {
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6);

        System.out.println("Squares of all numbers: " + squareAll(numbers));
        System.out.println("Squares of even numbers: " + squareEven(numbers));
    }
}

