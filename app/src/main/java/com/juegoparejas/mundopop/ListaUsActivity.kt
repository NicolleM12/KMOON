package com.juegoparejas.mundopop

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class ListaUsActivity : AppCompatActivity() {

    lateinit var rv_listaU:RecyclerView
    lateinit var usuImp:UsuarioDAOImpl

    lateinit var store:ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lista_us)

        usuImp = UsuarioDAOImpl(this)

        rv_listaU = findViewById(R.id.rv_listaU)

        llenarListaU()

        store = findViewById(R.id.iv_tienda)
    }

    private fun llenarListaU() {

        val listaUsuarios:List<Usuario> = usuImp.listarUsuarios()
        val adaptador = UsuarioAdapter(listaUsuarios)

        rv_listaU.adapter = adaptador
        rv_listaU.layoutManager = LinearLayoutManager(this)

    }

    fun goToProfile(v: View){

        startActivity(Intent(applicationContext,ProfileActivity::class.java))
        finish()

    }

    fun goToStore(v:View){

        startActivity(Intent(applicationContext,StoreActivity::class.java))
        finish()

    }
}