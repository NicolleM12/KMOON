package com.juegoparejas.mundopop

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import com.android.volley.Request
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.bumptech.glide.Glide
import org.json.JSONArray
import org.json.JSONObject

class ProductoActivity : AppCompatActivity() {

    lateinit var store:ImageView
    lateinit var profile:ImageView

    lateinit var titulo:TextView
    lateinit var precio:TextView
    lateinit var des:TextView
    lateinit var rate:RatingBar
    lateinit var foto:ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_producto)

        store = findViewById(R.id.iv_tienda)
        profile = findViewById(R.id.iv_profile)

        titulo = findViewById(R.id.tv_titulo)
        precio = findViewById(R.id.tv_precio)
        des = findViewById(R.id.tv_des)
        rate = findViewById(R.id.rating)
        foto = findViewById(R.id.iv_imagen)

        val idProducto = intent.extras?.getLong("idProducto", 0)
        val url = "https://fakestoreapi.com/products/${idProducto}"

        val sol = Volley.newRequestQueue(this)
        val res = StringRequest(Request.Method.GET,url,
            {response ->

                val producto = JSONObject(response)

                try {

                    val imagenP = producto.getString("image")
                    titulo.text = producto.getString("title")
                    precio.text = producto.getString("price")
                    des.text = producto.getString("description")

                    val ratingOb = producto.getJSONObject("rating")
                    val rateValue = ratingOb.getDouble("rate").toFloat()
                    rate.rating = rateValue
                    Glide.with(this).load(imagenP).into(foto)


                }catch (e:Exception){
                    e.printStackTrace()
                    e.toString()
                }

            },
            {
                error -> Log.e("VolleyError", "Error al hacer la solicitud: $error")
            })
        sol.add(res)
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