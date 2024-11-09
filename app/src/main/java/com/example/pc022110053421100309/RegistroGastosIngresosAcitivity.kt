package com.example.pc022110053421100309

import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.firestore.FirebaseFirestore

class RegistroGastosIngresosAcitivity : AppCompatActivity() {

    private val db = FirebaseFirestore.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_registro_gastos_ingresos_acitivity)

        // Referencias a los EditTexts en la interfaz
        val etFecha: EditText = findViewById(R.id.etFecha)
        val etDescripcion: EditText = findViewById(R.id.etDescripcion)
        val etMonto: EditText = findViewById(R.id.etMonto)

        // Botón para guardar los datos
        val btGuardar: Button = findViewById(R.id.btGuardar)

        btGuardar.setOnClickListener {
            val fecha = etFecha.text.toString()
            val descripcion = etDescripcion.text.toString()
            val monto = etMonto.text.toString()

            if (fecha.isEmpty() || descripcion.isEmpty() || monto.isEmpty()) {
                Toast.makeText(this, "Por favor, completa todos los campos", Toast.LENGTH_SHORT).show()
            } else {
                guardarDatos(fecha, descripcion, monto)
            }
        }
    }

    private fun guardarDatos(fecha: String, descripcion: String, monto: String) {
        // Convertir el monto a tipo numérico (Double)
        val montoDouble = monto.toDoubleOrNull()

        if (montoDouble == null) {
            Toast.makeText(this, "El monto debe ser un número válido", Toast.LENGTH_SHORT).show()
            return
        }

        val gastosPersonales = hashMapOf(
            "fecha" to fecha,
            "descripcion" to descripcion,
            "monto" to montoDouble
        )

        // Guardar el documento en la colección "gastosPersonales"
        db.collection("gastosPersonales")
            .add(gastosPersonales)
            .addOnSuccessListener { documentReference ->
                Log.d("Firestore", "Documento agregado con ID: ${documentReference.id}")
                Toast.makeText(this, "Gasto guardado correctamente", Toast.LENGTH_SHORT).show()
            }
            .addOnFailureListener { e ->
                Log.w("Firestore", "Error al agregar el documento", e)
                Toast.makeText(this, "Error al guardar el gasto", Toast.LENGTH_SHORT).show()
            }
    }
}
