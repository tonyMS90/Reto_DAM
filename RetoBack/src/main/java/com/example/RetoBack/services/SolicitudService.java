package com.example.RetoBack.services;

import com.example.RetoBack.model.Solicitud;

import java.util.List;

public interface SolicitudService {
    Solicitud addSolicitud(Solicitud solicitud);
    List<Solicitud> getSolicitudesByUsuario(String username);
    void cancelarSolicitud(int id);
    List<Solicitud> getSolicitudesByEstado(Integer estado);
}
