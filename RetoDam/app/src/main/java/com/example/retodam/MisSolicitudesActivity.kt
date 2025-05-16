package com.example.retodam

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MisSolicitudesActivity : AppCompatActivity() {

    private lateinit var recyclerSolicitudes: RecyclerView
    private lateinit var solicitudAdapter: SolicitudAdapter

    private var solicitudesUsuario = mutableListOf<Solicitud>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mis_solicitudes)

        recyclerSolicitudes = findViewById(R.id.recyclerSolicitudes)

        // Cargar solicitudes simuladas
        val idUsuarioActual = UsuarioSession.usuarioActual?.id ?: ""

        solicitudesUsuario = SolicitudesSimuladas.listaSolicitudes
            .filter { it.idUsuario == idUsuarioActual }
            .toMutableList()

        solicitudAdapter = SolicitudAdapter(solicitudesUsuario) { solicitud ->
            cancelarSolicitud(solicitud)
        }

        recyclerSolicitudes.layoutManager = LinearLayoutManager(this)
        recyclerSolicitudes.adapter = solicitudAdapter

        val toolbar = findViewById<androidx.appcompat.widget.Toolbar>(R.id.toolbarSolicitudes)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = "Buscar Solicitudes" // (opcional, puedes poner título)

    }

    private fun cancelarSolicitud(solicitud: Solicitud) {
        SolicitudesSimuladas.listaSolicitudes.removeIf { it.id == solicitud.id }
        solicitudesUsuario.remove(solicitud)
        solicitudAdapter.notifyDataSetChanged()
        Toast.makeText(this, "Solicitud cancelada.", Toast.LENGTH_SHORT).show()
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

}

