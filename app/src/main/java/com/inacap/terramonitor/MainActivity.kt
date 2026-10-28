package com.inacap.terramonitor

import android.graphics.Color
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.Switch
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import kotlin.random.Random

class MainActivity : AppCompatActivity() {

    // 1. Declarar las variables que se conectarán con el diseño XML
    private lateinit var tvTemperatura: TextView
    private lateinit var tvEstadoCalefactor: TextView
    private lateinit var switchCalefactor: Switch

    // Variables para simular los datos del sensor
    private val handler = Handler(Looper.getMainLooper())
    private var simuladorActivo = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // 2. Enlazar las variables de Kotlin con los IDs del diseño XML
        tvTemperatura = findViewById(R.id.tvTemperatura)
        tvEstadoCalefactor = findViewById(R.id.tvEstadoCalefactor)
        switchCalefactor = findViewById(R.id.switchCalefactor)

        // 3. Configurar qué pasa cuando el usuario toca el interruptor (Switch)
        switchCalefactor.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                // Al encender
                tvEstadoCalefactor.text = "Calefacción Encendida"
                tvEstadoCalefactor.setTextColor(Color.parseColor("#FF5722"))
            } else {
                // Al apagar
                tvEstadoCalefactor.text = "Calefacción Apagada"
                tvEstadoCalefactor.setTextColor(Color.parseColor("#333333"))
            }
        }

        // 4. Iniciar el ciclo falso de lectura de temperatura
        iniciarSimulacionTemperatura()
    }

    private fun iniciarSimulacionTemperatura() {
        val runnable = object : Runnable {
            override fun run() {
                if (simuladorActivo) {
                    // Genera un número aleatorio entre 24.0 y 26.5
                    val temperaturaSimulada = 24.0 + Random.nextDouble() * 2.5

                    // Muestra la temperatura en el texto principal
                    tvTemperatura.text = String.format("%.1f °C", temperaturaSimulada)

                    // Se vuelve a ejecutar a sí mismo cada 3000 milisegundos (3 segundos)
                    handler.postDelayed(this, 3000)
                }
            }
        }
        handler.post(runnable)
    }

    override fun onDestroy() {
        super.onDestroy()
        // Apaga la simulación si cierras la app
        simuladorActivo = false
        handler.removeCallbacksAndMessages(null)
    }
}