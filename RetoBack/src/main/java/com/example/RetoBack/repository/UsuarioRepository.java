package com.example.RetoBack.repository;

import com.example.RetoBack.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UsuarioRepository extends JpaRepository<Usuario, String> {
    List<Usuario> findByEnabled(Integer enabled); // Buscar usuarios habilitados o deshabilitados
}
