package com.example.retodam

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class HomeActivity : AppCompatActivity() {

    private lateinit var buttonBuscarVacantes: Button
    private lateinit var buttonMisSolicitudes: Button
    private lateinit var buttonPerfil: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        buttonBuscarVacantes = findViewById(R.id.buttonBuscarVacantes)
        buttonMisSolicitudes = findViewById(R.id.buttonMisSolicitudes)
        buttonPerfil = findViewById(R.id.buttonPerfil)

        buttonBuscarVacantes.setOnClickListener {
            startActivity(Intent(this, BuscarVacantesActivity::class.java))
        }

        buttonMisSolicitudes.setOnClickListener {
            startActivity(Intent(this, MisSolicitudesActivity::class.java))
        }

        buttonPerfil.setOnClickListener {
            startActivity(Intent(this, PerfilUsuarioActivity::class.java))
        }

        val toolbar = findViewById<androidx.appcompat.widget.Toolbar>(R.id.toolbarHome)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = "Home" // (opcional, puedes poner título)

    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

}
