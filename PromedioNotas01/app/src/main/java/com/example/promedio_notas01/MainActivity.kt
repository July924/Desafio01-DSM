package com.example.promedio_notas01

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import java.text.DecimalFormat

class MainActivity : AppCompatActivity() {

    private lateinit var inputNombre: EditText
    private lateinit var inputNotas: EditText
    private lateinit var btnIngresar: Button
    private lateinit var btnCalcular: Button
    private lateinit var btnClear: Button
    private lateinit var listNotas: ListView
    private lateinit var txtPromedio: TextView

    private val notas = mutableListOf<Double>()
    private lateinit var adapter: ArrayAdapter<String>
    private val ponderaciones = listOf(0.20, 0.15, 0.25, 0.10, 0.30)

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        inputNombre = findViewById(R.id.inputNombre)
        inputNotas = findViewById(R.id.inputNotas)
        btnIngresar = findViewById(R.id.btnIngresar)
        btnCalcular = findViewById(R.id.btnCalcular)
        listNotas = findViewById(R.id.listNotas)
        txtPromedio = findViewById(R.id.txtPromedio)

        adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, mutableListOf())
        listNotas.adapter = adapter

        btnIngresar.setOnClickListener {
            agregarNota()
        }

        btnCalcular.setOnClickListener {
            calcularPromedio()
        }

        btnClear = findViewById(R.id.btnClear)
        btnClear.setOnClickListener {
            limpiarCampos()
        }

    }

    private fun agregarNota() {
        val notaTexto = inputNotas.text.toString().trim()
        val nota = notaTexto.toDoubleOrNull()

        if (nota == null || nota < 0.0 || nota > 10.0) {
            Toast.makeText(this, "Ingrese una nota válida entre 0 y 10", Toast.LENGTH_SHORT).show()
            return
        }

        if (notas.size >= 5) {
            Toast.makeText(this, "Solo se permiten 5 notas", Toast.LENGTH_SHORT).show()
            return
        }

        notas.add(nota)
        adapter.add("Nota ${notas.size}: $nota")
        inputNotas.text.clear()
    }

    private fun calcularPromedio() {
        val nombre = inputNombre.text.toString().trim()

        if (nombre.isEmpty()) {
            Toast.makeText(this, "Ingrese el nombre del estudiante", Toast.LENGTH_SHORT).show()
            return
        }

        if (notas.size < 5) {
            Toast.makeText(this, "Debe ingresar exactamente 5 notas", Toast.LENGTH_SHORT).show()
            return
        }

        var promedio = 0.0
        for (i in notas.indices) {
            promedio += notas[i] * ponderaciones[i]
        }

        val df = DecimalFormat("#.00")
        val promedioStr = df.format(promedio)
        val estado = if (promedio >= 6.0) "APROBADO" else "REPROBADO"

        txtPromedio.text = "Estudiante: $nombre\nPromedio: $promedioStr\nEstado: $estado"
    }

    private fun limpiarCampos() {
        inputNombre.text.clear()
        inputNotas.text.clear()
        txtPromedio.text = ""

        notas.clear()
        adapter.clear()

        Toast.makeText(this, "Campos limpiados", Toast.LENGTH_SHORT).show()
    }

}
