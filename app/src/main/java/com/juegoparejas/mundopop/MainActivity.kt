package com.juegoparejas.mundopop

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button

class MainActivity : AppCompatActivity() {

    lateinit var login:Button
    lateinit var crearS:Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        login = findViewById(R.id.btn_login)
        crearS = findViewById(R.id.btn_crear)
    }

    fun goToLog(v:View){

        startActivity(Intent(applicationContext,LogInActivity::class.java))
        finish()

    }

    fun goToCreate(v:View){

        startActivity(Intent(applicationContext,CreateAccActivity::class.java))
        finish()

    }
}