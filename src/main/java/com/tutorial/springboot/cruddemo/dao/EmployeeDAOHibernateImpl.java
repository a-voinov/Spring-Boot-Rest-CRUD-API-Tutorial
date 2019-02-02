package com.tutorial.springboot.cruddemo.dao;

import com.tutorial.springboot.cruddemo.entity.Employee;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
public class EmployeeDAOHibernateImpl implements EmployeeDAO {

    //define field for EntityManager
    private EntityManager entityManager;

    //set up constructor injection
    @Autowired
    public EmployeeDAOHibernateImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public List<Employee> findAll() {
        //get the current hibernate session
        Session session = entityManager.unwrap(Session.class);
        //create a query
        Query<Employee> query =
                session.createQuery("from Employee", Employee.class);
        //execute the query and return result list
        return query.getResultList();
    }

    @Override
    public Employee findById(int id) {
        //get the current hibernate session
        Session session = entityManager.unwrap(Session.class);
        //get the employee
        Employee employee =
                session.get(Employee.class, id);
        //return the employee
        return employee;
    }

    @Override
    public void save(Employee employee) {
        Session session = entityManager.unwrap(Session.class);
        session.saveOrUpdate(employee);
    }

    @Override
    public void deleteById(int id) {
        Session session = entityManager.unwrap(Session.class);
        Query query =
                session.createQuery("delete from Employee where id=:employeeId");
        query.setParameter("employeeId", id);
        query.executeUpdate();
    }

}
