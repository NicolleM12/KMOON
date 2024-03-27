package com.juegoparejas.mundopop

interface UsuarioDAO {

    fun guardarUsuario(usuario: Usuario)
    fun obtenerUsuarioCorreo(correo:String): Usuario?
    fun eliminarUsuario(usuario: Usuario)
    fun actualizarUsuario(usuario: Usuario)
    fun listarUsuarios():List<Usuario>
}