package com.solvd.buildingcompany.service.impl;

import com.solvd.buildingcompany.dao.impl.EmployeeDaoImpl;
import com.solvd.buildingcompany.model.Employee;
import com.solvd.buildingcompany.service.EmployeeManagementService;

import java.util.List;
import java.util.Objects;

public class EmployeeManagementServiceImpl implements EmployeeManagementService {

    private final EmployeeDaoImpl employeeDaoImpl;

    public EmployeeManagementServiceImpl() {
        this.employeeDaoImpl = new EmployeeDaoImpl();
    }

    @Override
    public Employee getEmployeeById(int employeeId) {
        validateEmployeeId(employeeId);
        Employee employee = employeeDaoImpl.getEmployeeById(employeeId);
        if (employee == null) {
            throw new IllegalStateException("Employee not found for ID: " + employeeId);
        }
        return employee;
    }

    @Override
    public List<Employee> getAllEmployees() {
        return employeeDaoImpl.getAllEmployees();
    }

    @Override
    public void addNewEmployee(Employee employee) {
        validateNewEmployee(employee);
        employeeDaoImpl.insertEmployee(employee);
        System.out.println("New employee added: " + employee.getName());
    }

    @Override
    public void updateEmployee(Employee employee) {
        validateExistingEmployee(employee);
        employeeDaoImpl.updateEmployee(employee);
        System.out.println("Employee updated: " + employee.getName());
    }

    @Override
    public void deleteEmployee(int employeeId) {
        validateEmployeeId(employeeId);
        employeeDaoImpl.deleteEmployee(employeeId);
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
