package com.example.pc022110053421100309

import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.firebase.firestore.FirebaseFirestore

class RegistroGastosIngresosAcitivity : AppCompatActivity() {

    private val db = FirebaseFirestore.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_registro_gastos_ingresos_acitivity)
        //Referencias a loos editext en al interfaz
        val etFecha: EditText = findViewById(R.id.etFecha)
        val etDescripcion: EditText = findViewById(R.id.etDescripcion)
        val etMonto:EditText = findViewById(R.id.etMonto)

        // Botón para guardar los datos
        val btGuardar: Button = findViewById(R.id.btGuardar)


        btGuardar.setOnClickListener {
            guardarDatos(
                fecha = etFecha.text.toString(),
                descripcion = etDescripcion.text.toString(),
                monto = etMonto.text.toString()
            )
        }
    }
    private fun guardarDatos(
        fecha: String,
        descripcion: String,
        monto: String
    ){
        val gastosPersonales = hashMapOf(
            "fecha" to fecha,
            "descripcion" to descripcion,
            "monto" to monto
        )

        // Guardar el documento en la colección "registroGastos"
        db.collection("gastosPersonales")
            .add(gastosPersonales)
            .addOnSuccessListener { documentReference ->
                Log.d("Firestore", "Documento agregado con ID: ${documentReference.id}")
            }
            .addOnFailureListener { e ->
                Log.w("Firestore", "Error al agregar el documento", e)
            }

    }
}