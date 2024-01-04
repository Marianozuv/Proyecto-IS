package com.example.smartcuponapp.poko

class Cliente {

    var idCliente: Int = 0
    var nombre: String = ""
    var apellidoPaterno: String = ""
    var apellidoMaterno: String = ""
    var telefono: String = ""
    var email: String = ""
    var direccion: String = ""
    var fechaNacimiento: String = ""
    var password: String = ""

    constructor(
        nombre: String,
        apellidoPaterno: String,
        apellidoMaterno: String,
        telefono: String,
        email: String,
        direccion: String,
        fechaNacimiento: String,
        password: String
    ) {
        this.nombre = nombre
        this.apellidoPaterno = apellidoPaterno
        this.apellidoMaterno = apellidoMaterno
        this.telefono = telefono
        this.email = email
        this.direccion = direccion
        this.fechaNacimiento = fechaNacimiento
        this.password = password
    }
}