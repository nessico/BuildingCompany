package com.solvd.buildingcompany.service;

import com.solvd.buildingcompany.model.Project;

import java.util.List;

public interface ProjectManagementService {

    Project getProjectById(int projectId);

    List<Project> getAllProjects();

    void addNewProject(Project project);

    void updateProject(Project project);


}
