package com.juegoparejas.mundopop

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.ProgressBar
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.Request
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import org.json.JSONObject

class EntryActivity : AppCompatActivity() {

    lateinit var store:ImageView
    lateinit var profile:ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_entry)

        store = findViewById(R.id.iv_tienda)
        profile = findViewById(R.id.iv_profile)

    }

    fun goToStore(v:View){

        startActivity(Intent(applicationContext,StoreActivity::class.java))
        finish()

    }

    fun goToProfile(v:View){

        startActivity(Intent(applicationContext,ProfileActivity::class.java))
        finish()

    }
}