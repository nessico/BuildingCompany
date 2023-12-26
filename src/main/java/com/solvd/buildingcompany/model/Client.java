package com.solvd.buildingcompany.model;

public class Client {
    private int clientId;
    private String clientName;
    private String contactInfo;

    public Client() {
        // Default constructor
    }

    public Client(int clientId, String clientName, String contactInfo) {
        this.clientId = clientId;
        this.clientName = clientName;
        this.contactInfo = contactInfo;
    }

    // Getters and Setters
    public int getClientId() {
        return clientId;
    }

    public void setClientId(int clientId) {
        this.clientId = clientId;
    }

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public String getContactInfo() {
        return contactInfo;
    }

    public void setContactInfo(String contactInfo) {
        this.contactInfo = contactInfo;
    }

    @Override
    public String toString() {
        return "Client{" +
                "clientId=" + clientId +
                ", clientName='" + clientName + '\'' +
                ", contactInfo='" + contactInfo + '\'' +
                '}';
    }
}
