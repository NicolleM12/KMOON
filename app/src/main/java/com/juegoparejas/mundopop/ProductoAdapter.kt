package com.juegoparejas.mundopop

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class ProductoAdapter (val datos:List<Producto>):RecyclerView.Adapter<ProductoAdapter.ViewHolder>(){

    class ViewHolder(v: View):RecyclerView.ViewHolder(v){

        val txt_titulo:TextView
        val txt_descripcion:TextView
        val txt_precio:TextView
        val iv_foto:ImageView
        var id_producto:Long

        init {
            txt_titulo = v.findViewById(R.id.txt_titulo)
            txt_descripcion = v.findViewById(R.id.txt_des)
            txt_precio = v.findViewById(R.id.txt_precio)
            iv_foto = v.findViewById(R.id.iv_fotoU)
            id_producto = 0
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductoAdapter.ViewHolder {

        val item = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_producto,parent,false)
        return ViewHolder(item)

    }

    override fun onBindViewHolder(holder: ProductoAdapter.ViewHolder, position: Int) {

        holder.txt_titulo.text = datos[position].titulo
        holder.txt_descripcion.text = datos[position].descripcion
        holder.txt_precio.text = datos[position].precio.toString()
        Glide.with(holder.txt_titulo.context)
            .load(datos[position].url_imagen)
            .into(holder.iv_foto)

        holder.id_producto = datos[position].id

        holder.iv_foto.setOnClickListener{

            val intent = Intent(holder.iv_foto.context,ProductoActivity::class.java)
            intent.putExtra("idProducto", holder.id_producto)
            holder.iv_foto.context.startActivity(intent)

        }

    }

    override fun getItemCount() = datos.size

}