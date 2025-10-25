package com.challenge.api.service;

import com.challenge.api.dto.CreateEmployeeRequest;
import com.challenge.api.model.Employee;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface EmployeeService {
    List<Employee> getAllEmployees();

    Optional<Employee> getEmployeeByUuid(UUID id);

    Employee createEmployee(CreateEmployeeRequest requestBody);
}
