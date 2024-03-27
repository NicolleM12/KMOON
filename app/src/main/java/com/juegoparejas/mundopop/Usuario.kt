package com.juegoparejas.mundopop

import com.orm.SugarRecord

class Usuario:SugarRecord<Usuario?>() {

    var nombre: String? = null

    var correo: String? = null

    var clave: String? = null
    var esAdmin: Boolean = false

    // Agregar campo de ID explícito
    var id: Long = 0
}