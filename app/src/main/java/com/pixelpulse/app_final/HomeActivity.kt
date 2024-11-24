package com.pixelpulse.app_final

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.text.SpannableString
import android.text.style.UnderlineSpan
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.viewbinding.ViewBinding
import com.pixelpulse.app_final.adapter.itemAdapter
import com.pixelpulse.app_final.databinding.ActivityHomeBinding

class HomeActivity : AppCompatActivity() {
    lateinit var binding: ActivityHomeBinding
    lateinit var username: String
    lateinit var lista: MutableList<ListaOpciones>
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityHomeBinding.inflate(layoutInflater)
        username =intent.getStringExtra("username").toString();
        supportActionBar!!.hide()
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(binding.root)
        lista = mutableListOf()
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        lista.add(ListaOpciones(R.drawable.lista_a,"Ingreso personal"))
        lista.add(ListaOpciones(R.drawable.lista_f,"Salida del personal"))
        lista.add(ListaOpciones(R.drawable.contrato,"Contrataciones"))
        lista.add(ListaOpciones(R.drawable.fin,"CESES"))
        val adapter = itemAdapter(this, lista)
        binding.listaOpciones.adapter =adapter;

        //Subrayado del titulo
        binding.txtTitulo.text = SpannableString("Recursos Humanos").apply {
            setSpan(UnderlineSpan(), 0, length, 0)
        }
        binding.usuario.setText(username)
        binding.fotoPerfil.setOnClickListener {
            desplegarmenu()
        }
        binding.btnBack.setOnClickListener(){
            AlertaCierre();
        }
    }

    private fun AlertaCierre() {
        var alerta = AlertDialog.Builder(this);
        alerta.setTitle("Alerta de Cierre de sesion-");
        alerta.setMessage("Seguro de cerrar la sesion ?");
        alerta.setCancelable(false)
        alerta.setPositiveButton("Aceptar"){
            dialog, wich -> CerrarSesion()
        }
        alerta.setNegativeButton("Cancelar"){
            dialog, wich ->
        }
        alerta.show()
    }

    private fun CerrarSesion() {
        BorrarSesion()
        val intent = Intent(this, LoginActivity::class.java)
        startActivity(intent)
        finish()
    }

    private fun BorrarSesion() {
        var sharedPreferences =getSharedPreferences("loginData", Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit();
        editor.clear();
        editor.remove("nombre");
        editor.commit();
    }

    private fun desplegarmenu() {

    }
}