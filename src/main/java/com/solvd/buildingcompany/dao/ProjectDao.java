package com.solvd.buildingcompany.dao;

import com.solvd.buildingcompany.model.Project;
import com.solvd.buildingcompany.util.ConnectionPool;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

// DAO should only contain db CRUD operations and should be called from a service
public class ProjectDao {

    public Project getProjectById(int projectId) {
        Project project = null;
        String sql = "SELECT * FROM projects WHERE id = ?";

        try (Connection conn = ConnectionPool.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, projectId);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    project = mapRowToProject(rs);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return project;
    }

    public List<Project> getAllProjects() {
        List<Project> projects = new ArrayList<>();
        String sql = "SELECT * FROM projects";

        try (Connection conn = ConnectionPool.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Project project = mapRowToProject(rs);
                projects.add(project);
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Replace with more robust error handling
        }

        return projects;
    }

    public void saveProject(Project project) {
        String sql = "INSERT INTO projects (name, start_date, end_date, budget) VALUES (?, ?, ?, ?)";

        try (Connection conn = ConnectionPool.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, project.getName());
            stmt.setDate(2, new java.sql.Date(project.getStartDate().getTime()));
            stmt.setDate(3, new java.sql.Date(project.getEndDate().getTime()));
            stmt.setDouble(4, project.getBudget());

            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateProject(Project project) {
        String sql = "UPDATE projects SET name = ?, start_date = ?, end_date = ?, budget = ? WHERE id = ?";

        try (Connection conn = ConnectionPool.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, project.getName());
            stmt.setDate(2, new java.sql.Date(project.getStartDate().getTime()));
            stmt.setDate(3, new java.sql.Date(project.getEndDate().getTime()));
            stmt.setDouble(4, project.getBudget());
            stmt.setInt(5, project.getId());

            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteProject(int projectId) {
        String sql = "DELETE FROM projects WHERE id = ?";

        try (Connection conn = ConnectionPool.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, projectId);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private Project mapRowToProject(ResultSet rs) throws SQLException {
        Project project = new Project();
        project.setId(rs.getInt("id"));
        project.setName(rs.getString("name"));
        project.setStartDate(rs.getDate("start_date"));
        project.setEndDate(rs.getDate("end_date"));
        project.setBudget(rs.getDouble("budget"));
        return project;
    }
}
