package com.example.retodam

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class DetalleVacanteActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detalle_vacante)

        val titulo = findViewById<TextView>(R.id.textTituloDetalle)
        val empresa = findViewById<TextView>(R.id.textEmpresaDetalle)
        val tipoContrato = findViewById<TextView>(R.id.textTipoContratoDetalle)
        val descripcion = findViewById<TextView>(R.id.textDescripcionDetalle)

        // Recibir datos enviados
        val bundle = intent.extras
        if (bundle != null) {
            titulo.text = bundle.getString("titulo")
            empresa.text = bundle.getString("empresa")
            tipoContrato.text = bundle.getString("tipoContrato")
            descripcion.text = bundle.getString("descripcion")
        }

        val buttonPostularse = findViewById<Button>(R.id.buttonPostularse)

        buttonPostularse.setOnClickListener {
            val idUsuario = UsuarioSession.usuarioActual?.id ?: return@setOnClickListener
            val idVacante = bundle?.getString("idVacante") ?: return@setOnClickListener

            val solicitud = Solicitud(
                id = "solicitud_${System.currentTimeMillis()}",
                idUsuario = idUsuario,
                idVacante = idVacante,
                estado = "Enviada"
            )

            SolicitudesSimuladas.listaSolicitudes.add(solicitud)

            Toast.makeText(this, "¡Te has postulado a esta vacante!", Toast.LENGTH_LONG).show()
        }

        val toolbar = findViewById<androidx.appcompat.widget.Toolbar>(R.id.toolbarDetalle)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = "Detalles Vacante" // (opcional, puedes poner título)

    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

}