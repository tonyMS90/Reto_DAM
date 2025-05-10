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
@Setter
@Getter

@Entity
@Table(name = "usuarios")
public class Usuario implements Serializable {
    @Id
    private String username; //

    @Column
    private String nombre;

    @Column
    private String apellidos;

    @Column
    private String email;

    @Column
    private String password;

    @Column
    private Integer enabled; // 1: Activo, 0: Deshabilitado

    @Column
    private Date fechaRegistro;

    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL)
    private List<Solicitud> solicitudes;

    @ManyToMany
    @JoinTable(
            name = "usuarioperfil",
            joinColumns = @JoinColumn(name = "username"),
            inverseJoinColumns = @JoinColumn(name = "id_perfil")
    )
    private List<Perfil> perfiles;

    //CONSTRUCTOR SIN ID

    public Usuario(String nombre, String apellidos, String email, String password, Integer enabled, Date fechaRegistro, List<Solicitud> solicitudes, List<Perfil> perfiles) {
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.email = email;
        this.password = password;
        this.enabled = enabled;
        this.fechaRegistro = fechaRegistro;
        this.solicitudes = solicitudes;
        this.perfiles = perfiles;
    }
}
