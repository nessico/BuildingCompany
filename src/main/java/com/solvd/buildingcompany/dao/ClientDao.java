package com.solvd.buildingcompany.dao;

import com.solvd.buildingcompany.model.Client;

import java.util.List;

public interface ClientDao {

    Client getClientById(int clientId);

    List<Client> getAllClients();

    void insertClient(Client client);

    void updateClient(Client client);

    void deleteClient(int clientId);


}
