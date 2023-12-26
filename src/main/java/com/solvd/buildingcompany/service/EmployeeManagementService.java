package com.solvd.buildingcompany.service;

import com.solvd.buildingcompany.dao.EmployeeDao;
import com.solvd.buildingcompany.model.Employee;

import java.util.List;
import java.util.Objects;

public class EmployeeManagementService {

    private final EmployeeDao employeeDao;

    public EmployeeManagementService() {
        this.employeeDao = new EmployeeDao();
    }

    public Employee getEmployeeById(int employeeId) {
        validateEmployeeId(employeeId);
        Employee employee = employeeDao.getEmployeeById(employeeId);
        if (employee == null) {
            throw new IllegalStateException("Employee not found for ID: " + employeeId);
        }
        return employee;
    }

    public List<Employee> getAllEmployees() {
        return employeeDao.getAllEmployees();
    }

    public void addNewEmployee(Employee employee) {
        validateNewEmployee(employee);
        employeeDao.insertEmployee(employee);
        System.out.println("New employee added: " + employee.getName());
    }

    public void updateEmployee(Employee employee) {
        validateExistingEmployee(employee);
        employeeDao.updateEmployee(employee);
        System.out.println("Employee updated: " + employee.getName());
    }

    public void deleteEmployee(int employeeId) {
        validateEmployeeId(employeeId);
        employeeDao.deleteEmployee(employeeId);
        System.out.println("Employee deleted with ID: " + employeeId);
    }

    private void validateNewEmployee(Employee employee) {
        Objects.requireNonNull(employee, "Employee cannot be null");
        if (employee.getName() == null || employee.getName().trim().isEmpty()) {
            throw new IllegalArgumentException("Employee name cannot be empty");
        }
        if (employee.getDepartmentId() <= 0) {
            throw new IllegalArgumentException("Employee must belong to a valid department");
        }
    }

    private void validateExistingEmployee(Employee employee) {
        validateNewEmployee(employee);
        if (employee.getEmployeeId() <= 0) {
            throw new IllegalArgumentException("Existing employee must have a valid ID");
        }
    }

    private void validateEmployeeId(int employeeId) {
        if (employeeId <= 0) {
            throw new IllegalArgumentException("Invalid employee ID");
        }
    }
}
