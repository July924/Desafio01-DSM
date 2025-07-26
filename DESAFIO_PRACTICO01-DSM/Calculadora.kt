package com.example.calculadorabasica03
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.calculadorabasica03.databinding.ActivityMainBinding
import kotlin.math.pow
import kotlin.math.sqrt

class Calculadora : AppCompatActivity() {

    // ViewBinding
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Inflar layout con ViewBinding
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Botón suma
        binding.btnSuma.setOnClickListener {
            val (n1, n2) = obtenerDosNumeros() ?: return@setOnClickListener
            binding.txtResultado.text = "Resultado: ${n1 + n2}"
        }

        // Botón resta
        binding.btnResta.setOnClickListener {
            val (n1, n2) = obtenerDosNumeros() ?: return@setOnClickListener
            binding.txtResultado.text = "Resultado: ${n1 - n2}"
        }

        // Botón multiplicación
        binding.btnMulti.setOnClickListener {
            val (n1, n2) = obtenerDosNumeros() ?: return@setOnClickListener
            binding.txtResultado.text = "Resultado: ${n1 * n2}"
        }

        // Botón división
        binding.btnDivision.setOnClickListener {
            val (n1, n2) = obtenerDosNumeros() ?: return@setOnClickListener
            if (n2 == 0.0) {
                Toast.makeText(this, "No se puede dividir entre cero", Toast.LENGTH_SHORT).show()
            } else {
                binding.txtResultado.text = "Resultado: ${n1 / n2}"
            }
        }

        // Botón exponente (al cuadrado)
        binding.btnExponente.setOnClickListener {
            val n = binding.txtNumero3.text.toString().toDoubleOrNull()
            if (n == null) {
                mostrarError("Ingresa un número válido para el exponente")
            } else {
                val resultado = n.pow(2)
                binding.txtResultado2.text = "Resultado: $resultado"
            }
        }

        // Botón raíz cuadrada
        binding.btnRaiz.setOnClickListener {
            val n = binding.txtNumero3.text.toString().toDoubleOrNull()
            if (n == null || n < 0) {
                mostrarError("Ingresa un número válido para la raíz (mayor o igual a 0)")
            } else {
                val resultado = sqrt(n)
                binding.txtResultado2.text = "Resultado: $resultado"
            }
        }
    }

    // Obtener dos números desde los EditText
    private fun obtenerDosNumeros(): Pair<Double, Double>? {
        val num1 = binding.txtNum1.text.toString().toDoubleOrNull()
        val num2 = binding.txtNum2.text.toString().toDoubleOrNull()
        return if (num1 == null || num2 == null) {
            mostrarError("Ingresa ambos números correctamente")
            null
        } else {
            Pair(num1, num2)
        }
    }

    // Mostrar error como Toast
    private fun mostrarError(mensaje: String) {
        Toast.makeText(this, mensaje, Toast.LENGTH_SHORT).show()
    }
}
