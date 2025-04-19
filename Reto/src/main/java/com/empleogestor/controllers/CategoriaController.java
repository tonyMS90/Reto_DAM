package com.empleogestor.controllers;

import com.empleogestor.models.Categoria;
import com.empleogestor.services.DatabaseService;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CategoriaController {
    private DatabaseService databaseService;

    public CategoriaController() {
        databaseService = new DatabaseService();
    }

    public List<Categoria> getAllCategorias() {
        List<Categoria> categorias = new ArrayList<>();
        String query = "SELECT * FROM categorias";

        try (Connection conn = databaseService.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            while (rs.next()) {
                Categoria categoria = new Categoria();
                categoria.setId(rs.getInt("id"));
                categoria.setNombre(rs.getString("nombre"));
                categoria.setDescripcion(rs.getString("descripcion"));

                categorias.add(categoria);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return categorias;
    }

    public Categoria getCategoriaById(int id) {
        Categoria categoria = null;
        String query = "SELECT * FROM categorias WHERE id = ?";

        try (Connection conn = databaseService.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                categoria = new Categoria();
                categoria.setId(rs.getInt("id"));
                categoria.setNombre(rs.getString("nombre"));
                categoria.setDescripcion(rs.getString("descripcion"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return categoria;
    }

    public boolean addCategoria(Categoria categoria) {
        String query = "INSERT INTO categorias (nombre, descripcion) VALUES (?, ?)";

        try (Connection conn = databaseService.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {

            pstmt.setString(1, categoria.getNombre());
            pstmt.setString(2, categoria.getDescripcion());

            int affectedRows = pstmt.executeUpdate();

            if (affectedRows > 0) {
                try (ResultSet generatedKeys = pstmt.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        categoria.setId(generatedKeys.getInt(1));
                    }
                }
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    public boolean updateCategoria(Categoria categoria) {
        String query = "UPDATE categorias SET nombre = ?, descripcion = ? WHERE id = ?";

        try (Connection conn = databaseService.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setString(1, categoria.getNombre());
            pstmt.setString(2, categoria.getDescripcion());
            pstmt.setInt(3, categoria.getId());

            int affectedRows = pstmt.executeUpdate();
            return affectedRows > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    public boolean deleteCategoria(int id) {
        String query = "DELETE FROM categorias WHERE id = ?";

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