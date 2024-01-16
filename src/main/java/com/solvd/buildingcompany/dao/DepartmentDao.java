package com.solvd.buildingcompany.dao;

import com.solvd.buildingcompany.model.Department;

import java.util.List;

public interface DepartmentDao {

    Department getDepartmentById(int departmentId);

    List<Department> getAllDepartments();

    void insertDepartment(Department department);

    void updateDepartment(Department department);

    void deleteDepartment(int departmentId);
}
