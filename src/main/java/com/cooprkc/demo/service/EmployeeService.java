package com.cooprkc.demo.service;

import com.cooprkc.demo.model.EmployeeEntity;
import com.cooprkc.demo.repository.EmployeeRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService {

    private final EmployeeRepository repository;

    public EmployeeService(EmployeeRepository repository) {
        this.repository = repository;
    }

    public List<EmployeeEntity> getAllEmployees() {
        return repository.findAll();
    }

    public EmployeeEntity getEmployeeById(Long id) {
        Optional<EmployeeEntity> employee = repository.findById(id);
        return employee.orElseThrow(() -> new IllegalArgumentException("Empleado no encontrado con id: " + id));
    }

    public EmployeeEntity createOrUpdateEmployee(EmployeeEntity entity) {
        return repository.save(entity);
    }

    public void deleteEmployeeById(Long id) {
        repository.deleteById(id);
    }
}
