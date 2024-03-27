package com.juegoparejas.mundopop

data class Producto(

    var id:Long = 0,
    var titulo:String,
    var precio:Float,
    var descripcion:String,
    var url_imagen:String

){
    constructor():this(0,"",0F,"","")
}
