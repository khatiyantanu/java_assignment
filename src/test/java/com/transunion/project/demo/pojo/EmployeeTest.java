package com.transunion.project.demo.pojo;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import java.util.Comparator;
import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EmployeeTest {

	// Case 1: Without hashCode or equals
    @Test
    void testWithoutHashCodeOrEquals() {
        Employee e1 = new Employee(1, "John", "Doe");
        Employee e2 = new Employee(1, "John", "Doe");

        Set<Employee> set = new HashSet<>();
        set.add(e1);
        set.add(e2);

        // Different objects, HashSet treats them as unique
        assertEquals(2, set.size(), "HashSet allows duplicates if neither equals nor hashCode is overridden");
    }

    // Case 2: Only hashCode
    @Test
    void testWithOnlyHashCode() {
        Employee e1 = new Employee.WithHashCode(1, "John", "Doe");
        Employee e2 = new Employee.WithHashCode(1, "John", "Doe");

        Set<Employee> set = new HashSet<>();
        set.add(e1);
        set.add(e2);

        // Same hash but equals not overridden, still treated as different
        assertEquals(2, set.size(), "HashSet stores both when only hashCode is implemented");
    }

    // Case 3: Only equals
    @Test
    void testWithOnlyEquals() {
        Employee e1 = new Employee.WithEquals(1, "John", "Doe");
        Employee e2 = new Employee.WithEquals(1, "John", "Doe");

        Set<Employee> set = new HashSet<>();
        set.add(e1);
        set.add(e2);

        // equals says objects are equal, but different hash codes, both stored
        assertEquals(2, set.size(), "HashSet stores both when only equals is implemented");
    }

    // Case 4: Both hashCode & equals
    @Test
    void testWithHashCodeAndEquals() {
        Employee e1 = new Employee.WithHashCodeAndEquals(1, "John", "Doe");
        Employee e2 = new Employee.WithHashCodeAndEquals(1, "John", "Doe");

        Set<Employee> set = new HashSet<>();
        set.add(e1);
        set.add(e2);

        // Both hashCode and equals consistent, duplicates prevented
        assertEquals(1, set.size(), "HashSet prevents duplicates when both equals and hashCode are implemented");
    }
	
	
//	@Test
    void testDefaultSorting() {
        Set<Employee> employees = new TreeSet<>();

        employees.add(new Employee(3, "Alice", "Smith"));
        employees.add(new Employee(1, "John", "Doe"));
        employees.add(new Employee(2, "Bob", "Brown"));

        System.out.println("TreeSet with default sorting (by id):");
        employees.forEach(System.out::println);
    }
	
	
//	@Test
    void testCustomSortingByFirstName() {
        Set<Employee> employees = new TreeSet<>(Comparator.comparing(Employee::getFirstName));

        employees.add(new Employee(3, "Alice", "Smith"));
        employees.add(new Employee(1, "John", "Doe"));
        employees.add(new Employee(2, "Bob", "Brown"));

        System.out.println("TreeSet with custom sorting (by firstName):");
        employees.forEach(System.out::println);
    }
}

