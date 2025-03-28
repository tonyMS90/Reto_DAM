package com.example.RetoBack.services;

import com.example.RetoBack.model.EstadoVacante;
import com.example.RetoBack.model.Vacante;
import com.example.RetoBack.repository.VacanteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VacanteServiceImp implements VacanteService{

    @Autowired
    private VacanteRepository vacanteRepository;

    @Override
    public Vacante addVacante(Vacante vacante) {
        vacante.setEstatus(EstadoVacante.CREADA); // Estado inicial
        return vacanteRepository.save(vacante);
    }

    @Override
    public Vacante updateVacante(int id, Vacante vacante) {
        Vacante existente = vacanteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Vacante no encontrada"));
        existente.setNombre(vacante.getNombre());
        existente.setDescripcion(vacante.getDescripcion());
        existente.setDetalles(vacante.getDetalles());
        existente.setImagen(vacante.getImagen());
        existente.setSalario(vacante.getSalario());
        existente.setEstatus(vacante.getEstatus());
        existente.setDestacado(vacante.isDestacado());
        return vacanteRepository.save(existente);
    }

    @Override
    public void cancelVacante(int id) {
        Vacante vacante = vacanteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Vacante no encontrada"));
        vacante.setEstatus(EstadoVacante.CANCELADA);
        vacanteRepository.save(vacante);

    }

    @Override
    public List<Vacante> getVacantesByEstado(EstadoVacante estado) {
        return vacanteRepository.findByEstado(estado);
    }

    @Override
    public List<Vacante> getVacantesDestacadas() {
        return vacanteRepository.findByDestacado(true);
    }

    @Override
    public Vacante getVacanteById(int id) {
        return vacanteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Vacante no encontrada"));
    }
}
