package com.solvd.buildingcompany.dao;

import com.solvd.buildingcompany.model.Inspection;

import java.util.List;

public interface InspectionDao {

    Inspection getInspectionById(int inspectionId);

    List<Inspection> getAllInspections();

    void insertInspection(Inspection inspection);

    void updateInspection(Inspection inspection);

    void deleteInspection(int inspectionId);

}
