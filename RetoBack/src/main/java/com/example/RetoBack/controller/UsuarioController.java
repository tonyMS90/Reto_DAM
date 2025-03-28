package com.example.RetoBack.controller;

import com.example.RetoBack.model.Usuario;
import com.example.RetoBack.services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {
    @Autowired
    private UsuarioService usuarioService;

    @PostMapping("/agregarUsuario")
    public ResponseEntity<Usuario> agregarUsuario(@RequestBody Usuario usuario) {
        return new ResponseEntity<>(usuarioService.addUsuario(usuario), HttpStatus.CREATED);
    }

    @GetMapping("/obtenerUsuario")
    public ResponseEntity<List<Usuario>> obtenerUsuarios() {
        List<Usuario> usuarios = usuarioService.getAllUsuarios();
        return new ResponseEntity<>(usuarios, HttpStatus.OK);
    }

    @PutMapping("/actualizarUsuario")
    public ResponseEntity<Usuario> actualizarUsuario(@RequestParam String username, @RequestBody Usuario usuario) {
        return new ResponseEntity<>(usuarioService.updateUsuario(username, usuario), HttpStatus.OK);
    }

    @PutMapping("/deshabilitarUsuario")
    public ResponseEntity<Usuario> deshabilitarUsuario(@RequestParam String username) {
        return new ResponseEntity<>(usuarioService.disableUsuario(username), HttpStatus.OK);
    }
}
