package com.solvd.buildingcompany.util;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionPool {
    private static final HikariDataSource ds;

    static {
        try (InputStream input = ConnectionPool.class
                .getClassLoader().getResourceAsStream("database.properties")) {
            Properties prop = new Properties();
            if (input == null) {
                throw new IllegalStateException("Sorry, unable to find database.properties");
            }
            prop.load(input);

            // read credentials from properties
            HikariConfig config = new HikariConfig();
            config.setJdbcUrl(prop.getProperty("jdbc.url"));
            config.setUsername(prop.getProperty("jdbc.username"));
            config.setPassword(prop.getProperty("jdbc.password"));


            ds = new HikariDataSource(config);
        } catch (IOException ex) {
            throw new RuntimeException("Error reading database properties", ex);
        }
    }

    public static Connection getConnection() throws SQLException {
        return ds.getConnection();
    }

    private ConnectionPool() {}
}
