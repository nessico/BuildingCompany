package com.solvd.buildingcompany.dao;

import com.solvd.buildingcompany.model.Employee;

import java.util.List;

public interface EmployeeDao {

    Employee getEmployeeById(int id);

    List<Employee> getAllEmployees();

    void insertEmployee(Employee employee);

    void updateEmployee(Employee employee);

    void deleteEmployee(int id);

}
