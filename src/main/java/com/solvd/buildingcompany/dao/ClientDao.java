package com.solvd.buildingcompany.dao;

import com.solvd.buildingcompany.model.Client;
import com.solvd.buildingcompany.util.ConnectionPool;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ClientDao {

    
    public Client getClientById(int clientId) {
        String sql = "SELECT * FROM Clients WHERE client_id = ?";
        try (Connection conn = ConnectionPool.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, clientId);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return mapRowToClient(rs);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    
    public List<Client> getAllClients() {
        List<Client> clients = new ArrayList<>();
        String sql = "SELECT * FROM Clients";
        try (Connection conn = ConnectionPool.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                clients.add(mapRowToClient(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return clients;
    }

    
    public void insertClient(Client client) {
        String sql = "INSERT INTO Clients (client_name, contact_info) VALUES (?, ?)";
        try (Connection conn = ConnectionPool.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, client.getClientName());
            stmt.setString(2, client.getContactInfo());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    
    public void updateClient(Client client) {
        String sql = "UPDATE Clients SET client_name = ?, contact_info = ? WHERE client_id = ?";
        try (Connection conn = ConnectionPool.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, client.getClientName());
            stmt.setString(2, client.getContactInfo());
            stmt.setInt(3, client.getClientId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    
    public void deleteClient(int clientId) {
        String sql = "DELETE FROM Clients WHERE client_id = ?";
        try (Connection conn = ConnectionPool.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, clientId);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private Client mapRowToClient(ResultSet rs) throws SQLException {
        Client client = new Client();
        client.setClientId(rs.getInt("client_id"));
        client.setClientName(rs.getString("client_name"));
        client.setContactInfo(rs.getString("contact_info"));
        return client;
    }
}
