package com.solvd.buildingcompany.dao.impl;

import com.solvd.buildingcompany.dao.EmployeeDao;
import com.solvd.buildingcompany.model.Employee;
import com.solvd.buildingcompany.util.ConnectionPool;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EmployeeDaoImpl implements EmployeeDao {

    
    public Employee getEmployeeById(int id) {
        String sql = "SELECT * FROM Employees WHERE employee_id = ?";
        try (Connection conn = ConnectionPool.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return mapRowToEmployee(rs);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    
    public List<Employee> getAllEmployees() {
        List<Employee> employees = new ArrayList<>();
        String sql = "SELECT * FROM Employees";

        try (Connection conn = ConnectionPool.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                employees.add(mapRowToEmployee(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return employees;
    }

    
    public void insertEmployee(Employee employee) {
        String sql = "INSERT INTO Employees (name, department_id) VALUES (?, ?)";

        try (Connection conn = ConnectionPool.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, employee.getName());
            stmt.setInt(2, employee.getDepartmentId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    
    public void updateEmployee(Employee employee) {
        String sql = "UPDATE Employees SET name = ?, department_id = ? WHERE employee_id = ?";

        try (Connection conn = ConnectionPool.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, employee.getName());
            stmt.setInt(2, employee.getDepartmentId());
            stmt.setInt(3, employee.getEmployeeId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    
    public void deleteEmployee(int id) {
        String sql = "DELETE FROM Employees WHERE employee_id = ?";

        try (Connection conn = ConnectionPool.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private Employee mapRowToEmployee(ResultSet rs) throws SQLException {
        Employee employee = new Employee();
        employee.setEmployeeId(rs.getInt("employee_id"));
        employee.setName(rs.getString("name"));
        employee.setDepartmentId(rs.getInt("department_id"));
        employee.setCreatedAt(rs.getTimestamp("created_at"));
        employee.setUpdatedAt(rs.getTimestamp("updated_at"));
        return employee;
    }
}
