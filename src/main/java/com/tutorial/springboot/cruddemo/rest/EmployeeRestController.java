package com.tutorial.springboot.cruddemo.rest;

import com.tutorial.springboot.cruddemo.entity.Employee;
import com.tutorial.springboot.cruddemo.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class EmployeeRestController {

    private EmployeeService employeeService;

    // constructor injection
    @Autowired
    public EmployeeRestController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    //expose "/employees" and return list of employees
    @GetMapping("/employees")
    public List<Employee> getEmployees(){
        return employeeService.findAll();
    }

    //add mapping for GET /employees/{employeeId}
    @GetMapping("/employees/{employeeId}")
    public Employee getEmployee(@PathVariable int employeeId){
        Employee employee = employeeService.findById(employeeId);
        if (employee == null)
            throw new RuntimeException("Employee id not found - " + employeeId);
        return employee;
    }

    //add mapping for POST /employees - adding new employee
    @PostMapping("/employees")
    public Employee addEmployee(@RequestBody Employee employee){
        employee.setId(0);
        employeeService.save(employee);
        return employee;
    }

    //add mapping for PUT /employees - updating employee
    @PutMapping("/employees")
    public Employee updateEmployee(@RequestBody Employee employee){
        employeeService.save(employee);
        return employee;
    }

    //add mapping for DELETE /employees - deleting employee
    @DeleteMapping("/employees/{employeeId}")
    public String deleteEmployee(@PathVariable int employeeId){
        Employee employee = employeeService.findById(employeeId);
        //throw exception if employee was not found
        if (employee == null)
            throw new RuntimeException("Employee id not found - " + employeeId);
        employeeService.deleteById(employeeId);
        return "Deleted employee with id - " + employeeId;
    }

}
