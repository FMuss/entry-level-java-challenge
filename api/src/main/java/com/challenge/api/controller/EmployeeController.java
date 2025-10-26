package com.challenge.api.controller;

import com.challenge.api.config.ApiKeyInterceptor;
import com.challenge.api.dto.CreateEmployeeRequest;
import com.challenge.api.model.Employee;
import com.challenge.api.service.EmployeeService;
import com.challenge.api.web.EmployeeNotFoundException;
import jakarta.validation.Valid;
import java.util.List;
import java.util.UUID;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

/**
 * Exposes employee info for Employees-R-US webhooks.
 * Security is enforced via an API key interceptor {@link ApiKeyInterceptor}
 * To call any endpoint, include the following header:
 * <p>
 *     api-key: {key}
 * </p>
 * The default key is <code>key123</code> and can be configured in <code>application.yml</code>
 */
@RestController
@RequestMapping("/api/v1/employee")
public class EmployeeController {

    private final EmployeeService service;

    public EmployeeController(EmployeeService service) {
        this.service = service;
    }

    @GetMapping
    public List<Employee> getAllEmployees() {
        return service.getAllEmployees();
    }

    @GetMapping("/{uuid}")
    public Employee getEmployeeByUuid(@PathVariable UUID uuid) {
        return service.getEmployeeByUuid(uuid).orElseThrow(() -> new EmployeeNotFoundException(uuid));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Employee createEmployee(@RequestBody @Valid CreateEmployeeRequest requestBody) {
        return service.createEmployee(requestBody);
    }
}
