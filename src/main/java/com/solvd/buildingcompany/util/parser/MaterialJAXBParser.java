package com.solvd.buildingcompany.util.parser;

import com.solvd.buildingcompany.model.Material;
import org.xml.sax.SAXException;

import javax.xml.XMLConstants;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.helpers.DefaultValidationEventHandler;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import java.io.File;
import java.util.List;

public class MaterialJAXBParser {

    private Schema loadSchema(String schemaPath) {
        SchemaFactory factory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
        try {
            return factory.newSchema(new File(schemaPath));
        } catch (SAXException e) {
            throw new RuntimeException("Problem loading schema from path: " + schemaPath, e);
        }
    }

    public List<Material> unmarshalMaterials(String filePath, String schemaPath) {
        try {
            JAXBContext context = JAXBContext.newInstance(Materials.class);
            Unmarshaller unmarshaller = context.createUnmarshaller();
            Schema schema = loadSchema(schemaPath);
            unmarshaller.setSchema(schema);
            unmarshaller.setEventHandler(new DefaultValidationEventHandler());

            Materials materials = (Materials) unmarshaller.unmarshal(new File(filePath));
            return materials.getMaterialList();
        } catch (JAXBException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void marshalMaterials(List<Material> materialList, String filePath, String schemaPath) {
        try {
            JAXBContext context = JAXBContext.newInstance(Materials.class);
            Marshaller marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

            Schema schema = loadSchema(schemaPath);
            marshaller.setSchema(schema);

            Materials materials = new Materials();
            materials.setMaterialList(materialList);
            marshaller.marshal(materials, new File(filePath));
        } catch (JAXBException e) {
            e.printStackTrace();
        }
    }

    public List<Material> unmarshalMaterials(String filePath) {
        try {
            JAXBContext context = JAXBContext.newInstance(Materials.class);
            Unmarshaller unmarshaller = context.createUnmarshaller();

            Materials materials = (Materials) unmarshaller.unmarshal(new File(filePath));
            return materials.getMaterialList();
        } catch (JAXBException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void marshalMaterials(List<Material> materialList, String filePath) {
        try {
            JAXBContext context = JAXBContext.newInstance(Materials.class);
            Marshaller marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

            Materials materials = new Materials();
            materials.setMaterialList(materialList);
            marshaller.marshal(materials, new File(filePath));
        } catch (JAXBException e) {
            e.printStackTrace();
        }
    }

    @XmlRootElement(name = "materials")
    @XmlAccessorType(XmlAccessType.FIELD)
    static class Materials {
        @XmlElement(name = "material")
        private List<Material> materialList;

        // Getters and Setters
        public List<Material> getMaterialList() {
            return materialList;
        }

        public void setMaterialList(List<Material> materialList) {
            this.materialList = materialList;
        }
    }
}
