package com.tutorial.springboot.cruddemo.dao;

import com.tutorial.springboot.cruddemo.entity.Employee;

import java.util.List;

public interface EmployeeDAO {
    public List<Employee> findAll();
}
