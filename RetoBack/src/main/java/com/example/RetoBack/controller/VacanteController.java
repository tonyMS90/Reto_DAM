package com.example.RetoBack.controller;

import com.example.RetoBack.model.EstadoVacante;
import com.example.RetoBack.model.Vacante;
import com.example.RetoBack.services.VacanteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/vacantes")
public class VacanteController {
    @Autowired
    private VacanteService vacanteService;

    @PostMapping("/agregarVacante")
    public ResponseEntity<Vacante> agregarVacante(@RequestBody Vacante vacante) {

        return new ResponseEntity<>(vacanteService.addVacante(vacante), HttpStatus.OK);
    }

    @GetMapping("/obtenerVacantes")
    public ResponseEntity<List<Vacante>> obtenerVacantes() {
        List<Vacante> vacantes = vacanteService.getVacantesByEstado(EstadoVacante.CREADA);
        return new ResponseEntity<>(vacantes, HttpStatus.OK);
    }

    @GetMapping("/VacantesDestacadas")
    public ResponseEntity<List<Vacante>> obtenerVacantesDestacadas() {
        List<Vacante> destacadas = vacanteService.getVacantesDestacadas();
        return new ResponseEntity<>(destacadas, HttpStatus.OK);
    }

    @GetMapping("/VacantesPorId")
    public ResponseEntity<Vacante> obtenerVacantePorId(@RequestParam int id) {
        Vacante vacante = vacanteService.getVacanteById(id);
        return new ResponseEntity<>(vacante, HttpStatus.OK);
    }

    @PutMapping("/actualizarVacante")
    public ResponseEntity<Vacante> actualizarVacante(@RequestParam int id, @RequestBody Vacante vacante) {
        Vacante vacanteActualizada = vacanteService.updateVacante(id, vacante);
        return new ResponseEntity<>(vacanteActualizada, HttpStatus.OK);
    }

    @PutMapping("/cancelarVacante")
    public ResponseEntity<Void> cancelarVacante(@RequestParam int id) {
        vacanteService.cancelVacante(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
