package com.juegoparejas.mundopop

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import com.orm.SugarRecord

class UsuarioActivity : AppCompatActivity() {

    lateinit var store:ImageView
    lateinit var profile:ImageView

    lateinit var nombre:EditText
    lateinit var correo:EditText
    lateinit var contra:EditText
    lateinit var usuario:Usuario
    lateinit var guardar:Button
    lateinit var act:Button
    lateinit var eliminar:Button
    lateinit var usuImp:UsuarioDAOImpl

    var id_usuario:Long = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_usuario)

        store = findViewById(R.id.iv_tienda)
        profile = findViewById(R.id.iv_profile)

        nombre = findViewById(R.id.et_nombreC)
        correo = findViewById(R.id.et_correoC)
        contra = findViewById(R.id.et_conC)
        guardar = findViewById(R.id.btn_guardar)
        act = findViewById(R.id.btn_actualizar)
        eliminar = findViewById(R.id.btn_eliminar)
        usuImp = UsuarioDAOImpl(this)
        usuario = Usuario()

        act.visibility = View.GONE
        eliminar.visibility = View.GONE
    }

    private fun initBotones(){
        act.visibility = View.GONE
        eliminar.visibility = View.GONE
        guardar.visibility = View.VISIBLE
    }

    fun limpiarCampos(v:View){
        nombre.text?.clear()
        correo.text?.clear()
        contra.text.clear()

        nombre.requestFocus()
        initBotones()
    }

    private fun valCamp():Boolean{

        val emailRegex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\$"

        return nombre.text.toString().length > 3 && correo.text.toString().isNotEmpty() && correo.text.toString().matches(emailRegex.toRegex()) && contra.text.toString().length > 6 && nombre.text.matches("^[a-zA-Z]*$".toRegex())
    }

    fun guardar(v:View){

        if (valCamp()){

            usuario = Usuario()
            usuario.nombre = nombre.text.toString().uppercase()
            usuario.correo = correo.text.toString()
            usuario.clave = contra.text.toString()

            if(usuario.correo!!.contains("@kmoon.com")){
                usuario.esAdmin = true
            }

            val usuarioExis = usuImp.obtenerUsuarioCorreo(usuario.correo.toString())

            if(usuarioExis == null){

                usuImp.guardarUsuario(usuario)
                Log.d("admin?", "el usuario es admin? ${usuario.esAdmin}")
                limpiarCampos(v)

            }else{

                correo.error = "Ya existe el correo en la BD"

            }

        }else{
            nombre.error = "Debe ingresar un nombre válido."
            correo.error = "Debe ingresar un correo válido."
            contra.error = "La contraseña es muy corta."
        }

    }

    fun consultar(v:View){

        val correoStr = correo.text.toString()

        if (correoStr.isNotEmpty()) {
            val usuario = usuImp.obtenerUsuarioCorreo(correoStr)

            if (usuario != null) {
                nombre.setText(usuario.nombre)
                contra.setText(usuario.clave)

                act.visibility = View.VISIBLE
                eliminar.visibility = View.VISIBLE
                guardar.visibility = View.GONE
            } else {
                correo.error = "No existe el correo."
                nombre.text?.clear()
                contra.text?.clear()
                id_usuario = 0

                initBotones()
            }
        } else {
            correo.error = "Debe ingresar un correo."
            id_usuario = 0
        }

    }

    fun eliminar(v: View){

        Log.d("EliminarUsuario", "ID de usuario: $id_usuario")
        val correoStr = correo.text.toString()
        val usuExs = usuImp.obtenerUsuarioCorreo(correoStr)

        Log.d("EliminarUsuario", "usuario encontrado: $usuExs")

        if (usuExs != null) {

            usuImp.eliminarUsuario(usuExs)
            initBotones()
            limpiarCampos(v)
        } else {
            correo.error = "El usuario no existe."
        }

    }

    fun actualizar(v: View){

        try {

            if (valCamp()){

                usuario.correo = correo.text.toString()
                usuario.nombre = nombre.text.toString()
                usuario.clave = contra.text.toString()
                usuImp.guardarUsuario(usuario)
                limpiarCampos(v)

            }else{
                correo.error = "Debe llenar todos los campos."
            }

        }catch (e:Exception){
            e.printStackTrace()
        }



    }

    fun listaUs(v:View){

        startActivity(Intent(applicationContext,ListaUsActivity::class.java))
        finish()

    }

    fun goToStore(v: View){

        startActivity(Intent(applicationContext,StoreActivity::class.java))
        finish()

    }

    fun goToProfile(v: View){

        startActivity(Intent(applicationContext,ProfileActivity::class.java))
        finish()

    }
}