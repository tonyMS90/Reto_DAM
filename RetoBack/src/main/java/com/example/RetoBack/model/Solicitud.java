package com.example.RetoBack.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter

@Entity
@Table(name = "solicitudes")
public class Solicitud implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_solicitud")
    private Long idSolicitud;

    @Column
    private Date fecha;

    @Column
    private String archivo;

    @Column
    private String comentarios;

    @Column
    private Integer estado; //0 - PENDIENTE, 1 - ADJUDICADA

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "id_vacante")
    private Vacante vacante;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "username")
    private Usuario usuario;

    //CONSTRUCTOR SIN ID


    public Solicitud(Date fecha, String archivo, String comentarios, Integer estado, Vacante vacante, Usuario usuario) {
        this.fecha = fecha;
        this.archivo = archivo;
        this.comentarios = comentarios;
        this.estado = estado;
        this.vacante = vacante;
        this.usuario = usuario;
    }
}
