package com.transunion.project.demo.pojo;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import java.util.Comparator;
import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EmployeeTest {

	// 1️⃣ Without equals & hashCode
    @Test
    void testWithoutEqualsAndHashCode() {
        Employee e1 = new Employee(1, "John", "Doe");
        Employee e2 = new Employee(1, "John", "Doe");

        Set<Employee> set = new HashSet<>();
        set.add(e1);
        set.add(e2);

        // Two different objects → treated as unique
        assertEquals(2, set.size(), "HashSet stores duplicates if equals/hashCode not overridden");
    }

    // 2️⃣ Only hashCode
    static class EmployeeWithHashCode extends Employee {
        public EmployeeWithHashCode(int id, String f, String l) {
            super(id, f, l);
        }
        @Override
        public int hashCode() {
            return getId();
        }
    }

    @Test
    void testWithOnlyHashCode() {
        Employee e1 = new EmployeeWithHashCode(1, "John", "Doe");
        Employee e2 = new EmployeeWithHashCode(1, "John", "Doe");

        Set<Employee> set = new HashSet<>();
        set.add(e1);
        set.add(e2);

        // Hash codes same, but equals not overridden → still treated as different
        assertEquals(2, set.size(), "HashSet stores duplicates when only hashCode is implemented");
    }

    // 3️⃣ Only equals
    static class EmployeeWithEquals extends Employee {
        public EmployeeWithEquals(int id, String f, String l) {
            super(id, f, l);
        }
        @Override
        public boolean equals(Object obj) {
            if (this == obj) return true;
            if (!(obj instanceof EmployeeWithEquals)) return false;
            EmployeeWithEquals other = (EmployeeWithEquals) obj;
            return getId() == other.getId()
                    && getFirstName().equals(other.getFirstName())
                    && getLastName().equals(other.getLastName());
        }
    }

    @Test
    void testWithOnlyEquals() {
        Employee e1 = new EmployeeWithEquals(1, "John", "Doe");
        Employee e2 = new EmployeeWithEquals(1, "John", "Doe");

        Set<Employee> set = new HashSet<>();
        set.add(e1);
        set.add(e2);

        // equals says same, but hashCode differs → still both stored
        assertEquals(2, set.size(), "HashSet stores duplicates when only equals is implemented");
    }

    // 4️⃣ Both hashCode & equals
    static class EmployeeWithHashCodeAndEquals extends Employee {
        public EmployeeWithHashCodeAndEquals(int id, String f, String l) {
            super(id, f, l);
        }
        @Override
        public int hashCode() {
            return getId();
        }
        @Override
        public boolean equals(Object obj) {
            if (this == obj) return true;
            if (!(obj instanceof EmployeeWithHashCodeAndEquals)) return false;
            EmployeeWithHashCodeAndEquals other = (EmployeeWithHashCodeAndEquals) obj;
            return getId() == other.getId()
                    && getFirstName().equals(other.getFirstName())
                    && getLastName().equals(other.getLastName());
        }
    }

    @Test
    void testWithHashCodeAndEquals() {
        Employee e1 = new EmployeeWithHashCodeAndEquals(1, "John", "Doe");
        Employee e2 = new EmployeeWithHashCodeAndEquals(1, "John", "Doe");

        Set<Employee> set = new HashSet<>();
        set.add(e1);
        set.add(e2);

        // Both hashCode and equals consistent → duplicate prevented
        assertEquals(1, set.size(), "HashSet removes duplicates when equals & hashCode are both implemented");
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

