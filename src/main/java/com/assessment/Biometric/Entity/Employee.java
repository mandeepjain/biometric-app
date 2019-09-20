package com.assessment.Biometric.Entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Entity
@Table(name = "employee")
public class Employee extends AuditModel{

    @NotNull
    @Column(name="EMPLOYEE_NAME")
    private String employeeName;

    @NotNull
    @Column(name="BIO_KEY")
    private String key;

    @NotNull
    @Column(name="GENDER")
    private String gender;

    @Positive
    @Column(name="AGE")
    private int age;

    @Column(name="SALARY")
    private long salary;

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public long getSalary() {
        return salary;
    }

    public void setSalary(long salary) {
        this.salary = salary;
    }

    public Employee() {
    }
}

