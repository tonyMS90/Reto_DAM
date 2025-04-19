package com.empleogestor.controllers;

import com.empleogestor.models.Usuario;
import com.empleogestor.services.DatabaseService;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UsuarioController {
    private DatabaseService databaseService;

    public UsuarioController() {
        databaseService = new DatabaseService();
    }

    public List<Usuario> getAllUsuarios() {
        List<Usuario> usuarios = new ArrayList<>();
        String query = "SELECT * FROM usuarios";

        try (Connection conn = databaseService.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            while (rs.next()) {
                Usuario usuario = new Usuario();
                usuario.setId(rs.getInt("id"));
                usuario.setNombre(rs.getString("nombre"));
                usuario.setEmail(rs.getString("email"));
                usuario.setTelefono(rs.getString("telefono"));
                usuario.setDireccion(rs.getString("direccion"));
                usuario.setEnabled(rs.getBoolean("enabled"));

                usuarios.add(usuario);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return usuarios;
    }

    public Usuario getUsuarioById(int id) {
        Usuario usuario = null;
        String query = "SELECT * FROM usuarios WHERE id = ?";

        try (Connection conn = databaseService.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                usuario = new Usuario();
                usuario.setId(rs.getInt("id"));
                usuario.setNombre(rs.getString("nombre"));
                usuario.setEmail(rs.getString("email"));
                usuario.setTelefono(rs.getString("telefono"));
                usuario.setDireccion(rs.getString("direccion"));
                usuario.setEnabled(rs.getBoolean("enabled"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return usuario;
    }

    public boolean toggleUsuarioStatus(int id) {
        Usuario usuario = getUsuarioById(id);
        if (usuario == null) return false;

        String query = "UPDATE usuarios SET enabled = ? WHERE id = ?";

        try (Connection conn = databaseService.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setBoolean(1, !usuario.isEnabled());
            pstmt.setInt(2, id);

            int affectedRows = pstmt.executeUpdate();
            return affectedRows > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }
}