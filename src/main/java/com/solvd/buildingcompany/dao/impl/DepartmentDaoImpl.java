package com.solvd.buildingcompany.dao.impl;

import com.solvd.buildingcompany.dao.DepartmentDao;
import com.solvd.buildingcompany.model.Department;
import com.solvd.buildingcompany.util.ConnectionPool;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DepartmentDaoImpl implements DepartmentDao {

    
    public Department getDepartmentById(int departmentId) {
        String sql = "SELECT * FROM Departments WHERE department_id = ?";
        try (Connection conn = ConnectionPool.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, departmentId);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return mapRowToDepartment(rs);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    
    public List<Department> getAllDepartments() {
        List<Department> departments = new ArrayList<>();
        String sql = "SELECT * FROM Departments";
        try (Connection conn = ConnectionPool.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                departments.add(mapRowToDepartment(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return departments;
    }

    
    public void insertDepartment(Department department) {
        String sql = "INSERT INTO Departments (department_name) VALUES (?)";
        try (Connection conn = ConnectionPool.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, department.getDepartmentName());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    
    public void updateDepartment(Department department) {
        String sql = "UPDATE Departments SET department_name = ? WHERE department_id = ?";
        try (Connection conn = ConnectionPool.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, department.getDepartmentName());
            stmt.setInt(2, department.getDepartmentId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    
    public void deleteDepartment(int departmentId) {
        String sql = "DELETE FROM Departments WHERE department_id = ?";
        try (Connection conn = ConnectionPool.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, departmentId);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private Department mapRowToDepartment(ResultSet rs) throws SQLException {
        Department department = new Department();
        department.setDepartmentId(rs.getInt("department_id"));
        department.setDepartmentName(rs.getString("department_name"));
        return department;
    }
}
