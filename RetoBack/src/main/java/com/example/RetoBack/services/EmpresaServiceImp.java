package com.example.RetoBack.services;

import com.example.RetoBack.model.Empresa;
import com.example.RetoBack.repository.EmpresaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmpresaServiceImp implements EmpresaService{

    @Autowired
    private EmpresaRepository empresaRepository;

    @Override
    public Empresa addEmpresa(Empresa empresa) {
        return empresaRepository.save(empresa);
    }

    @Override
    public Empresa updateEmpresa(int id, Empresa empresa) {
        Empresa existente = empresaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Empresa no encontrada"));
        existente.setRazonSocial(empresa.getRazonSocial());
        existente.setDireccion(empresa.getDireccion());
        existente.setPais(empresa.getPais());
        return empresaRepository.save(existente);
    }

    @Override
    public void deleteEmpresa(int id) {
        empresaRepository.deleteById(id);
    }

    @Override
    public List<Empresa> getAllEmpresas() {
        return empresaRepository.findAll();
    }

    @Override
    public Empresa getEmpresaById(int id) {
        return empresaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Empresa no encontrada"));
    }
}
