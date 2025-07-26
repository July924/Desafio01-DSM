package com.example.promedio_notas01

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class MenuActivity : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.inicio)

        val btnPantalla1 = findViewById<Button>(R.id.btnPantalla1)
        val btnPantalla2 = findViewById<Button>(R.id.btnPantalla2)
        val btnPantalla3 = findViewById<Button>(R.id.btnPantalla3)

        btnPantalla1.setOnClickListener {
            startActivity(Intent(this, Promedio::class.java))
        }

        btnPantalla2.setOnClickListener {
            startActivity(Intent(this, Promedio::class.java))
        }

        btnPantalla3.setOnClickListener {
            startActivity(Intent(this, Promedio::class.java))
        }
    }
}
