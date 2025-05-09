package com.example.retodam

data class Vacante(
    val id: String = "",
    val titulo: String = "",
    val empresa: String = "",
    val tipoContrato: String = "",
    val categoria: String = "",
    val estado: String = "", // Ej: "CREADA", "CERRADA"
    val descripcion: String = ""
)
