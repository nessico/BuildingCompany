package com.solvd.buildingcompany.service;

import com.solvd.buildingcompany.model.Employee;

import java.util.List;

public interface EmployeeManagementService {

    Employee getEmployeeById(int employeeId);

    List<Employee> getAllEmployees();

    void addNewEmployee(Employee employee);

    void updateEmployee(Employee employee);

    void deleteEmployee(int employeeId);



}
