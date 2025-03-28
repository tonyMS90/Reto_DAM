package com.example.RetoBack.services;

import com.example.RetoBack.model.Categoria;

import java.util.List;

public interface CategoriaService {
    Categoria addCategoria(Categoria categoria);
    List<Categoria> getAllCategorias();
}
