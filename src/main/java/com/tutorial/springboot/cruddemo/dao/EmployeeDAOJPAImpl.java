package com.tutorial.springboot.cruddemo.dao;

import com.tutorial.springboot.cruddemo.entity.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

@Repository
public class EmployeeDAOJPAImpl implements EmployeeDAO {

    private EntityManager entityManager;

    @Autowired
    public EmployeeDAOJPAImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public List<Employee> findAll() {
        //create a query
        Query query = entityManager.createQuery("from Employee");
        //execute query and get a result list
        return query.getResultList();
    }

    @Override
    public Employee findById(int id) {
       return entityManager.find(Employee.class, id);
    }

    @Override
    public void save(Employee employee) {
        Employee newEmployee = entityManager.merge(employee);
        //update with id from db
        employee.setId(newEmployee.getId());
    }

    @Override
    public void deleteById(int id) {
        //delete object with primary key
        Query query = entityManager.createQuery("delete from Employee where id=:employeeId");
        query.setParameter("employeeId", id);
        query.executeUpdate();
    }
}
