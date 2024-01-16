package com.solvd.buildingcompany.service.impl;

import com.solvd.buildingcompany.dao.impl.ClientDaoImpl;
import com.solvd.buildingcompany.model.Client;
import com.solvd.buildingcompany.service.ClientManagementService;

import java.util.List;
import java.util.Objects;

public class ClientManagementServiceImpl implements ClientManagementService {

    private final ClientDaoImpl clientDaoImpl;

    public ClientManagementServiceImpl (){
        this.clientDaoImpl = new ClientDaoImpl();
    }

    @Override
    public Client getClientById(int clientId) {
        validateClientId(clientId);
        Client client = clientDaoImpl.getClientById(clientId);
        if (client == null) {
            throw new IllegalStateException("Client not found for ID: " + clientId);
        }
        return client;
    }

    @Override
    public List<Client> getAllClients() {
        return clientDaoImpl.getAllClients();
    }

    @Override
    public void addNewClient(Client client) {
        validateNewClient(client);
        clientDaoImpl.insertClient(client);
        System.out.println("New client added: " + client.getClientName());
    }

    @Override
    public void updateClient(Client client) {
        validateExistingClient(client);
        clientDaoImpl.updateClient(client);
        System.out.println("Client updated: " + client.getClientName());
    }

    @Override
    public void deleteClient(int clientId) {
        validateClientId(clientId);
        clientDaoImpl.deleteClient(clientId);
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
