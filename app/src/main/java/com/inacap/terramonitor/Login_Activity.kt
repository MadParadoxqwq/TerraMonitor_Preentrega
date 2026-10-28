package com.inacap.terramonitor

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        // Enlazar el código con el diseño visual
        val etUsuario = findViewById<EditText>(R.id.etUsuario)
        val etPassword = findViewById<EditText>(R.id.etPassword)
        val btnIngresar = findViewById<Button>(R.id.btnIngresar)

        // Acción al presionar el botón
        btnIngresar.setOnClickListener {
            val usuarioIngresado = etUsuario.text.toString()
            val passwordIngresada = etPassword.text.toString()

            // Validar credenciales (Usuario: admin / Pass: 12345)
            if (usuarioIngresado == "admin" && passwordIngresada == "12345") {

                // Si es correcto, pasar a la pantalla principal (MainActivity)
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)

                // Cerrar la pantalla de login para que no se pueda volver atrás con el botón del celular
                finish()
            } else {
                // Si es incorrecto, mostrar un mensaje flotante de error
                Toast.makeText(this, "Usuario o contraseña incorrectos", Toast.LENGTH_SHORT).show()
            }
        }
    }
}