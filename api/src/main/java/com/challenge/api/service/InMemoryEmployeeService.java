package com.challenge.api.service;

import com.challenge.api.dto.CreateEmployeeRequest;
import com.challenge.api.model.Employee;
import com.challenge.api.model.impl.EmployeeImpl;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import org.springframework.stereotype.Service;

@Service
public class InMemoryEmployeeService implements EmployeeService {

    private final ConcurrentHashMap<UUID, Employee> employeeStore = new ConcurrentHashMap<>();

    public InMemoryEmployeeService() {
        // Adding a few sample employees for testing
        createEmployee(createSample("John", "McJohn", "John D. McJohn", 100000, 25, "SWE", "john@gmail.com"));
        createEmployee(createSample("Joe", "Joseph", "Joe H. Joseph", 110000, 30, "SysAdmin", "joe@gmail.com"));
    }

    @Override
    public List<Employee> getAllEmployees() {
        return new ArrayList<>(employeeStore.values());
    }

    @Override
    public Optional<Employee> getEmployeeByUuid(UUID uuid) {
        return Optional.ofNullable(employeeStore.get(uuid));
    }

    @Override
    public Employee createEmployee(CreateEmployeeRequest requestBody) {
        EmployeeImpl e = new EmployeeImpl();
        e.setUuid(UUID.randomUUID());
        e.setFirstName(requestBody.getFirstName());
        e.setLastName(requestBody.getLastName());
        e.setFullName(requestBody.getFullName());
        e.setSalary(requestBody.getSalary());
        e.setAge(requestBody.getAge());
        e.setJobTitle(requestBody.getJobTitle());
        e.setEmail(requestBody.getEmail());
        e.setContractHireDate(
                requestBody.getContractHireDate() != null ? requestBody.getContractHireDate() : Instant.now());
        e.setContractTerminationDate(requestBody.getContractTerminationDate());
        employeeStore.put(e.getUuid(), e);
        return e;
    }

    // Helper for creating sample employees for testing
    private static CreateEmployeeRequest createSample(
            String first, String last, String full, Integer salary, Integer age, String title, String email) {
        CreateEmployeeRequest sample = new CreateEmployeeRequest();
        sample.setFirstName(first);
        sample.setLastName(last);
        sample.setFullName(full);
        sample.setSalary(salary);
        sample.setAge(age);
        sample.setJobTitle(title);
        sample.setEmail(email);
        sample.setContractHireDate(Instant.now());
        sample.setContractTerminationDate(Instant.now().plus(365, ChronoUnit.DAYS));
        return sample;
    }
}
