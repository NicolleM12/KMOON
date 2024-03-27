package com.juegoparejas.mundopop

import android.content.Context
import com.orm.SugarRecord

class UsuarioDAOImpl (context: Context):UsuarioDAO{

    override fun guardarUsuario(usuario: Usuario) {
        usuario.save()
    }

    override fun obtenerUsuarioCorreo(correo: String): Usuario? {
        return SugarRecord.find(Usuario::class.java, "correo=?", correo).firstOrNull()
    }

    override fun eliminarUsuario(usuario: Usuario) {
        usuario.delete()
    }

    override fun actualizarUsuario(usuario: Usuario) {
        usuario.save()
    }

    override fun listarUsuarios(): List<Usuario> {
        return SugarRecord.listAll(Usuario::class.java)
    }

    fun logIn(correo:String, clave:String): Usuario? {
        return SugarRecord.find(Usuario::class.java, "correo=? AND clave=?", correo, clave).firstOrNull()
    }

    fun admin(correo: String,clave: String):Usuario?{

        val correoA = correo.toString().contains("@kmoon")

        return SugarRecord.find(Usuario::class.java, "correo=? AND clave=?",
            correoA.toString(), clave).firstOrNull()

    }
}