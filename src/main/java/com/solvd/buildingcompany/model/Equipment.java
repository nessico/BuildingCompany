package com.solvd.buildingcompany.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Date;

public class Equipment {
    @JsonProperty("equipment_id")
    private int equipmentId;

    @JsonProperty("equipment_name")
    private String equipmentName;


    @JsonProperty("maintenance_date")
    private Date maintenanceDate;

    // Constructors
    public Equipment() {}

    public Equipment(int equipmentId, String equipmentName, Date maintenanceDate) {
        this.equipmentId = equipmentId;
        this.equipmentName = equipmentName;
        this.maintenanceDate = maintenanceDate;
    }

    public int getEquipmentId() {
        return equipmentId;
    }

    public void setEquipmentId(int equipmentId) {
        this.equipmentId = equipmentId;
    }

    public String getEquipmentName() {
        return equipmentName;
    }

    public void setEquipmentName(String equipmentName) {
        this.equipmentName = equipmentName;
    }

    public Date getMaintenanceDate() {
        return maintenanceDate;
    }

    public void setMaintenanceDate(Date maintenanceDate) {
        this.maintenanceDate = maintenanceDate;
    }

    // toString method
    @Override
    public String toString() {
        return "Equipment{" +
                "equipmentId=" + equipmentId +
                ", equipmentName='" + equipmentName + '\'' +
                ", maintenanceDate=" + maintenanceDate +
                '}';
    }
}
