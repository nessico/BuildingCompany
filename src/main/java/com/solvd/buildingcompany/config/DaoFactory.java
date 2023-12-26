package com.solvd.buildingcompany.config;

import com.solvd.buildingcompany.dao.ClientDao;
import com.solvd.buildingcompany.dao.EmployeeDao;
import com.solvd.buildingcompany.dao.ProjectDao;
import com.solvd.buildingcompany.dao.mybatis.ClientMapper;
import com.solvd.buildingcompany.dao.mybatis.EmployeeMapper;
import com.solvd.buildingcompany.dao.mybatis.ProjectMapper;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import java.io.InputStream;
import java.util.Properties;

public class DaoFactory {

    private static final Properties properties = new Properties();
    private static SqlSessionFactory sqlSessionFactory;

    static {
        try (InputStream input = DaoFactory.class.getClassLoader().getResourceAsStream("application.properties")) {
            properties.load(input);
            if (Boolean.parseBoolean(properties.getProperty("use.mybatis"))) {
                InputStream inputStream = Resources.getResourceAsStream("mybatis-config.xml");
                sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
            }
        } catch (Exception e) {
            throw new RuntimeException("Error initializing DaoFactory", e);
        }
    }

    public static ClientDao getClientDao() {
        if (sqlSessionFactory != null) {
            SqlSession session = sqlSessionFactory.openSession();
            return (ClientDao) session.getMapper(ClientMapper.class);
        } else {
            return new ClientDao();
        }
    }

    public static EmployeeDao getEmployeeDao() {
        if (sqlSessionFactory != null) {
            SqlSession session = sqlSessionFactory.openSession();
            return (EmployeeDao) session.getMapper(EmployeeMapper.class);
        } else {
            return new EmployeeDao();
        }
    }

    public static ProjectDao getProjectDao() {
        if (sqlSessionFactory != null) {
            SqlSession session = sqlSessionFactory.openSession();
            return (ProjectDao) session.getMapper(ProjectMapper.class);
        } else {
            return new ProjectDao();
        }
    }

    // Similar methods for other DAOs
}
