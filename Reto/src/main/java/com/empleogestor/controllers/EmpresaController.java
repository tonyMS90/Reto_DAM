package com.empleogestor.controllers;

import com.empleogestor.models.Empresa;
import com.empleogestor.services.DatabaseService;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EmpresaController {
    private DatabaseService databaseService;

    public EmpresaController() {
        databaseService = new DatabaseService();
    }

    public List<Empresa> getAllEmpresas() {
        List<Empresa> empresas = new ArrayList<>();
        String query = "SELECT * FROM empresas";

        try (Connection conn = databaseService.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            while (rs.next()) {
                Empresa empresa = new Empresa();
                empresa.setId(rs.getInt("id"));
                empresa.setNombre(rs.getString("nombre"));
                empresa.setEmail(rs.getString("email"));
                empresa.setTelefono(rs.getString("telefono"));
                empresa.setDireccion(rs.getString("direccion"));

                empresas.add(empresa);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return empresas;
    }

    public Empresa getEmpresaById(int id) {
        Empresa empresa = null;
        String query = "SELECT * FROM empresas WHERE id = ?";

        try (Connection conn = databaseService.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                empresa = new Empresa();
                empresa.setId(rs.getInt("id"));
                empresa.setNombre(rs.getString("nombre"));
                empresa.setEmail(rs.getString("email"));
                empresa.setTelefono(rs.getString("telefono"));
                empresa.setDireccion(rs.getString("direccion"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return empresa;
    }

    public boolean addEmpresa(Empresa empresa) {
        String query = "INSERT INTO empresas (nombre, email, telefono, direccion) VALUES (?, ?, ?, ?)";

        try (Connection conn = databaseService.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {

            pstmt.setString(1, empresa.getNombre());
            pstmt.setString(2, empresa.getEmail());
            pstmt.setString(3, empresa.getTelefono());
            pstmt.setString(4, empresa.getDireccion());

            int affectedRows = pstmt.executeUpdate();

            if (affectedRows > 0) {
                try (ResultSet generatedKeys = pstmt.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        empresa.setId(generatedKeys.getInt(1));
                    }
                }
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    public boolean updateEmpresa(Empresa empresa) {
        String query = "UPDATE empresas SET nombre = ?, email = ?, telefono = ?, direccion = ? WHERE id = ?";

        try (Connection conn = databaseService.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setString(1, empresa.getNombre());
            pstmt.setString(2, empresa.getEmail());
            pstmt.setString(3, empresa.getTelefono());
            pstmt.setString(4, empresa.getDireccion());
            pstmt.setInt(5, empresa.getId());

            int affectedRows = pstmt.executeUpdate();
            return affectedRows > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    public boolean deleteEmpresa(int id) {
        String query = "DELETE FROM empresas WHERE id = ?";

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