package com.example.retodam

data class Solicitud(
    val id: String = "",
    val idUsuario: String = "",
    val idVacante: String = "",
    val fechaPostulacion: String = "",
    val estado: String = "" // Ej: "Enviada", "Revisada", "Aceptada", "Rechazada"
)

