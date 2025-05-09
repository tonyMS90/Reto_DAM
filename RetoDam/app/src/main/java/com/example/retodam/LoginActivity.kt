package com.example.retodam

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class LoginActivity : AppCompatActivity() {

    private lateinit var emailLogin: EditText
    private lateinit var passwordLogin: EditText
    private lateinit var buttonLogin: Button
    private lateinit var buttonIrRegistro: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        emailLogin = findViewById(R.id.editTextEmailLogin)
        passwordLogin = findViewById(R.id.editTextPasswordLogin)
        buttonLogin = findViewById(R.id.buttonLogin)
        buttonIrRegistro = findViewById(R.id.buttonIrRegistro)

        buttonLogin.setOnClickListener {
            val email = emailLogin.text.toString()
            val password = passwordLogin.text.toString()

            if (UsuarioSession.usuarioActual?.email == email) {
                startActivity(Intent(this, HomeActivity::class.java))
                finish()
            } else {
                Toast.makeText(this, "Usuario no registrado, por favor regístrate.", Toast.LENGTH_SHORT).show()
            }
        }

        buttonIrRegistro.setOnClickListener {
            startActivity(Intent(this, RegistroActivity::class.java))
        }
    }
}
