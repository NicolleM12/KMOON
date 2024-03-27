package com.juegoparejas.mundopop

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView

class LogInActivity : AppCompatActivity() {

    lateinit var create:TextView
    lateinit var email:EditText
    lateinit var clave:EditText

    lateinit var usuario: Usuario
    lateinit var usuImp:UsuarioDAOImpl

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_log_in)

        create = findViewById(R.id.tv_crear)
        email = findViewById(R.id.et_email)
        clave = findViewById(R.id.et_clave)

        usuario = Usuario()
        usuImp = UsuarioDAOImpl(this)
    }

    fun goToCreate(v:View){

        startActivity(Intent(applicationContext,CreateAccActivity::class.java))
        finish()

    }

    fun goToSite(v:View){

        var correoString = email.text.toString()
        var claveString = clave.text.toString()
        var siExiste = usuImp.logIn(correoString,claveString)

        if (siExiste != null){

            startActivity(Intent(applicationContext,EntryActivity::class.java))
            finish()

        }else{
            email.error = "Ingrese un correo correcto"
            clave.error = "Ingrese una clave correcta"
        }

    }
}