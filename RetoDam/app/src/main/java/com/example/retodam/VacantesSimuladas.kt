package com.example.retodam

object VacantesSimuladas {
    val listaVacantes = listOf(
        Vacante(
            id = "v1",
            titulo = "Desarrollador Android",
            empresa = "Tech Solutions",
            tipoContrato = "Tiempo completo",
            categoria = "Tecnología",
            estado = "CREADA",
            descripcion = "Buscamos un desarrollador Android con experiencia en Kotlin."
        ),
        Vacante(
            id = "v2",
            titulo = "Diseñador UI/UX",
            empresa = "Creative Studio",
            tipoContrato = "Freelance",
            categoria = "Diseño",
            estado = "CREADA",
            descripcion = "Se busca diseñador UI/UX creativo y con experiencia en apps móviles."
        ),
        Vacante(
            id = "v3",
            titulo = "Administrador de Base de Datos",
            empresa = "DataCorp",
            tipoContrato = "Tiempo parcial",
            categoria = "TI",
            estado = "CERRADA",
            descripcion = "Administrar bases de datos en entorno empresarial."
        )
    )
}
