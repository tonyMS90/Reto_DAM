package com.empleogestor.services;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DatabaseService {
    private String dbUrl;
    private String dbUser;
    private String dbPassword;

    public DatabaseService() {
        // Configuración de la base de datos (debería leerse de un archivo properties)
        this.dbUrl = "jdbc:mysql://localhost:3306/empleo_gestor";
        this.dbUser = "root";
        this.dbPassword = "";

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public Connection getConnection() throws SQLException {
        Properties props = new Properties();
        props.setProperty("user", dbUser);
        props.setProperty("password", dbPassword);
        props.setProperty("useSSL", "false");
        props.setProperty("autoReconnect", "true");

        return DriverManager.getConnection(dbUrl, props);
    }
}