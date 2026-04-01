package com.cooprkc.demo.service;

import com.cooprkc.demo.model.EmployeeEntity;
import com.cooprkc.demo.repository.EmployeeRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService {

    private final EmployeeRepository repository;

    public EmployeeService(EmployeeRepository repository) {
        this.repository = repository;
    }

    public List<EmployeeEntity> getAllEmployees() {
        List<EmployeeEntity> result = new ArrayList<>();
        repository.findAll().forEach(result::add);
        return result;
    }

    public EmployeeEntity getEmployeeById(Long id) {
        Optional<EmployeeEntity> employee = repository.findById(id);
        return employee.orElseThrow(() -> new IllegalArgumentException("Empleado no encontrado con id: " + id));
    }

    public EmployeeEntity createOrUpdateEmployee(EmployeeEntity entity) {
        if (entity.getId() == null) {
            return repository.save(entity);
        }

        Optional<EmployeeEntity> current = repository.findById(entity.getId());
        if (current.isPresent()) {
            EmployeeEntity newEntity = current.get();
            newEntity.setFirstName(entity.getFirstName());
            newEntity.setLastName(entity.getLastName());
            newEntity.setEmail(entity.getEmail());
            return repository.save(newEntity);
        }

        return repository.save(entity);
    }

    public void deleteEmployeeById(Long id) {
        repository.deleteById(id);
    }
}
