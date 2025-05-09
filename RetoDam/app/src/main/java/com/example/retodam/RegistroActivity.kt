package com.example.retodam

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class RegistroActivity : AppCompatActivity() {

    private lateinit var nombre: EditText
    private lateinit var email: EditText
    private lateinit var password: EditText
    private lateinit var experiencia: EditText
    private lateinit var educacion: EditText
    private lateinit var buttonRegistrar: Button
    private lateinit var cv: EditText


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registro)

        nombre = findViewById(R.id.editTextNombre)
        email = findViewById(R.id.editTextEmail)
        password = findViewById(R.id.editTextPassword)
        experiencia = findViewById(R.id.editTextExperiencia)
        educacion = findViewById(R.id.editTextEducacion)
        buttonRegistrar = findViewById(R.id.buttonRegistrar)
        cv = findViewById(R.id.editTextCV)


        buttonRegistrar.setOnClickListener {
            val nombreText = nombre.text.toString()
            val emailText = email.text.toString()
            val passwordText = password.text.toString()
            val experienciaText = experiencia.text.toString()
            val educacionText = educacion.text.toString()
            val cvText = cv.text.toString()

            // Validaciones
            if (nombreText.isBlank() || emailText.isBlank() || passwordText.isBlank() ||
                experienciaText.isBlank() || educacionText.isBlank() || cvText.isBlank()) {

                Toast.makeText(this, "Por favor completa todos los campos.", Toast.LENGTH_LONG).show()
            } else {
                // Guardar usuario en sesión simulada
                val nuevoUsuario = Usuario(
                    id = "user_${System.currentTimeMillis()}",
                    nombre = nombreText,
                    email = emailText,
                    experiencia = experienciaText,
                    educacion = educacionText
                )

                UsuarioSession.usuarioActual = nuevoUsuario

                Toast.makeText(this, "Registro exitoso. ¡Bienvenido!", Toast.LENGTH_SHORT).show()

                startActivity(Intent(this, HomeActivity::class.java))
                finish()
            }
        }

        val toolbar = findViewById<androidx.appcompat.widget.Toolbar>(R.id.toolbarRegistro)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = "Registro" // (opcional, puedes poner título)

    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

}

