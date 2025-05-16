package com.example.retodam

data class Usuario(
    val id: String = "",
    val nombre: String = "",
    val email: String = "",
    val experiencia: String = "",
    val educacion: String = "",
    val cvUrl: String = "" // URL del CV subido (opcional)
)
