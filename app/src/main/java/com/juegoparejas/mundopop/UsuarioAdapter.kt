package com.juegoparejas.mundopop

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class UsuarioAdapter (private val usuarios:List<Usuario>):RecyclerView.Adapter<UsuarioAdapter.ViewHolder>() {

     class ViewHolder(v:View):RecyclerView.ViewHolder(v){

        val txt_nombreU:TextView
        val txt_correoU:TextView

        init {
            txt_nombreU = v.findViewById(R.id.txt_nombreU)
            txt_correoU = v.findViewById(R.id.txt_correoU)
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UsuarioAdapter.ViewHolder {

        val item = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_usuario,parent,false)
        return ViewHolder(item)

    }

    override fun onBindViewHolder(holder: UsuarioAdapter.ViewHolder, position: Int) {

        val usuario = usuarios[position]

        holder.txt_nombreU.text = usuario.nombre
        holder.txt_correoU.text = usuario.correo

    }

    override fun getItemCount() = usuarios.size

}