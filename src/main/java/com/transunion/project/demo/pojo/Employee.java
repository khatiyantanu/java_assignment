package com.transunion.project.demo.pojo;

public class Employee  implements Comparable<Employee>{
    private int id;
    private String firstName;
    private String lastName;

    public Employee(int id, String firstName, String lastName) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    // Getters
    public int getId() { return id; }
    public String getFirstName() { return firstName; }
    public String getLastName() { return lastName; }
    
    
    // Case 2: only hashCode ---
    public static class WithHashCode extends Employee {
        public WithHashCode(int id, String f, String l) { super(id, f, l); }
        @Override
        public int hashCode() {
            int hash = getId();
            System.out.println("hashCode() called : " + this + " = " + hash);
            return hash;
        }
    }

    // --Case 3: only equals ---
    public static class WithEquals extends Employee {
        public WithEquals(int id, String f, String l) { super(id, f, l); }
        @Override
        public boolean equals(Object obj) {
            System.out.println("equals() called : " + this + " vs " + obj);
            if (this == obj) return true;
            if (!(obj instanceof Employee)) return false;
            Employee other = (Employee) obj;
            return getId() == other.getId()
                    && getFirstName().equals(other.getFirstName())
                    && getLastName().equals(other.getLastName());
        }
    }

    // --- Case 4: both hashCode & equals ---
    public static class WithHashCodeAndEquals extends Employee {
        public WithHashCodeAndEquals(int id, String f, String l) { super(id, f, l); }
        @Override
        public int hashCode() {
            int hash = getId();
            System.out.println("hashCode() called : " + this + " = " + hash);
            return hash;
        }
        @Override
        public boolean equals(Object obj) {
            System.out.println("equals() called : " + this + " vs " + obj);
            if (this == obj) return true;
            if (!(obj instanceof Employee)) return false;
            Employee other = (Employee) obj;
            return getId() == other.getId()
                    && getFirstName().equals(other.getFirstName())
                    && getLastName().equals(other.getLastName());
        }
    }

    
    @Override
    public int compareTo(Employee other) {
        return Integer.compare(this.id, other.id);
    }

    @Override
    public String toString() {
        return "Employee{id=" + id + ", name=" + firstName + " " + lastName + "}";
    }
}
