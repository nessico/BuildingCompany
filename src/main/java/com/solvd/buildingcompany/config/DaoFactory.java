package com.solvd.buildingcompany.config;

import com.solvd.buildingcompany.dao.impl.ClientDaoImpl;
import com.solvd.buildingcompany.dao.impl.EmployeeDaoImpl;
import com.solvd.buildingcompany.dao.impl.ProjectDaoImpl;
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

    public static ClientDaoImpl getClientDao() {
        if (sqlSessionFactory != null) {
            SqlSession session = sqlSessionFactory.openSession();
            return (ClientDaoImpl) session.getMapper(ClientMapper.class);
        } else {
            return new ClientDaoImpl();
        }
    }

    public static EmployeeDaoImpl getEmployeeDao() {
        if (sqlSessionFactory != null) {
            SqlSession session = sqlSessionFactory.openSession();
            return (EmployeeDaoImpl) session.getMapper(EmployeeMapper.class);
        } else {
            return new EmployeeDaoImpl();
        }
    }

    public static ProjectDaoImpl getProjectDao() {
        if (sqlSessionFactory != null) {
            SqlSession session = sqlSessionFactory.openSession();
            return (ProjectDaoImpl) session.getMapper(ProjectMapper.class);
        } else {
            return new ProjectDaoImpl();
        }
    }

    // Similar methods for other DAOs
}
