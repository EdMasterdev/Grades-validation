package com.example.parcial_1_zhang_maestre

import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.parcial_1_zhang_maestre.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {


    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnValidar.setOnClickListener { validarNota() }
    }

    private fun validarNota() {
        val textoNota = binding.etNota.text.toString().trim()


        if (textoNota.isEmpty()) {
            showCustomToast("️  Por favor ingresa una nota.")
            return
        }

        val nota = textoNota.toIntOrNull()
        if (nota == null) {
            showCustomToast("  La nota debe ser un número entero.")
            return
        }

        if (nota !in 0..100) {
            showCustomToast("  La nota debe estar entre 0 y 100.")
            return
        }


        val resultado = when (nota) {
            in 91..100 -> "A  –  ¡Excelente!"
            in 81..90  -> "B  –  Bueno"
            in 71..80  -> "C  –  Regular"
            in 61..70  -> "D  –  Más o menos regular"
            else       -> "F  –  No aprobado, gracias por participar"
        }

        showCustomToast("Resultado: $resultado")
    }


    private fun showCustomToast(mensaje: String) {
        val inflater: LayoutInflater = layoutInflater
        val layout = inflater.inflate(R.layout.custom_toast, findViewById(R.id.rootToast))

        layout.findViewById<android.widget.TextView>(R.id.toastMensaje).text = mensaje

        Toast(applicationContext).apply {
            setGravity(Gravity.BOTTOM or Gravity.CENTER_HORIZONTAL, 0, 120)
            duration = Toast.LENGTH_LONG
            view = layout
        }.show()
    }
}
