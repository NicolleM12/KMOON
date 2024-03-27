package com.juegoparejas.mundopop

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView

class ProfileActivity : AppCompatActivity() {

    lateinit var store:ImageView
    lateinit var admin:Button
    lateinit var txt_nombre:EditText
    lateinit var txt_corr:EditText
    lateinit var txt_con:EditText
    lateinit var cambios:Button

    lateinit var usuBD:UsuarioDAOImpl
    lateinit var usuario: Usuario
    lateinit var login:LogInActivity

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)

        store = findViewById(R.id.iv_tienda)
        txt_nombre = findViewById(R.id.txt_nombre)
        txt_corr = findViewById(R.id.txt_correo)
        txt_con = findViewById(R.id.txt_contraP)
        cambios = findViewById(R.id.btn_cambios)
        admin = findViewById(R.id.btn_admin)

        usuBD = UsuarioDAOImpl(this)
        usuario = Usuario()
        login = LogInActivity()

        admin.visibility = View.GONE


    }

    fun adminB(v:View){
        startActivity(Intent(applicationContext,UsuarioActivity::class.java))
        finish()
    }

    fun datosProf(v: View) {
        val correoE = txt_corr.text.toString()

        if (correoE.isNotEmpty()) {
            val usuarioC = usuBD.obtenerUsuarioCorreo(correoE)

            if (usuarioC != null) {
                Log.d("admin?", "Valor de esAdmin: ${usuarioC.esAdmin}")

                txt_nombre.setText(usuarioC.nombre)
                txt_con.setText(usuarioC.clave)

                if (usuarioC.esAdmin) {
                    Log.d("admin?", "el usuario es admin? ${usuarioC.esAdmin}")
                    admin.visibility = View.VISIBLE
                    admin.setOnClickListener {
                        startActivity(Intent(applicationContext, UsuarioActivity::class.java))
                        finish()
                    }
                } else {
                    Log.d("admin?", "El usuario no es admin: ${usuarioC.esAdmin}")
                }
            } else {
                txt_corr.error = "Correo incorrecto"
            }
        }
    }




    fun goToStore(v: View){

        startActivity(Intent(applicationContext,StoreActivity::class.java))
        finish()

    }
}