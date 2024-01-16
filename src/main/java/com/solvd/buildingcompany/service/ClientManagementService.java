package com.solvd.buildingcompany.service;

import com.solvd.buildingcompany.model.Client;

import java.util.List;

public interface ClientManagementService {

    Client getClientById(int clientId);

    List<Client> getAllClients();

    void addNewClient(Client client);

    void updateClient(Client client);

    void deleteClient(int clientId);


}
