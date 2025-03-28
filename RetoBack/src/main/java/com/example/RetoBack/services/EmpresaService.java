package com.example.RetoBack.services;

import com.example.RetoBack.model.Empresa;

import java.util.List;

public interface EmpresaService {
    Empresa addEmpresa(Empresa empresa);
    Empresa updateEmpresa(int id, Empresa empresa);
    void deleteEmpresa(int id);
    List<Empresa> getAllEmpresas();
    Empresa getEmpresaById(int id);
}
