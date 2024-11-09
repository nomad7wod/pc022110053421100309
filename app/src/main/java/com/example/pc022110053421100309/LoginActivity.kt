package com.example.pc022110053421100309

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.firebase.auth.FirebaseAuth

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_login)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val etEmailLogin = findViewById(R.id.etCorreo)
        val etPasswordLogin = findViewById(R.id.etPassword)
        val btnLogin = findViewById(R.id.btnLogin)
        val auth = FirebaseAuth.getInstance()


        btnLogin.setOnClickListener{
            val email = etEmailLogin.text.toString()
            val password = etPasswordLogin.text.toString()

            auth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this){ task -> if (task.isSuccessful){
    //Inicio de sesión exitoso
                    Snackbar.make(findViewById(android.R.id.content),
                        "Inicio de sesión exitoso", Snackbar.LENGTH_SHORT).show()
                    startActivity(Intent(this, RegistroGastosIngresosAcitivity::class.java))


                }else{
//Inicio de sesión fallido
                    Snackbar.make(findViewById(android.R.id.content),
                        "Error con el inicio de sesión", Snackbar.LENGTH_SHORT).show()
                }
                }

        }
    }
}