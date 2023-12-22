package com.solvd.buildingcompany.main;

import com.solvd.buildingcompany.model.Project;
import com.solvd.buildingcompany.service.ProjectManagementService;

import java.util.Date;

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
    }
}
