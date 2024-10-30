package org.example;

import java.time.LocalDate;
import java.util.UUID;

public class Employee {

    long employeeId;
    String firstName;
    String lastName;
    LocalDate employmentDate;
    double yearlySalary;
    PensionPlan pensionPlan;


    public Employee() {
    }

    public Employee( String firstName, String lastName,  double yearlySalary,LocalDate employmentDate) {
        this.employeeId = UUID.randomUUID().getMostSignificantBits();
        this.firstName = firstName;
        this.lastName = lastName;
        this.employmentDate = employmentDate;
        this.yearlySalary = yearlySalary;
    }

    public Employee( String firstName, String lastName, double yearlySalary,LocalDate employmentDate, PensionPlan pensionPlan) {
        this.employeeId = UUID.randomUUID().getMostSignificantBits();
        this.firstName = firstName;
        this.lastName = lastName;
        this.employmentDate = employmentDate;
        this.yearlySalary = yearlySalary;
        this.pensionPlan = pensionPlan;
    }

    public long getEmployeeId() {
        return employeeId;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public LocalDate getEmploymentDate() {
        return employmentDate;
    }

    public double getYearlySalary() {
        return yearlySalary;
    }

    public void setEmployeeId(long employeeId) {
        this.employeeId = employeeId;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setEmploymentDate(LocalDate employmentDate) {
        this.employmentDate = employmentDate;
    }

    public void setYearlySalary(double yearlySalary) {
        this.yearlySalary = yearlySalary;
    }
}
