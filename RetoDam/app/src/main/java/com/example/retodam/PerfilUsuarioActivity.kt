package com.example.retodam

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class PerfilUsuarioActivity : AppCompatActivity() {

    private lateinit var textNombre: TextView
    private lateinit var textEmail: TextView
    private lateinit var textExperiencia: TextView
    private lateinit var textEducacion: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_perfil_usuario)

        textNombre = findViewById(R.id.textNombre)
        textEmail = findViewById(R.id.textEmail)
        textExperiencia = findViewById(R.id.textExperiencia)
        textEducacion = findViewById(R.id.textEducacion)

        val usuario = UsuarioSession.usuarioActual

        if (usuario != null) {
            textNombre.text = "Nombre: ${usuario.nombre}"
            textEmail.text = "Email: ${usuario.email}"
            textExperiencia.text = "Experiencia: ${usuario.experiencia}"
            textEducacion.text = "Educación: ${usuario.educacion}"
        }

        val toolbar = findViewById<androidx.appcompat.widget.Toolbar>(R.id.toolbarPerfil)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = "Perfil" // (opcional, puedes poner título)

    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

}

