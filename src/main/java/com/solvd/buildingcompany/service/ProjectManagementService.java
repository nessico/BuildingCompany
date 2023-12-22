package com.solvd.buildingcompany.service;

import com.solvd.buildingcompany.dao.ProjectDao;
import com.solvd.buildingcompany.model.Project;

import java.util.Date;
import java.util.List;
import java.util.Objects;

public class ProjectManagementService {

    private final ProjectDao projectDao;


    public ProjectManagementService() {
        this.projectDao = new ProjectDao();
    }

    public Project getProjectById(int projectId) {
        validateProjectId(projectId);
        Project project = projectDao.getProjectById(projectId);
        if (project == null) {

            throw new IllegalStateException("Project not found for ID: " + projectId);
        }
        return project;
    }

    public List<Project> getAllProjects() {
        List<Project> projects = projectDao.getAllProjects();
        return projects;
    }

    public void addNewProject(Project project) {
        validateNewProject(project);
        projectDao.saveProject(project);
    }

    public void updateProject(Project project) {
        validateExistingProject(project);
        projectDao.updateProject(project);
        System.out.println("Project updated: " + project.getName());
    }

    public void deleteProject(int projectId) {
        validateProjectId(projectId);
        projectDao.deleteProject(projectId);

        System.out.println("Project deleted with ID: " + projectId);
    }

    private void validateNewProject(Project project) {
        Objects.requireNonNull(project, "Project cannot be null");
        if (project.getName() == null || project.getName().trim().isEmpty()) {
            throw new IllegalArgumentException("Project name cannot be empty");
        }
        if (project.getBudget() <= 0) {
            throw new IllegalArgumentException("Project budget must be positive");
        }
    }

    private void validateExistingProject(Project project) {
        validateNewProject(project);
        if (project.getId() <= 0) {
            throw new IllegalArgumentException("Existing project must have a valid ID");
        }
    }

    private void validateProjectId(int projectId) {
        if (projectId <= 0) {
            throw new IllegalArgumentException("Invalid project ID");
        }
    }
}
