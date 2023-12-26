package com.solvd.buildingcompany.model;

import java.util.Date;

public class Inspection {
    private int inspectionId;
    private int projectId;
    private Date date;
    private String report;

    public Inspection() {
        // Default constructor
    }

    public Inspection(int inspectionId, int projectId, Date date, String report) {
        this.inspectionId = inspectionId;
        this.projectId = projectId;
        this.date = date;
        this.report = report;
    }

    // Getters and Setters
    public int getInspectionId() {
        return inspectionId;
    }

    public void setInspectionId(int inspectionId) {
        this.inspectionId = inspectionId;
    }

    public int getProjectId() {
        return projectId;
    }

    public void setProjectId(int projectId) {
        this.projectId = projectId;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getReport() {
        return report;
    }

    public void setReport(String report) {
        this.report = report;
    }

    @Override
    public String toString() {
        return "Inspection{" +
                "inspectionId=" + inspectionId +
                ", projectId=" + projectId +
                ", date=" + date +
                ", report='" + report + '\'' +
                '}';
    }
}
