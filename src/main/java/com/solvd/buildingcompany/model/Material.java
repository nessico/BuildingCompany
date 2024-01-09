package com.solvd.buildingcompany.model;

import javax.xml.bind.annotation.*;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "material")
public class Material {
    @XmlAttribute
    private int materialId;

    @XmlElement(name = "materialName")
    private String materialName;

    @XmlElement(name = "supplierId")
    private int supplierId;


    // Constructors
    public Material() {}

    public Material(int materialId, String materialName, int supplierId) {
        this.materialId = materialId;
        this.materialName = materialName;
        this.supplierId = supplierId;
    }

    public int getMaterialId() {
        return materialId;
    }

    public void setMaterialId(int materialId) {
        this.materialId = materialId;
    }

    public String getMaterialName() {
        return materialName;
    }

    public void setMaterialName(String materialName) {
        this.materialName = materialName;
    }

    public int getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(int supplierId) {
        this.supplierId = supplierId;
    }


    // toString method
    @Override
    public String toString() {
        return "Material{" +
                "materialId=" + materialId +
                ", materialName='" + materialName + '\'' +
                ", supplierId=" + supplierId +
                '}';
    }
}
