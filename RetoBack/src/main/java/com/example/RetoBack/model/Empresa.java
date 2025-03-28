package com.example.RetoBack.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter

@Entity
@Table(name = "empresas")
public class Empresa  implements Serializable {

    @Id
    @Column(name = "id_empresa")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idEmpresa;

    @Column(name = "razon_social")
    private String razonSocial;

    @Column(name = "direccion_fiscal")
    private String direccion;

    @Column
    private String pais;

    //CONSTRUCTOR SIN ID


    public Empresa(String razonSocial, String direccion, String pais) {
        this.razonSocial = razonSocial;
        this.direccion = direccion;
        this.pais = pais;
    }
}
