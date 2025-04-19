package com.empleogestor.controllers;

import com.empleogestor.models.Administrador;
import com.empleogestor.services.DatabaseService;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AdminController {
    private DatabaseService databaseService;

    public AdminController() {
        databaseService = new DatabaseService();
    }

    public boolean authenticate(String username, String password) {
        String query = "SELECT * FROM administradores WHERE username = ? AND password = ?";

        try (Connection conn = databaseService.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setString(1, username);
            pstmt.setString(2, password);

            ResultSet rs = pstmt.executeQuery();
            return rs.next();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    public List<Administrador> getAllAdmins() {
        List<Administrador> admins = new ArrayList<>();
        String query = "SELECT * FROM administradores";

        try (Connection conn = databaseService.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            while (rs.next()) {
                Administrador admin = new Administrador();
                admin.setId(rs.getInt("id"));
                admin.setUsername(rs.getString("username"));
                admin.setNombre(rs.getString("nombre"));
                admin.setEmail(rs.getString("email"));

                admins.add(admin);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return admins;
    }

    public boolean addAdmin(Administrador admin) {
        String query = "INSERT INTO administradores (username, password, nombre, email) VALUES (?, ?, ?, ?)";

        try (Connection conn = databaseService.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {

            pstmt.setString(1, admin.getUsername());
            pstmt.setString(2, admin.getPassword());
            pstmt.setString(3, admin.getNombre());
            pstmt.setString(4, admin.getEmail());

            int affectedRows = pstmt.executeUpdate();

            if (affectedRows > 0) {
                try (ResultSet generatedKeys = pstmt.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        admin.setId(generatedKeys.getInt(1));
                    }
                }
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    public boolean updateAdmin(Administrador admin) {
        String query = "UPDATE administradores SET username = ?, nombre = ?, email = ? WHERE id = ?";

        try (Connection conn = databaseService.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setString(1, admin.getUsername());
            pstmt.setString(2, admin.getNombre());
            pstmt.setString(3, admin.getEmail());
            pstmt.setInt(4, admin.getId());

            int affectedRows = pstmt.executeUpdate();
            return affectedRows > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    public boolean deleteAdmin(int id) {
        String query = "DELETE FROM administradores WHERE id = ?";

        try (Connection conn = databaseService.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setInt(1, id);
            int affectedRows = pstmt.executeUpdate();
            return affectedRows > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }
}