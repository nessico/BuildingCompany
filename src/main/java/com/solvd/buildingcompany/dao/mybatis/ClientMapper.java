package com.solvd.buildingcompany.dao.mybatis;

import com.solvd.buildingcompany.model.Client;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface ClientMapper {

    @Select("SELECT * FROM Clients WHERE client_id = #{id}")
    Client getClientById(@Param("id") int clientId);

    @Select("SELECT * FROM Clients")
    List<Client> getAllClients();

    @Insert("INSERT INTO Clients (client_name, contact_info) VALUES (#{clientName}, #{contactInfo})")
    void insertClient(Client client);

    @Update("UPDATE Clients SET client_name = #{clientName}, contact_info = #{contactInfo} WHERE client_id = #{clientId}")
    void updateClient(Client client);

    @Delete("DELETE FROM Clients WHERE client_id = #{clientId}")
    void deleteClient(@Param("id") int clientId);
}
