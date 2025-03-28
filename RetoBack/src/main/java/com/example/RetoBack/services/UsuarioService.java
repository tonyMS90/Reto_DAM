package com.example.RetoBack.services;

import com.example.RetoBack.model.Usuario;

import java.util.List;

public interface UsuarioService {
    Usuario addUsuario(Usuario usuario);
    Usuario updateUsuario(String username, Usuario usuario);
    Usuario disableUsuario(String username);
    List<Usuario> getAllUsuarios();
}
