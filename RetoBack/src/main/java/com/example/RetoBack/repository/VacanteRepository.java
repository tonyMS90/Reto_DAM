package com.example.RetoBack.repository;

import com.example.RetoBack.model.EstadoVacante;
import com.example.RetoBack.model.Vacante;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface VacanteRepository extends JpaRepository<Vacante, Integer> {
    List<Vacante> findByEstado(EstadoVacante estado); // Buscar por estado (CREADA, CANCELADA, ASIGNADA)
    List<Vacante> findByDestacado(Boolean destacado); // Buscar vacantes destacadas
}
