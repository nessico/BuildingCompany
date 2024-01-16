package com.solvd.buildingcompany.dao.impl;

import com.solvd.buildingcompany.dao.InspectionDao;
import com.solvd.buildingcompany.model.Inspection;
import com.solvd.buildingcompany.util.ConnectionPool;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class InspectionDaoImpl implements InspectionDao {

    
    public Inspection getInspectionById(int inspectionId) {
        String sql = "SELECT * FROM Inspections WHERE inspection_id = ?";
        try (Connection conn = ConnectionPool.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, inspectionId);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return mapRowToInspection(rs);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    
    public List<Inspection> getAllInspections() {
        List<Inspection> inspections = new ArrayList<>();
        String sql = "SELECT * FROM Inspections";
        try (Connection conn = ConnectionPool.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                inspections.add(mapRowToInspection(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return inspections;
    }

    
    public void insertInspection(Inspection inspection) {
        String sql = "INSERT INTO Inspections (project_id, date, report) VALUES (?, ?, ?)";
        try (Connection conn = ConnectionPool.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, inspection.getProjectId());
            stmt.setDate(2, new java.sql.Date(inspection.getDate().getTime()));
            stmt.setString(3, inspection.getReport());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    
    public void updateInspection(Inspection inspection) {
        String sql = "UPDATE Inspections SET project_id = ?, date = ?, report = ? WHERE inspection_id = ?";
        try (Connection conn = ConnectionPool.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, inspection.getProjectId());
            stmt.setDate(2, new java.sql.Date(inspection.getDate().getTime()));
            stmt.setString(3, inspection.getReport());
            stmt.setInt(4, inspection.getInspectionId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    
    public void deleteInspection(int inspectionId) {
        String sql = "DELETE FROM Inspections WHERE inspection_id = ?";
        try (Connection conn = ConnectionPool.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, inspectionId);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private Inspection mapRowToInspection(ResultSet rs) throws SQLException {
        Inspection inspection = new Inspection();
        inspection.setInspectionId(rs.getInt("inspection_id"));
        inspection.setProjectId(rs.getInt("project_id"));
        inspection.setDate(rs.getDate("date"));
        inspection.setReport(rs.getString("report"));
        return inspection;
    }
}