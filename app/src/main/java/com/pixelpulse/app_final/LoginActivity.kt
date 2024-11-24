package com.pixelpulse.app_final

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.pixelpulse.app_final.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {
    lateinit var viewBinding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = ActivityLoginBinding.inflate(layoutInflater)
        supportActionBar!!.hide()
        enableEdgeToEdge()
        setContentView(viewBinding.root)

        // Verificar si ya hay una sesión guardada
        val sharedPreferences = getSharedPreferences("loginData", Context.MODE_PRIVATE)
        val savedUsername = sharedPreferences.getString("nombre", null)
        if (savedUsername != null) {
            // Si ya hay un usuario guardado, redirigir directamente al HomeActivity
            val intent = Intent(this, HomeActivity::class.java)
            startActivity(intent)
            finish()
            return
        }

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Listener para el botón de ingresar
        viewBinding.btnIngresar.setOnClickListener {
            val username = viewBinding.edtUsername.text.toString()
            val password = viewBinding.edtPassword.text.toString()

            if (username.isEmpty() || username != "Gabriel") {
                viewBinding.edtUsername.error = "USERNAME ERRONEO"
            } else if (password.isEmpty() || password != "123") {
                viewBinding.edtPassword.error = "PASSWORD ERRONEO"
            } else {
                // Credenciales correctas, guardar sesión si está marcada la opción "Recordar"
                if (viewBinding.chkRecordar.isChecked) {
                    GuardarPerfil(username)
                }
                // Navegar a HomeActivity
                val intent = Intent(this, HomeActivity::class.java)
                intent.putExtra("username", username)
                startActivity(intent)
                finish()
                Toast.makeText(this, "Bienvenido $username", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun GuardarPerfil(user: String) {
        val sharedPreferences = getSharedPreferences("loginData", Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.putString("nombre", user)
        editor.apply()
    }
}















