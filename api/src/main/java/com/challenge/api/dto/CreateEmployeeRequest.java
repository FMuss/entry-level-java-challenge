package com.challenge.api.dto;

import jakarta.validation.constraints.*;
import java.time.Instant;

/**
 * Data transfer object that represents the JSON body for creating a new Employee via the REST API.
 * Provides input validation through annotations before data is passed to the service layer.
 */
public class CreateEmployeeRequest {
    @NotBlank
    private String firstName;

    @NotBlank
    private String lastName;

    @NotBlank
    private String fullName;

    @PositiveOrZero
    @NotNull private Integer salary;

    @Positive @NotNull private Integer age;

    @NotBlank
    private String jobTitle;

    @Email
    @NotBlank
    private String email;

    @PastOrPresent
    private Instant contractHireDate;

    private Instant contractTerminationDate;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getSalary() {
        return salary;
    }

    public void setSalary(Integer salary) {
        this.salary = salary;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Instant getContractHireDate() {
        return contractHireDate;
    }

    public void setContractHireDate(Instant contractHireDate) {
        this.contractHireDate = contractHireDate;
    }

    public Instant getContractTerminationDate() {
        return contractTerminationDate;
    }

    public void setContractTerminationDate(Instant contractTerminationDate) {
        this.contractTerminationDate = contractTerminationDate;
    }
}
