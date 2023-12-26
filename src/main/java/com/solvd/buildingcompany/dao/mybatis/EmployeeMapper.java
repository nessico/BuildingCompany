package com.solvd.buildingcompany.dao.mybatis;

import com.solvd.buildingcompany.model.Employee;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface EmployeeMapper {

    @Select("SELECT * FROM Employees WHERE employee_id = #{id}")
    Employee getEmployeeById(@Param("id") int employeeId);

    @Select("SELECT * FROM Employees")
    List<Employee> getAllEmployees();

    @Insert("INSERT INTO Employees (name, department_id) VALUES (#{name}, #{departmentId})")
    void insertEmployee(Employee employee);

    @Update("UPDATE Employees SET name = #{name}, department_id = #{departmentId} WHERE employee_id = #{employeeId}")
    void updateEmployee(Employee employee);

    @Delete("DELETE FROM Employees WHERE employee_id = #{employeeId}")
    void deleteEmployee(@Param("id") int employeeId);
}
