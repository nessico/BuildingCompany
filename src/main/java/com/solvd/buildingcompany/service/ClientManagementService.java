package com.solvd.buildingcompany.service;

import com.solvd.buildingcompany.dao.ClientDao;
import com.solvd.buildingcompany.model.Client;

import java.util.List;
import java.util.Objects;

public class ClientManagementService {

    private final ClientDao clientDao;

    public ClientManagementService() {
        this.clientDao = new ClientDao();
    }

    public Client getClientById(int clientId) {
        validateClientId(clientId);
        Client client = clientDao.getClientById(clientId);
        if (client == null) {
            throw new IllegalStateException("Client not found for ID: " + clientId);
        }
        return client;
    }

    public List<Client> getAllClients() {
        return clientDao.getAllClients();
    }

    public void addNewClient(Client client) {
        validateNewClient(client);
        clientDao.insertClient(client);
        System.out.println("New client added: " + client.getClientName());
    }

    public void updateClient(Client client) {
        validateExistingClient(client);
        clientDao.updateClient(client);
        System.out.println("Client updated: " + client.getClientName());
    }

    public void deleteClient(int clientId) {
        validateClientId(clientId);
        clientDao.deleteClient(clientId);
        System.out.println("Client deleted with ID: " + clientId);
    }

    private void validateNewClient(Client client) {
        Objects.requireNonNull(client, "Client cannot be null");
        if (client.getClientName() == null || client.getClientName().trim().isEmpty()) {
            throw new IllegalArgumentException("Client name cannot be empty");
        }
    }

    private void validateExistingClient(Client client) {
        validateNewClient(client);
        if (client.getClientId() <= 0) {
            throw new IllegalArgumentException("Existing client must have a valid ID");
        }
    }

    private void validateClientId(int clientId) {
        if (clientId <= 0) {
            throw new IllegalArgumentException("Invalid client ID");
        }
    }
}
