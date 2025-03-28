package com.example.RetoBack.services;

import com.example.RetoBack.model.Solicitud;
import com.example.RetoBack.repository.SolicitudRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class SolicitudServiceImp implements SolicitudService{

    @Autowired
    private SolicitudRepository solicitudRepository;

    @Override
    public Solicitud addSolicitud(Solicitud solicitud) {
        solicitud.setEstado(0); // Estado inicial "Pendiente"
        solicitud.setFecha(new Date());
        return solicitudRepository.save(solicitud);
    }

    @Override
    public List<Solicitud> getSolicitudesByUsuario(String username) {
        return solicitudRepository.findByUsuario_Username(username);
    }

    @Override
    public void cancelarSolicitud(int id) {
        Solicitud solicitud = solicitudRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Solicitud no encontrada"));
        solicitud.setEstado(-1); // Marcamos como "Cancelada"
        solicitudRepository.save(solicitud);
    }

    @Override
    public List<Solicitud> getSolicitudesByEstado(Integer estado) {
        return solicitudRepository.findByEstado(estado);
    }
}
