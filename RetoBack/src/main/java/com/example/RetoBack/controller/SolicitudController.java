package com.example.RetoBack.controller;

import com.example.RetoBack.model.Solicitud;
import com.example.RetoBack.services.SolicitudService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/solicitudes")
public class SolicitudController {
    @Autowired
    private SolicitudService solicitudService;

    @PostMapping("/agregar")
    public ResponseEntity<Solicitud> agregarSolicitud(@RequestBody Solicitud solicitud) {
        return new ResponseEntity<>(solicitudService.addSolicitud(solicitud), HttpStatus.OK);
    }

    @GetMapping("/usuario")
    public ResponseEntity<List<Solicitud>> obtenerSolicitudesPorUsuario(@RequestParam String username) {
        List<Solicitud> solicitudes = solicitudService.getSolicitudesByUsuario(username);
        return new ResponseEntity<>(solicitudes, HttpStatus.OK);
    }

    @GetMapping("/estado")
    public ResponseEntity<List<Solicitud>> obtenerSolicitudesPorEstado(@RequestParam Integer estado) {
        List<Solicitud> solicitudesPorEstado = solicitudService.getSolicitudesByEstado(estado);
        return new ResponseEntity<>(solicitudesPorEstado, HttpStatus.OK);
    }

    @PutMapping("/cancelar")
    public ResponseEntity<Void> cancelarSolicitud(@RequestParam int id) {
        solicitudService.cancelarSolicitud(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
