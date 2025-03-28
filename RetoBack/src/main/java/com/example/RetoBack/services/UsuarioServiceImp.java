package com.example.RetoBack.services;

import com.example.RetoBack.model.Usuario;
import com.example.RetoBack.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class UsuarioServiceImp implements UsuarioService{

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    public Usuario addUsuario(Usuario usuario) {
        usuario.setFechaRegistro(new Date());
        usuario.setEnabled(1); // Habilitado por defecto
        return usuarioRepository.save(usuario);
    }

    @Override
    public Usuario updateUsuario(String username, Usuario usuario) {
        Usuario existente = usuarioRepository.findById(username)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
        existente.setNombre(usuario.getNombre());
        existente.setApellidos(usuario.getApellidos());
        existente.setEmail(usuario.getEmail());
        existente.setPassword(usuario.getPassword());
        return usuarioRepository.save(existente);
    }

    @Override
    public Usuario disableUsuario(String username) {
        Usuario usuario = usuarioRepository.findById(username)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
        usuario.setEnabled(0); // Deshabilitar
        return usuarioRepository.save(usuario);
    }

    @Override
    public List<Usuario> getAllUsuarios() {
        return usuarioRepository.findAll();
    }
}
