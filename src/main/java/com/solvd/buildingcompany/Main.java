package com.solvd.buildingcompany;

import com.solvd.buildingcompany.model.Equipment;
import com.solvd.buildingcompany.model.Material;
import com.solvd.buildingcompany.model.Project;
import com.solvd.buildingcompany.model.Supplier;
import com.solvd.buildingcompany.service.ProjectManagementService;
import com.solvd.buildingcompany.util.EquipmentJsonUtil;
import com.solvd.buildingcompany.util.parser.MaterialJAXBParser;
import com.solvd.buildingcompany.util.parser.SupplierSAXParserUtil;

import java.util.Date;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        ProjectManagementService service = new ProjectManagementService();

        // Adding a new project
        Project newProject = new Project();
        newProject.setName("New Building Project");
        newProject.setStartDate(new Date());
        newProject.setEndDate(new Date());
        newProject.setBudget(50000.0);
        service.addNewProject(newProject);
        System.out.println("Added new project: " + newProject.getName());

        // Fetch and display project 1
        Project fetchedProject = service.getProjectById(1);
        if (fetchedProject != null) {
            System.out.println("Fetched Project: " + fetchedProject.getName());
        } else {
            System.out.println("Project not found.");
        }

        // Listing all projects
        service.getAllProjects().forEach(project ->
                System.out.println("Project: " + project.getName() + ", Budget: " + project.getBudget())
        );


        String xmlSupplierFilePath = "src/main/resources/suppliers.xml";

        // Parse suppliers from XML file using SAX parser
        List<Supplier> suppliers = SupplierSAXParserUtil.parseSuppliers(xmlSupplierFilePath);

        // Print out the parsed suppliers
        for (Supplier supplier : suppliers) {
            System.out.println(supplier);
        }


        MaterialJAXBParser parser = new MaterialJAXBParser();
        String xmlMaterialFilePath = "src/main/resources/materials.xml";

        // Unmarshalling (Reading)
        List<Material> materialList = parser.unmarshalMaterials(xmlMaterialFilePath);
        for (Material material : materialList) {
            System.out.println(material);
        }

        // Marshalling (Writing)
        // modify material or create a new list of materials, then write it back to XML
        parser.marshalMaterials(materialList, xmlMaterialFilePath);

        String jsonFilePath = "src/main/resources/equipment.json";

        // Read equipment from JSON file
        List<Equipment> equipment = EquipmentJsonUtil.readEquipment(jsonFilePath);
        equipment.forEach(System.out::println);

        // Modify the equipment list or add new equipment
        equipment.add(new Equipment(3, "Loader", new Date()));

        // Write equipment back to JSON file
        EquipmentJsonUtil.writeEquipment(jsonFilePath, equipment);

    }

}
