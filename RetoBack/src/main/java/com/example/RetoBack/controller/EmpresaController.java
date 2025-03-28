package com.example.RetoBack.controller;

import com.example.RetoBack.model.Empresa;
import com.example.RetoBack.services.EmpresaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/empresa")
public class EmpresaController {
    @Autowired
    private EmpresaService empresaService;

    @PostMapping("/agregar")
    public ResponseEntity<Empresa> agregarEmpresa(@RequestBody Empresa empresa) {
        return new ResponseEntity<>(empresaService.addEmpresa(empresa), HttpStatus.OK);
    }

    @GetMapping("obternerEmpresas")
    public ResponseEntity<List<Empresa>> obtenerEmpresas() {
        List<Empresa> lista = empresaService.getAllEmpresas();
        return new ResponseEntity<>(lista, HttpStatus.OK);
    }

    @GetMapping("/obtenerEmpresaPorId")
    public ResponseEntity<Empresa> obtenerEmpresaPorId(@RequestParam int id) {
        Empresa empresa = empresaService.getEmpresaById(id);
        return new ResponseEntity<>(empresa, HttpStatus.OK);
    }

    @PutMapping("/actualizarEmpresa")
    public ResponseEntity<Empresa> actualizarEmpresa(@RequestParam int id, @RequestBody Empresa empresa) {
        Empresa empresaActualizada = empresaService.updateEmpresa(id, empresa);
        return new ResponseEntity<>(empresaActualizada, HttpStatus.OK);
    }

    @DeleteMapping("/eliminarEmpresa")
    public ResponseEntity<Void> eliminarEmpresa(@RequestParam int id) {
        empresaService.deleteEmpresa(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
