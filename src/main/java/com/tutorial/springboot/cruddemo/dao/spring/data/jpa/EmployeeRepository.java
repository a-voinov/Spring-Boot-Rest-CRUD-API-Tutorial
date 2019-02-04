package com.tutorial.springboot.cruddemo.dao.spring.data.jpa;

import com.tutorial.springboot.cruddemo.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
    //that's it...
}
