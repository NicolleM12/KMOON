package com.juegoparejas.mundopop

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText

class CreateAccActivity : AppCompatActivity() {

    lateinit var correo:EditText
    lateinit var nombre:EditText
    lateinit var clave:EditText
    lateinit var registro:Button
    lateinit var usuario: Usuario
    lateinit var usuImp:UsuarioDAOImpl

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_acc)

        correo = findViewById(R.id.et_correo)
        nombre = findViewById(R.id.et_nombre)
        clave = findViewById(R.id.et_claveC)

        registro = findViewById(R.id.registroo)

        usuario = Usuario()
        usuImp = UsuarioDAOImpl(this)

    }

    private fun valCamp():Boolean{

        val emailRegex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\$"

        return nombre.text.toString().length > 3 && correo.text.toString().isNotEmpty() && correo.text.toString().matches(emailRegex.toRegex()) && clave.text.toString().length > 6 && nombre.text.matches("^[a-zA-Z]*$".toRegex())
    }

    fun crearUsuario(v:View){

        if (valCamp()){

            usuario = Usuario()
            usuario.nombre = nombre.text.toString().uppercase()
            usuario.correo = correo.text.toString()
            usuario.clave = clave.text.toString()

            if(usuario.correo!!.contains("@kmoon.com")){
                usuario.esAdmin = true
            }

            val usuarioExs = usuImp.obtenerUsuarioCorreo(correo.text.toString())

            if(usuarioExs == null){

                usuImp.guardarUsuario(usuario)

                startActivity(Intent(applicationContext,EntryActivity::class.java))
                finish()

            }else{
                correo.error = "Ya existe el correo en la BD"
            }

        }else{
            nombre.error = "Debe ingresar un nombre válido."
            correo.error = "Debe ingresar un correo válido."
            clave.error = "La contraseña es muy corta."
        }

    }



    fun goToLog(v:View){

        startActivity(Intent(applicationContext,LogInActivity::class.java))
        finish()

    }
}