package com.example.RetoBack.services;

import com.example.RetoBack.model.EstadoVacante;
import com.example.RetoBack.model.Vacante;

import java.util.List;

public interface VacanteService {
    Vacante addVacante(Vacante vacante);
    Vacante updateVacante(int id, Vacante vacante);
    void cancelVacante(int id);
    List<Vacante> getVacantesByEstado(EstadoVacante estado);
    List<Vacante> getVacantesDestacadas();
    Vacante getVacanteById(int id);
}
