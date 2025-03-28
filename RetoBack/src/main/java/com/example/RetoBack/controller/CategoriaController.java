package com.example.RetoBack.controller;

import com.example.RetoBack.model.Categoria;
import com.example.RetoBack.services.CategoriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/categoria")
public class CategoriaController {
    @Autowired
    private CategoriaService categoriaService;

    @PostMapping("/agregarCategoria")
    public ResponseEntity<Categoria> agregarCategoria(@RequestBody Categoria categoria) {
        return new ResponseEntity<>(categoriaService.addCategoria(categoria), HttpStatus.OK);
    }

    @GetMapping("/obtener")
    public ResponseEntity<List<Categoria>> obtenerCategorias() {
        List<Categoria> categorias = categoriaService.getAllCategorias();
        return new ResponseEntity<>(categorias, HttpStatus.OK);
    }
}
