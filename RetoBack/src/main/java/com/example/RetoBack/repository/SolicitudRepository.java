package com.example.RetoBack.repository;

import com.example.RetoBack.model.Solicitud;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SolicitudRepository extends JpaRepository<Solicitud, Integer> {
    List<Solicitud> findByEstado(Integer estado); // Buscar por estado de la solicitud
    List<Solicitud> findByUsuario_Username(String username); // Buscar solicitudes realizadas por un usuario
}

