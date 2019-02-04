package com.tutorial.springboot.cruddemo.service;

import com.tutorial.springboot.cruddemo.dao.spring.data.jpa.EmployeeRepository;
import com.tutorial.springboot.cruddemo.entity.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
//@Transactional remove since JPARepository provides it out of the box
@Primary
public class EmployeeServiceSpringDataJPAImpl implements EmployeeService {

    private EmployeeRepository employeeRepository;

    @Autowired
    public EmployeeServiceSpringDataJPAImpl(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Override
    public List<Employee> findAll() {
        return employeeRepository.findAll();
    }

    @Override
    public Employee findById(int id) {
        Optional<Employee> result = employeeRepository.findById(id);
        Employee employee;
        if (result.isPresent()){
            employee = result.get();
        } else {
            throw new RuntimeException("Employee not found with id - " + id);
        }
        return employee;
    }

    @Override
    public void save(Employee employee) {
        employeeRepository.save(employee);
    }

    @Override
    public void deleteById(int id) {
        employeeRepository.deleteById(id);
    }
}
