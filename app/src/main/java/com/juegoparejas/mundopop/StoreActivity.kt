package com.juegoparejas.mundopop

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.ProgressBar
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.Request
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import org.json.JSONArray
import org.json.JSONObject

class StoreActivity : AppCompatActivity() {

    lateinit var rv_lista:RecyclerView
    lateinit var pb_carga:ProgressBar
    lateinit var listaProductos:List<Producto>

    lateinit var profile:ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_store)

        rv_lista = findViewById(R.id.rv_lista)
        pb_carga = findViewById(R.id.pb_carga)

        rv_lista.visibility = View.GONE

        llenarLista()

        profile = findViewById(R.id.iv_profile)
    }

    private fun llenarLista() {

        val sol = Volley.newRequestQueue(this)
        val url = "https://fakestoreapi.com/products"

        val res = StringRequest(Request.Method.GET,url,

            {response ->

                val productos = JSONArray(response)

                listaProductos = (0 until productos.length()).map {

                    val fila = productos.getJSONObject(it)
                    Producto(
                        id = fila.getLong("id"),
                        titulo = fila.getString("title"),
                        precio = fila.getString("price").toFloat(),
                        descripcion = fila.getString("description"),
                        url_imagen = fila.getString("image")
                    )

                }

                val adaptador = ProductoAdapter(listaProductos)
                rv_lista.layoutManager = LinearLayoutManager(applicationContext)
                rv_lista.adapter = adaptador
                pb_carga.visibility = View.GONE
                rv_lista.visibility = View.VISIBLE

            },
            {
                error -> Log.e("VolleyError", "Error al hacer la solicitud: $error")
            })

        sol.add(res)

    }

    fun goToProfile(v: View){

        startActivity(Intent(applicationContext,ProfileActivity::class.java))
        finish()

    }
}