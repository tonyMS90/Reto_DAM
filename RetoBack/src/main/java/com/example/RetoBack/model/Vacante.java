package com.example.RetoBack.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter

@Entity
@Table(name = "vacantes")
public class Vacante implements Serializable {
    @Id
    @Column(name = "id_vacante")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idVacante;

    @Column
    private String nombre;

    @Column
    private String descripcion;

    @Column
    private Date fecha;

    @Column
    private double salario;

    @Enumerated(EnumType.STRING) //creo un enum (creada, cancelada, asignada)
    private EstadoVacante estatus;

    @Column
    private boolean destacado;

    @Column
    private String imagen;

    @Column
    private String detalles;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "id_empresa")
    private Empresa empresa;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "id_categoria")
    private Categoria categoria;

    @OneToMany(mappedBy = "vacante", cascade = CascadeType.ALL)
    private List<Solicitud> solicitudes;

    //CONSTRUCTOR SIN ID


    public Vacante(String nombre, String descripcion, Date fecha, double salario, EstadoVacante estatus, boolean destacado, String imagen, String detalles, Empresa empresa, Categoria categoria, List<Solicitud> solicitudes) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.fecha = fecha;
        this.salario = salario;
        this.estatus = estatus;
        this.destacado = destacado;
        this.imagen = imagen;
        this.detalles = detalles;
        this.empresa = empresa;
        this.categoria = categoria;
        this.solicitudes = solicitudes;
    }

}
