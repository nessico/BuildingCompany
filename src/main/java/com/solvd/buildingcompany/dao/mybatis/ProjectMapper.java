package com.solvd.buildingcompany.dao.mybatis;

import com.solvd.buildingcompany.model.Project;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface ProjectMapper {

    @Select("SELECT * FROM Projects WHERE project_id = #{id}")
    Project getProjectById(@Param("id") int projectId);

    @Select("SELECT * FROM Projects")
    List<Project> getAllProjects();

    @Insert("INSERT INTO Projects (name, start_date, end_date, budget) VALUES (#{name}, #{startDate}, #{endDate}, #{budget})")
    void insertProject(Project project);

    @Update("UPDATE Projects SET name = #{name}, start_date = #{startDate}, end_date = #{endDate}, budget = #{budget} WHERE project_id = #{projectId}")
    void updateProject(Project project);

    @Delete("DELETE FROM Projects WHERE project_id = #{projectId}")
    void deleteProject(@Param("id") int projectId);
}
