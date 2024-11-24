package com.pixelpulse.app_final

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar!!.hide()
        enableEdgeToEdge()
        setContentView(R.layout.activity_splash)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        var sharedPreferences = getSharedPreferences("loginData", Context.MODE_PRIVATE);
        var user = sharedPreferences.getString("nombre", "").toString()
        Handler().postDelayed({
            if (user.isEmpty()){
                val intent = Intent(this, LoginActivity::class.java);
                startActivity(intent);
                finish();
            }
            else{
                val intent = Intent(this, HomeActivity::class.java);
                intent.putExtra("username", user)
                startActivity(intent);
                finish();
            }
        }, 2000)
    }
}