package com.sample.diffutil;

import java.util.Objects;

public class Employee {

    public int id;
    public String name;
    public String role;

    public Employee(final int id, final String name, final String role) {
        this.id = id;
        this.name = name;
        this.role = role;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getRole() {
        return role;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Employee employee = (Employee) o;
        return id == employee.id;
    }

    @Override
    public int hashCode() {

        return Objects.hash(id);
    }
}
